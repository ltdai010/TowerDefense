package Screen;

import Bunch.BunchOfEnemy;
import Bunch.BunchOfTower;
import Console.Map;
import Console.Menu;
import Console.Player;
import PortableEntity.Enemy.*;
import PortableEntity.GameEntity;
import Button.*;
import TileEntity.Tower.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class GameField extends JPanel implements Runnable{
    private BunchOfTower bunchOfTower;
    private Player player;
    private BunchOfEnemy bunchOfEnemy;
    private BuyTowerButton buyNormalTowerButton;
    private BuyTowerButton buySniperTowerButton;
    private BuyTowerButton buyAntiTankTowerButton;
    private BuyTowerButton buyMachineGunTowerButton;
    private BuyTowerButton buyMissileTowerButton;
    private JButton quit;
    private Thread animator;
    private Map map;
    private GameStage gameStage;
    private boolean pause;
    public static final int CONTINUE = 1;
    public static final int NEW = 0;
    public GameField(Player player, GameStage gameStage, int operation) {
        this.player = player;
        player.setGameField(this);
        player.addScoreText();
        pause = false;
        this.gameStage = gameStage;
        initBoard();
        if(operation == NEW)
        {
            player.addScore(10000);
        }
        else
        {
            try {
                readFile();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void readFile() throws FileNotFoundException {
        FileReader fileReader = new FileReader("src\\save\\game\\player.txt");
        Scanner scanner = new Scanner(fileReader);
        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();
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
        map = new Map();
        bunchOfTower = new BunchOfTower();
        bunchOfEnemy = new BunchOfEnemy();
        initButton();
        setPreferredSize(new Dimension(GameEntity.SCREENWIDTH, GameEntity.SCREENHEIGHT));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void initButton()
    {
        buyNormalTowerButton = new BuyNormalTowerTowerButton(GameEntity.SCREENWIDTH/2 - 200,525, bunchOfTower, this, map);
        buySniperTowerButton = new BuySniperTowerTowerButton(GameEntity.SCREENWIDTH/2 - 100,525 , bunchOfTower, this, map);
        buyAntiTankTowerButton = new BuyAnitiTankTowerButton(GameEntity.SCREENWIDTH/2 ,525 , bunchOfTower, this, map);
        buyMachineGunTowerButton = new BuyMachineGunTowerButton(GameEntity.SCREENWIDTH/2 + 100,525 , bunchOfTower, this, map);
        buyMissileTowerButton = new BuyMissileTowerButton(GameEntity.SCREENWIDTH/2 + 200,525 , bunchOfTower, this, map);
        this.add(buyNormalTowerButton);
        this.add(buySniperTowerButton);
        this.add(buyAntiTankTowerButton);
        this.add(buyMachineGunTowerButton);
        this.add(buyMissileTowerButton);
        addMouseListener(buyNormalTowerButton);
        addMouseListener(buySniperTowerButton);
        addMouseListener(buyAntiTankTowerButton);
        addMouseListener(buyMachineGunTowerButton);
        addMouseListener(buyMissileTowerButton);
        quit = new JButton("=");
        quit.setBackground(Color.WHITE);
        quit.setBorder(null);
        quit.setMargin(null);
        quit.setBounds(1100, 0, 50,50);
        this.add(quit);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause = true;
                int click = JOptionPane.showConfirmDialog(null, "Save Game?");
                if(click == JOptionPane.YES_OPTION)
                {
                    Menu menu = new Menu();
                    player.saveGame();
                    gameStage.setVisible(false);
                    gameStage.dispose();
                }
                if(click == JOptionPane.NO_OPTION)
                {
                    Menu menu = new Menu();
                    player.saveScore();
                    gameStage.setVisible(false);
                    gameStage.dispose();
                }
                if(click == JOptionPane.CANCEL_OPTION)
                {
                    pause = false;
                }
            }
        });
    }



    private void onClickDraw(Graphics g)
    {
        buyNormalTowerButton.onClickDraw(g);
        buySniperTowerButton.onClickDraw(g);
        buyAntiTankTowerButton.onClickDraw(g);
        buyMissileTowerButton.onClickDraw(g);
        buyMachineGunTowerButton.onClickDraw(g);
    }

    public BuyTowerButton getBuyAntiTankTowerButton() {
        return buyAntiTankTowerButton;
    }

    public BuyTowerButton getBuyNormalTowerButton() {
        return buyNormalTowerButton;
    }

    public BuyTowerButton getBuySniperTowerButton() {
        return buySniperTowerButton;
    }

    public BuyTowerButton getBuyMachineGunTowerButton() {
        return buyMachineGunTowerButton;
    }

    public BuyTowerButton getBuyMissileTowerButton() {
        return buyMissileTowerButton;
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
        onClickDraw(g);
        bunchOfTower.drawBullet(g);
        bunchOfTower.draw(g);
        bunchOfEnemy.draw(g);
    }

    @Override
    public void run() {
        bunchOfEnemy.setSleepTime(System.currentTimeMillis());
        bunchOfTower.setSleepTime(System.currentTimeMillis());
        long spawnTime = System.currentTimeMillis();
        bunchOfEnemy.add(new NormalEnemy(map));
        while (true)
        {
            if(!pause)
            {
                if(System.currentTimeMillis() > spawnTime + 2000)
                {
                    bunchOfEnemy.add(new NormalEnemy(map));
                    spawnTime = System.currentTimeMillis();
                }
                bunchOfEnemy.onAction();
                bunchOfTower.onAction(bunchOfEnemy);
                repaint();
                Toolkit.getDefaultToolkit().sync();
            }
            else {
                bunchOfEnemy.setSleepTime(System.currentTimeMillis());
                bunchOfTower.setSleepTime(System.currentTimeMillis());
                spawnTime = System.currentTimeMillis();
            }
        }
    }


    @Override
    public void addNotify() {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }
}