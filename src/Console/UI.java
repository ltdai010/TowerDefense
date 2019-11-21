package Console;

import Button.*;
import PortableEntity.Bullet.*;
import PortableEntity.GameEntity;
import Screen.GameField;
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
    private JButton quit;
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
        information = new JTextArea();
        information.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        information.setBounds(0, 520, 400, 75);
        this.gameField.add(information);
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
        }
        else if(mouseX >= buyMachineGunTowerButton.getLocationX() && mouseX <= buyMachineGunTowerButton.getLocationX() + buyMachineGunTowerButton.getSizeX()
                && mouseY >= buyMachineGunTowerButton.getLocationY() && mouseY <= buyMachineGunTowerButton.getLocationY() + buyMachineGunTowerButton.getSizeY())
        {
            double a = (double) Math.round((double)100000/MachineGunTower.fireRate);
            a = a/100;
            information.setText("AD:"+ MachineGunBullet.damage+"\nAS:" + a + "/s\nRange:"+ MachineGunTower.RANGE);
        }
        else if(mouseX >= buyMissileTowerButton.getLocationX() && mouseX <= buyMissileTowerButton.getLocationX() + buyMissileTowerButton.getSizeX()
                && mouseY >= buyMissileTowerButton.getLocationY() && mouseY <= buyMissileTowerButton.getLocationY() + buyMissileTowerButton.getSizeY())
        {
            double a = (double) Math.round((double)100000/MissileTower.fireRate);
            a = a/100;
            information.setText("AD:"+ Missile.damage + "\nAS:" + a + "/s\nRange:"+ MissileTower.RANGE);
        }
        else if(mouseX >= buyNormalTowerButton.getLocationX() && mouseX <= buyNormalTowerButton.getLocationX() + buyNormalTowerButton.getSizeX()
                && mouseY >= buyNormalTowerButton.getLocationY() && mouseY <= buyNormalTowerButton.getLocationY() + buyNormalTowerButton.getSizeY())
        {
            double a = (double) Math.round((double)100000/NormalTower.fireRate);
            a = a/100;
            information.setText("AD:"+ NormalBullet.damage + "\nAS:" + a + "/s\nRange:" + NormalTower.RANGE);
        }
        else if(mouseX >= buySniperTowerButton.getLocationX() && mouseX <= buySniperTowerButton.getLocationX() + buySniperTowerButton.getSizeX()
                && mouseY >= buySniperTowerButton.getLocationY() && mouseY <= buySniperTowerButton.getLocationY() + buySniperTowerButton.getSizeY())
        {
            double a = (double) Math.round((double)100000/SniperTower.fireRate);
            a = a/100;
            information.setText("AD:"+ SniperBullet.damage + "\nAS:" + a + "/s\nRange:" + SniperTower.RANGE);
        }
        else
        {
            information.setText("");
        }
    }

    private void initButton()
    {
        buyNormalTowerButton = new BuyNormalTowerButton(GameEntity.SCREENWIDTH/2 - 200,525, gameField.getBunchOfTower(), gameField, gameField.getMap());
        buySniperTowerButton = new BuySniperTowerButton(GameEntity.SCREENWIDTH/2 - 100,525 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        buyAntiTankTowerButton = new BuyAnitiTankTowerButton(GameEntity.SCREENWIDTH/2 ,525 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        buyMachineGunTowerButton = new BuyMachineGunTowerButton(GameEntity.SCREENWIDTH/2 + 100,525 , gameField.getBunchOfTower(), gameField, gameField.getMap());
        buyMissileTowerButton = new BuyMissileTowerButton(GameEntity.SCREENWIDTH/2 + 200,525 , gameField.getBunchOfTower(), gameField, gameField.getMap());
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
        sellTowerButton = new SellTowerButton(GameEntity.SCREENWIDTH/2 + 300, 525, gameField.getBunchOfTower(),gameField, gameField.getMap());
        gameField.add(sellTowerButton);
        gameField.addMouseListener(sellTowerButton);
        quit = new JButton("=");
        quit.setBackground(Color.WHITE);
        quit.setBorder(null);
        quit.setBounds(1100, 0, 50,50);
        gameField.add(quit);
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameField.setPause(true);
                int click = JOptionPane.showConfirmDialog(null, "Save Game?");
                if(click == JOptionPane.YES_OPTION)
                {
                    Menu menu = new Menu();
                    gameField.getPlayer().saveGame();
                    gameField.getGameStage().setVisible(false);
                    gameField.getGameStage().dispose();
                }
                if(click == JOptionPane.NO_OPTION)
                {
                    Menu menu = new Menu();
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
