package Console;

import Button.*;
import PortableEntity.Bullet.*;
import PortableEntity.GameEntity;
import Screen.GameField;
import TileEntity.Target;
import TileEntity.Tower.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UI {
    private BuyTowerButton buyNormalTowerButton;
    private BuyTowerButton buySniperTowerButton;
    private BuyTowerButton buyAntiTankTowerButton;
    private BuyTowerButton buyMachineGunTowerButton;
    private BuyTowerButton buyMissileTowerButton;
    private ControlButton back_menu;
    private ControlButton leaderBoard;
    private SellTowerButton sellTowerButton;
    private ControlButton quit;
    private JLabel coin_icon;
    private JLabel stage_label;
    private JLabel change_stage;
    private JLabel store_icon;
    private JLabel result;
    private JLabel result_score;
    private JLabel result_bestScore;
    private ImageIcon coin;
    private JTextArea information;
    private GameField gameField;

    public UI(GameField gameField)
    {
        this.gameField = gameField;
        try {
            initUI();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() throws FileNotFoundException {
        initButton();
        initText();
    }

    private void initText() throws FileNotFoundException {
        result_score = new JLabel(Integer.toString(gameField.getPlayer().getScore()));
        result_score.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        result_score.setBounds(500, 290, 200, 50);
        result_score.setHorizontalAlignment(SwingConstants.CENTER);
        result_score.setVisible(false);
        gameField.add(result_score);
        result_bestScore = new JLabel(Integer.toString(bestScore(new File("src\\save\\score"))));
        result_bestScore.setBounds(500, 340, 200, 50);
        result_bestScore.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        result_bestScore.setHorizontalAlignment(SwingConstants.CENTER);
        result_bestScore.setVisible(false);
        gameField.add(result_bestScore);
        result = new JLabel();
        result.setBounds(400, 100 , 400, 400);
        gameField.add(result);
        result.setVisible(false);
        change_stage = new JLabel();
        change_stage.setBounds(550, 250, 100, 100);
        gameField.add(change_stage);
        stage_label = new JLabel("Stage " + gameField.getStage());
        stage_label.setBounds(Player.AVATAR_SIZE,50, 200, 50);
        stage_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 26));
        information = new JTextArea();
        information.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        information.setBackground(Color.ORANGE);
        coin = new ImageIcon("src\\img\\coins.png");
        Image temp_coin = coin.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        coin = new ImageIcon(temp_coin);
        coin_icon = new JLabel("", coin, JLabel.CENTER);
        coin_icon.setBounds(960,10,30,30);
        ImageIcon icon = new ImageIcon("src\\img\\store_icon.png");
        Image img = icon.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        store_icon = new JLabel("", icon, JLabel.CENTER);
        store_icon.setBounds(150,525, 150, 50);
        this.gameField.add(stage_label);
        this.gameField.add(coin_icon);
        this.gameField.add(information);
        this.gameField.add(store_icon);
    }

    public int bestScore(File folder) throws FileNotFoundException {
        ArrayList<Player> arrayList = new ArrayList<>();
        int max = 0;
        for (File fileEntry : folder.listFiles()) {
            Scanner scanner = new Scanner(fileEntry);
            scanner.nextInt();
            int tmp = scanner.nextInt();
            if(tmp > max)
            {
                max = tmp;
            }
        }
        return max;
    }

    public void loadResult(String result) {
        ImageIcon ii = new ImageIcon("src\\img\\" + result + ".png");
        Image img = ii.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        ii = new ImageIcon(img);
        this.result.setIcon(ii);
        this.result.setVisible(true);
        this.result_score.setVisible(true);
        this.result_bestScore.setVisible(true);
    }

    public JTextArea getInformation() {
        return information;
    }

    public JLabel getStage_label() {
        return stage_label;
    }

    public void setInformationText()
    {
        int mouseX = (int)MouseInfo.getPointerInfo().getLocation().getX() - (int)gameField.getLocationOnScreen().getX();
        int mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY() - (int)gameField.getLocationOnScreen().getY();
        if(mouseX >= buyAntiTankTowerButton.getLocationX() && mouseX <= buyAntiTankTowerButton.getLocationX() + buyAntiTankTowerButton.getSizeX()
                && mouseY >= buyAntiTankTowerButton.getLocationY() && mouseY <= buyAntiTankTowerButton.getLocationY() + buyAntiTankTowerButton.getSizeY())
        {
            double a = (double) Math.round((double)100000/AntiTankTower.fireRate);
            a = a/100;
            information.setText("AD:"+ AntiTankBullet.damage+"\nAS:" + a + "/s\nRange:"+AntiTankTower.RANGE + "\nDecrease half of the enemy's armor");
            information.setBounds(buyAntiTankTowerButton.getLocationX(), buyAntiTankTowerButton.getLocationY() - 75, 250, 75);
            information.setVisible(true);
        }
        else if(mouseX >= buyMachineGunTowerButton.getLocationX() && mouseX <= buyMachineGunTowerButton.getLocationX() + buyMachineGunTowerButton.getSizeX()
                && mouseY >= buyMachineGunTowerButton.getLocationY() && mouseY <= buyMachineGunTowerButton.getLocationY() + buyMachineGunTowerButton.getSizeY())
        {
            double a = (double) Math.round((double)100000/MachineGunTower.fireRate);
            a = a/100;
            information.setText("AD:"+ MachineGunBullet.damage+"\nAS:" + a + "/s\nRange:"+ MachineGunTower.RANGE);
            information.setBounds(buyMachineGunTowerButton.getLocationX(), buyMachineGunTowerButton.getLocationY() - 75, 250, 75);
            information.setVisible(true);
        }
        else if(mouseX >= buyMissileTowerButton.getLocationX() && mouseX <= buyMissileTowerButton.getLocationX() + buyMissileTowerButton.getSizeX()
                && mouseY >= buyMissileTowerButton.getLocationY() && mouseY <= buyMissileTowerButton.getLocationY() + buyMissileTowerButton.getSizeY())
        {
            double a = (double) Math.round((double)100000/MissileTower.fireRate);
            a = a/100;
            information.setText("AD:"+ Missile.damage + "\nAS:" + a + "/s\nRange:"+ MissileTower.RANGE);
            information.setBounds(buyMissileTowerButton.getLocationX(), buyMissileTowerButton.getLocationY() - 75, 250, 75);
            information.setVisible(true);
        }
        else if(mouseX >= buyNormalTowerButton.getLocationX() && mouseX <= buyNormalTowerButton.getLocationX() + buyNormalTowerButton.getSizeX()
                && mouseY >= buyNormalTowerButton.getLocationY() && mouseY <= buyNormalTowerButton.getLocationY() + buyNormalTowerButton.getSizeY())
        {
            double a = (double) Math.round((double)100000/NormalTower.fireRate);
            a = a/100;
            information.setText("AD:"+ NormalBullet.damage + "\nAS:" + a + "/s\nRange:" + NormalTower.RANGE);
            information.setBounds(buyNormalTowerButton.getLocationX(), buyNormalTowerButton.getLocationY() - 75, 250, 75);
            information.setVisible(true);
        }
        else if(mouseX >= buySniperTowerButton.getLocationX() && mouseX <= buySniperTowerButton.getLocationX() + buySniperTowerButton.getSizeX()
                && mouseY >= buySniperTowerButton.getLocationY() && mouseY <= buySniperTowerButton.getLocationY() + buySniperTowerButton.getSizeY())
        {
            double a = (double) Math.round((double)100000/SniperTower.fireRate);
            a = a/100;
            information.setText("AD:"+ SniperBullet.damage + "\nAS:" + a + "/s\nRange:" + SniperTower.RANGE);
            information.setBounds(buySniperTowerButton.getLocationX(), buySniperTowerButton.getLocationY() - 75, 250, 75);
            information.setVisible(true);
        }
        else
        {
            information.setText("");
            information.setVisible(false);
        }
    }

    private void initButton()
    {
        buyNormalTowerButton = new BuyNormalTowerButton(GameEntity.SCREENWIDTH - 600 - Target.HB_width,515, gameField.getBunchOfTower(), gameField, gameField.getMap());
        buySniperTowerButton = new BuySniperTowerButton(GameEntity.SCREENWIDTH - 500 - Target.HB_width,515 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        buyAntiTankTowerButton = new BuyAnitiTankTowerButton(GameEntity.SCREENWIDTH - 400 - Target.HB_width,515 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        buyMachineGunTowerButton = new BuyMachineGunTowerButton(GameEntity.SCREENWIDTH - 300 - Target.HB_width,515 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        buyMissileTowerButton = new BuyMissileTowerButton(GameEntity.SCREENWIDTH - 200 - Target.HB_width,515 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        gameField.add(buyNormalTowerButton);
        gameField.add(buySniperTowerButton);
        gameField.add(buyAntiTankTowerButton);
        gameField.add(buyMachineGunTowerButton);
        gameField.add(buyMissileTowerButton);
        gameField.addMouseListener(buyNormalTowerButton);
        gameField.addMouseListener(buySniperTowerButton);
        gameField.addMouseListener(buyAntiTankTowerButton);
        gameField.addMouseListener(buyMachineGunTowerButton);
        gameField.addMouseListener(buyMissileTowerButton);
        sellTowerButton = new SellTowerButton(GameEntity.SCREENWIDTH - 100 - Target.HB_width, 515, gameField.getBunchOfTower(),gameField, gameField.getMap());
        gameField.add(sellTowerButton);
        gameField.addMouseListener(sellTowerButton);
        quit = new ControlButton("src\\img\\close_2.png", "src\\img\\roll-close_2.png", 40, 40);
        quit.setBounds(1120, 10, 40, 40);
        gameField.add(quit);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameField.setPause(true);
                int click = JOptionPane.showConfirmDialog(null, "The game has automatically saved, quit game?");
                if(click == JOptionPane.YES_OPTION)
                {
                    Menu menu = new Menu();
                    gameField.getClip().stop();
                    gameField.getClip().close();
                    gameField.getGameStage().setVisible(false);
                    gameField.getGameStage().dispose();
                }
                if(click == JOptionPane.NO_OPTION)
                {
                    gameField.setPause(false);
                }
                if(click == JOptionPane.CANCEL_OPTION)
                {
                    gameField.setPause(false);
                }
                if(click == JOptionPane.CLOSED_OPTION)
                {
                    gameField.setPause(false);
                }
            }
        });
    }
    public void onClickDraw(Graphics g)
    {
        buyNormalTowerButton.onClickDraw(g);
        buySniperTowerButton.onClickDraw(g);
        buyAntiTankTowerButton.onClickDraw(g);
        buyMissileTowerButton.onClickDraw(g);
        buyMachineGunTowerButton.onClickDraw(g);
        sellTowerButton.onClickDraw(g);
    }


    public void drawStage(int stage) throws InterruptedException {
        change_stage.setVisible(true);
        ImageIcon button = new ImageIcon("src\\img\\"+ stage +".png");
        Image image= button.getImage().getScaledInstance(100, 100,
                Image.SCALE_SMOOTH);
        change_stage.setIcon(new ImageIcon(image));
        Thread.sleep(5000);
        change_stage.setVisible(false);
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

    public SellTowerButton getSellTowerButton() {
        return sellTowerButton;
    }
}
