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

public class UI {
    private BuyTowerButton buyNormalTowerButton;
    private BuyTowerButton buySniperTowerButton;
    private BuyTowerButton buyAntiTankTowerButton;
    private BuyTowerButton buyMachineGunTowerButton;
    private BuyTowerButton buyMissileTowerButton;
    private SellTowerButton sellTowerButton;
    private ControlButton quit;
    private JLabel coin_icon;
    private JLabel stage_label;
    private ImageIcon coin;
    private JTextArea information;
    private GameField gameField;

    public UI(GameField gameField)
    {
        this.gameField = gameField;
        initUI();
    }

    private void initUI()
    {
        initButton();
        stage_label = new JLabel("Stage " + gameField.getStage());
        stage_label.setBounds(20,50, 200, 50);
        stage_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 26));
        information = new JTextArea();
        information.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        information.setBounds(0, 520, 400, 75);
        information.setBackground(Color.ORANGE);
        coin = new ImageIcon("src\\img\\coins.png");
        Image temp_coin = coin.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);
        coin = new ImageIcon(temp_coin);
        coin_icon = new JLabel("", coin, JLabel.CENTER);
        coin_icon.setBounds(960,10,30,30);
        gameField.add(stage_label);
        this.gameField.add(coin_icon);
        this.gameField.add(information);
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
        buyNormalTowerButton = new BuyNormalTowerButton(GameEntity.SCREENWIDTH - 500 - 10 - Target.HB_width,515, gameField.getBunchOfTower(), gameField, gameField.getMap());
        buySniperTowerButton = new BuySniperTowerButton(GameEntity.SCREENWIDTH - 400 - 10 - Target.HB_width,515 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        buyAntiTankTowerButton = new BuyAnitiTankTowerButton(GameEntity.SCREENWIDTH - 300 - 10 - Target.HB_width,515 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        buyMachineGunTowerButton = new BuyMachineGunTowerButton(GameEntity.SCREENWIDTH - 200 - 10 - Target.HB_width,515 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        buyMissileTowerButton = new BuyMissileTowerButton(GameEntity.SCREENWIDTH + -100 - 10 - Target.HB_width,515 , gameField.getBunchOfTower(), gameField, gameField.getMap());
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
        sellTowerButton = new SellTowerButton(GameEntity.SCREENWIDTH - 10 - Target.HB_width, 515, gameField.getBunchOfTower(),gameField, gameField.getMap());
        gameField.add(sellTowerButton);
        gameField.addMouseListener(sellTowerButton);
        quit = new ControlButton("src\\img\\close_2.png", "src\\img\\roll-close_2.png", 40, 40);
        quit.setBounds(1120, 10, 40, 40);
        gameField.add(quit);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameField.setPause(true);
                int click = JOptionPane.showConfirmDialog(null, "Save Game?");
                if(click == JOptionPane.YES_OPTION)
                {
                    Menu menu = new Menu();
                    gameField.getClip().stop();
                    gameField.getClip().close();
                    gameField.getPlayer().saveGame();
                    gameField.getGameStage().setVisible(false);
                    gameField.getGameStage().dispose();
                }
                if(click == JOptionPane.NO_OPTION)
                {
                    Menu menu = new Menu();
                    gameField.getClip().stop();
                    gameField.getClip().close();
                    gameField.getPlayer().saveScore();
                    gameField.getGameStage().setVisible(false);
                    gameField.getGameStage().dispose();
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
