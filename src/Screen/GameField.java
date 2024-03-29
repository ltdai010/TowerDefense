package Screen;

import Bunch.BunchOfEnemy;
import Bunch.BunchOfTower;
import Console.Map;
import Console.Menu;
import Console.Player;
import Console.UI;
import PortableEntity.Enemy.*;
import PortableEntity.GameEntity;
import TileEntity.Target;
import TileEntity.Tower.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class GameField extends JPanel implements Runnable{
    private BunchOfTower bunchOfTower;
    private Player player;
    private BunchOfEnemy bunchOfEnemy;
    private Thread animator;
    private Map map;
    private GameStage gameStage;
    private AudioInputStream audioInputStream;
    private Clip clip;
    private UI ui;
    private int stage;
    private boolean pause;
    private String state;
    public static final String WIN = "win";
    public static final String LOST = "lost";
    public static final int CONTINUE = 1;
    public static final int NEW = 0;
    private boolean gameOver;
    public GameField(Player player, GameStage gameStage, int operation) {
        gameOver = false;
        this.player = player;
        player.setGameField(this);
        this.add(player.getScoreText());
        this.add(player.getName_label());
        this.add(player.getAddScore_label());
        this.add(player.getAvatar());
        pause = false;
        this.gameStage = gameStage;
        if(operation == NEW)
        {
            player.setCoin(0);
            player.setScore(0);
            stage = 1;
        }
        try {
            initBoard(operation);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        ui = new UI(this);
        this.getUi().getStage_label().setText("Stage " + stage);
    }

    public int getStage() {
        return stage;
    }


    private void readFile() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        FileReader fileReader = new FileReader("src\\save\\game\\player.txt");
        Scanner scanner = new Scanner(fileReader);
        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();
        stage = scanner.nextInt();
        String line = scanner.nextLine();
        while (line.compareTo("end")!=0)
        {
            String[] s = line.split(" ");
            switch (s[0])
            {
                case "AT":
                    bunchOfTower.add(new AntiTankTower(Integer.parseInt(s[1]), Integer.parseInt(s[2]), player));
                    break;
                case "MGT":
                    bunchOfTower.add(new MachineGunTower(Integer.parseInt(s[1]), Integer.parseInt(s[2]), player));
                    break;
                case "MT":
                    bunchOfTower.add(new MissileTower(Integer.parseInt(s[1]), Integer.parseInt(s[2]), player));
                    break;
                case "NT":
                    bunchOfTower.add(new NormalTower(Integer.parseInt(s[1]), Integer.parseInt(s[2]), player));
                    break;
                case "ST":
                    bunchOfTower.add(new SniperTower(Integer.parseInt(s[1]), Integer.parseInt(s[2]), player));
                    break;
            }
            line = scanner.nextLine();
        }
        loadStage();
        map.getBunchOfRoad().getTarget().setHP(scanner.nextInt());
        player.setScore(scanner.nextInt());
    }

    public Player getPlayer() {
        return player;
    }

    private void initBoard(int operation) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        setLayout(null);
        setBounds(0,0, GameEntity.SCREENWIDTH, GameEntity.SCREENHEIGHT);
        setBackground(Color.WHITE);
        bunchOfTower = new BunchOfTower();
        bunchOfEnemy = new BunchOfEnemy(this);
        if(operation == CONTINUE)
        {
            readFile();
        }
        else
            loadStage();
        setPreferredSize(new Dimension(GameEntity.SCREENWIDTH, GameEntity.SCREENHEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public GameStage getGameStage() {
        return gameStage;
    }

    public UI getUi() {
        return ui;
    }

    public BunchOfEnemy getBunchOfEnemy() {
        return bunchOfEnemy;
    }

    public BunchOfTower getBunchOfTower() {
        return bunchOfTower;
    }

    public Clip getClip() {
        return clip;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    public void draw(Graphics g)
    {
        map.draw(g);
        bunchOfTower.drawBullet(g);
        bunchOfTower.draw(g);
        bunchOfEnemy.draw(g);
        ui.onClickDraw(g);
        map.getBunchOfRoad().getTarget().draw(g);
    }

    public Map getMap() {
        return map;
    }

    @Override
    public void run() {
        bunchOfEnemy.setSleepTime(System.currentTimeMillis());
        bunchOfTower.setSleepTime(System.currentTimeMillis());
        try {
            ui.drawStage(stage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        gameOver= true; //you can set the win or lost state here
//        state = LOST;
        while (!gameOver)
        {
            if(map.getBunchOfRoad().getTarget().getHP() <= 0)
            {
                player.saveScore();
                state = LOST;
                gameOver = true;
                pause = true;
            }
            if(!pause)
            {
                ui.setInformationText();
                bunchOfEnemy.onAction();
                bunchOfTower.onAction(bunchOfEnemy);
                player.addScoreAnimate();
                if(!map.getBunchOfRoad().getStartPoint().spawnEnemy())
                {
                    if(bunchOfEnemy.getBunch().size() == 0)
                    {
                        map.getBunchOfRoad().getStartPoint().getScanner().close();
                        if(stage < 5)
                        {
                            ++stage;
                            player.setStage(stage);
                            player.saveGame();
                            remove(this.map.getBunchOfRoad().getTarget().getHP_label());
                            this.getUi().getStage_label().setText("Stage " + stage);
                            try {
                                audioInputStream.close();
                                clip.stop();
                                clip.close();
                                ui.drawStage(stage);
                                loadStage();
                            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            player.saveScore();
                            gameOver = true;
                            state = WIN;
                            pause = true;
                        }
                    }
                }
                repaint();
                Toolkit.getDefaultToolkit().sync();
            }
            else {
                bunchOfEnemy.setSleepTime(System.currentTimeMillis());
                bunchOfTower.setSleepTime(System.currentTimeMillis());
            }
        }
        ui.loadResult(state);
    }


    public void loadStage() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        int HP = Target.MaxHP;
        player.addCoin(100);
        if(map != null)
        {
            HP = map.getBunchOfRoad().getTarget().getHP();
        }
        if(stage == 1)
        {
            map = new Map(Map.MAP1);
        }
        else if(stage == 2)
        {
            map = new Map(Map.MAP2);
        }
        else if(stage == 3)
        {
            map = new Map(Map.MAP3);
        }
        else if(stage == 4)
        {
            map = new Map(Map.MAP4);
        }
        else if(stage == 5)
        {
            map = new Map(Map.MAP5);
        }
        audioInputStream = AudioSystem.getAudioInputStream(new File("src\\audio\\stage" + stage + ".wav"));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        this.add(map.getBunchOfRoad().getTarget().getHP_label());
        map.getBunchOfRoad().getTarget().setHP(HP);
        map.getBunchOfRoad().getStartPoint().setStartTime(System.currentTimeMillis());
        map.getBunchOfRoad().getStartPoint().setBunchOfEnemy(bunchOfEnemy);
    }

    public void destroy(){
        this.removeAll();
        this.getClip().stop();
        this.getClip().close();
        this.getGameStage().setVisible(false);
        this.getGameStage().dispose();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
}