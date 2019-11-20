package Button;

import Bunch.*;
import Console.Map;
import Screen.GameField;
import TileEntity.Tower.NormalTower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BuyNormalTowerButton extends BuyTowerButton {


    public BuyNormalTowerButton(int locationX, int locationY, BunchOfTower bunch, GameField gameField, Map map) {
        super(locationX, locationY, bunch, gameField, map);
    }

    @Override
    public  void add()
    {
        bunchOfTower.add((new NormalTower(imageLocationX,imageLocationY, gameField.getPlayer())));
    }

    @Override
    public void loadImage() {
        ImageIcon button = new ImageIcon("src\\img\\normal_turret.png");
        Image image= button.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        this.buttonIcon = new ImageIcon(image);
    }

    @Override
    public boolean enoughMoney()
    {
        if(gameField.getPlayer().getScore() >= NormalTower.price)
        {
            gameField.getPlayer().addScore(-NormalTower.price);
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
            gameField.getBuySniperTowerButton().setClicked(false);
            gameField.getBuyMachineGunTowerButton().setClicked(false);
            gameField.getBuyMissileTowerButton().setClicked(false);
        }
    }
}
