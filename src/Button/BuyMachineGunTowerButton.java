package Button;

import Bunch.BunchOfTower;
import Console.Map;
import Screen.GameField;
import TileEntity.Tower.MachineGunTower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BuyMachineGunTowerButton extends BuyTowerButton {
    public BuyMachineGunTowerButton(int locationX, int locationY, BunchOfTower bunch, GameField gameField, Map map) {
        super(locationX, locationY, bunch, gameField, map);
    }

    @Override
    public  void add()
    {
        bunchOfTower.add((new MachineGunTower(imageLocationX,imageLocationY, gameField.getPlayer())));
    }
    @Override
    public void loadImage() {
        ImageIcon ii = new ImageIcon("src\\img\\machinegun_turret.png");
        Image image= ii.getImage().getScaledInstance(MachineGunTower.sizeX, MachineGunTower.sizeY,
                Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        buttonImage = ii.getImage();
    }
    @Override
    public void loadImageIcon() {
        ImageIcon button = new ImageIcon("src\\img\\machinegun-buy-button.png");
        Image image= button.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        this.buttonIcon = new ImageIcon(image);
    }

    @Override
    public boolean enoughMoney()
    {
        if(gameField.getPlayer().getCoin() >= MachineGunTower.price)
        {
            map.addObject(imageLocationY/50, imageLocationX/50, Map.TOWER);
            gameField.getPlayer().addCoin(-MachineGunTower.price);
            return true;
        }
        return false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(clicked == true)
        {
            gameField.getUi().getBuyNormalTowerButton().setClicked(false);
            gameField.getUi().getBuySniperTowerButton().setClicked(false);
            gameField.getUi().getBuyAntiTankTowerButton().setClicked(false);
            gameField.getUi().getBuyMissileTowerButton().setClicked(false);
            gameField.getUi().getSellTowerButton().setClicked(false);

        }
    }
}
