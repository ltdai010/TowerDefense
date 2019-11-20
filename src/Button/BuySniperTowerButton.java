package Button;

import Bunch.BunchOfTower;
import Console.Map;
import PortableEntity.Bullet.SniperBullet;
import Screen.GameField;
import TileEntity.Tower.NormalTower;
import TileEntity.Tower.SniperTower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BuySniperTowerButton extends BuyTowerButton {


    public BuySniperTowerButton(int locationX, int locationY, BunchOfTower bunch, GameField gameField, Map map) {
        super(locationX, locationY, bunch, gameField, map);
    }

    @Override
    public  void add()
    {
        bunchOfTower.add((new SniperTower(imageLocationX,imageLocationY, gameField.getPlayer())));
    }

    @Override
    public void loadImage() {
        ImageIcon button = new ImageIcon("src\\img\\sniper_turret.png");
        Image image= button.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        this.buttonIcon = new ImageIcon(image);
    }

    @Override
    public boolean enoughMoney()
    {
        if(gameField.getPlayer().getScore() >= SniperTower.price)
        {
            gameField.getPlayer().addScore(-SniperTower.price);
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(clicked == true)
        {
            gameField.getBuyAntiTankTowerButton().setClicked(false);
            gameField.getBuyNormalTowerButton().setClicked(false);
            gameField.getBuyMissileTowerButton().setClicked(false);
            gameField.getBuyMachineGunTowerButton().setClicked(false);
        }
    }
}
