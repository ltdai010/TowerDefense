package Screen;

import Bunch.BunchOfEnemy;
import Bunch.BunchOfTower;
import Console.Map;
import Console.Menu;
import Console.Player;
import Console.UI;
import PortableEntity.Enemy.*;
import PortableEntity.GameEntity;
import Button.*;
import TileEntity.Tower.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class GameField extends JPanel implements Runnable{
    private BunchOfTower bunchOfTower;
    private Player player;
    private BunchOfEnemy bunchOfEnemy;
    private Thread animator;
    private Map map;
    private GameStage gameStage;
    private UI ui;
    private int stage;
    private boolean pause;
    public static final int CONTINUE = 1;
    public static final int NEW = 0;
    public GameField(Player player, GameStage gameStage, int operation) {
        this.player = player;
        player.setGameField(this);
        player.addScoreText();
        pause = false;
        this.gameStage = gameStage;
        if(operation == NEW)
        {
            player.addScore(10000);
            stage = 1;
        }
        else
        {
            try {
                readFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        initBoard();
        ui = new UI(this);
    }

    private void readFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader("src\\save\\game\\player.txt");
        Scanner scanner = new Scanner(fileReader);
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
        line = scanner.nextLine();
        while (line.compareTo("end")!=0)
        {
            String[] s = line.split(" ");
            switch (s[0])
            {
                case "BE":
                    Enemy enemy = new BossEnemy(map);
                    bunchOfEnemy.add(enemy);
                    break;
                case "NE":
                    Enemy enemy1 = new NormalEnemy(map);
                    bunchOfEnemy.add(enemy1);
                    break;
                case "SE":
                    Enemy enemy2 = new SmallEnemy(map);
                    bunchOfEnemy.add(enemy2);
                    break;
                case "TE":
                    Enemy enemy3 = new TankerEnemy(map);
                    bunchOfEnemy.add(enemy3);
                    break;
            }
            line = scanner.nextLine();
        }
    }

    public Player getPlayer() {
        return player;
    }

    private void initBoard() {
        setLayout(null);
        setBounds(0,0, GameEntity.SCREENWIDTH, GameEntity.SCREENHEIGHT);
        setBackground(Color.WHITE);
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
        bunchOfTower = new BunchOfTower();
        bunchOfEnemy = new BunchOfEnemy();
        map.getBunchOfRoad().getStartPoint().setBunchOfEnemy(bunchOfEnemy);
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
        map.getBunchOfRoad().getStartPoint().setStartTime(System.currentTimeMillis());
        while (true)
        {
            if(!pause)
            {
                ui.setInformationText();
                bunchOfEnemy.onAction();
                bunchOfTower.onAction(bunchOfEnemy);
                if(stage < 2 && !map.getBunchOfRoad().getStartPoint().spawnEnemy())
                {
                    ++stage;
                    loadStage();
                }
                repaint();
                Toolkit.getDefaultToolkit().sync();
            }
            else {
                bunchOfEnemy.setSleepTime(System.currentTimeMillis());
                bunchOfTower.setSleepTime(System.currentTimeMillis());
            }
        }
    }

    public void loadStage()
    {
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
        map.getBunchOfRoad().getStartPoint().setBunchOfEnemy(bunchOfEnemy);
    }



    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
}