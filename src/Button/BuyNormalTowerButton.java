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
    public void loadImageIcon() {
        ImageIcon button = new ImageIcon("src\\img\\normal-buy-button.png");
        Image image= button.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        this.buttonIcon = new ImageIcon(image);
    }

    @Override
    public void loadImage() {
        ImageIcon ii = new ImageIcon("src\\img\\normal_turret.png");
        Image image= ii.getImage().getScaledInstance(NormalTower.sizeX, NormalTower.sizeY,
                Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        buttonImage = ii.getImage();
    }

    @Override
    public boolean enoughMoney()
    {
        if(gameField.getPlayer().getScore() >= NormalTower.price)
        {
            map.addObject(imageLocationY/50, imageLocationX/50, Map.TOWER);
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
            gameField.getUi().getBuyAntiTankTowerButton().setClicked(false);
            gameField.getUi().getBuySniperTowerButton().setClicked(false);
            gameField.getUi().getBuyMachineGunTowerButton().setClicked(false);
            gameField.getUi().getBuyMissileTowerButton().setClicked(false);
            gameField.getUi().getSellTowerButton().setClicked(false);

        }
    }
}
