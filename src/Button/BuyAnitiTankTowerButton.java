package Button;

import Bunch.BunchOfTower;
import Console.Map;
import Screen.GameField;
import TileEntity.Tower.AntiTankTower;
import TileEntity.Tower.NormalTower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class BuyAnitiTankTowerButton extends BuyTowerButton {

    public BuyAnitiTankTowerButton(int locationX, int locationY, BunchOfTower bunch, GameField gameField, Map map) {
        super(locationX, locationY, bunch, gameField, map);
    }

    @Override
    public  void add()
    {
        bunchOfTower.add((new AntiTankTower(imageLocationX,imageLocationY, gameField.getPlayer())));
    }

    @Override
    public void loadImage() {
        ImageIcon button = new ImageIcon("src\\img\\antitank_turret.png");
        Image image= button.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        this.buttonIcon = new ImageIcon(image);
    }

    @Override
    public boolean enoughMoney()
    {
        if(gameField.getPlayer().getScore() >= AntiTankTower.price)
        {
            gameField.getPlayer().addScore(-AntiTankTower.price);
            return true;
        }
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if(clicked == true)
        {
            gameField.getBuyNormalTowerButton().setClicked(false);
            gameField.getBuySniperTowerButton().setClicked(false);
            gameField.getBuyMissileTowerButton().setClicked(false);
            gameField.getBuyMachineGunTowerButton().setClicked(false);
        }
    }
}
