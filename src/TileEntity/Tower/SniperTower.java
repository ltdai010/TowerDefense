package TileEntity.Tower;

import Bunch.BunchOfEnemy;
import Console.Player;
import PortableEntity.Bullet.NormalBullet;
import PortableEntity.Bullet.SniperBullet;
import PortableEntity.Enemy.Enemy;

import javax.swing.*;
import java.awt.*;

public class SniperTower extends Tower {
    public static final int price = 90;
    private static final int sizeX = 50;
    private static final int sizeY = 50;
    private static final int RANGE = 400;
    private static final int fireRate = 1000;
    private static final int bulletSpeedRate = 2;
    public SniperTower(int locationX, int locationY, Player player) {
        super(locationX, locationY, player);
        loadImage();
    }

    @Override
    public void loadImage()
    {
        ImageIcon ii = new ImageIcon("src\\img\\sniper_turret.png");
        Image image= ii.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        turret = ii.getImage();
        rotatedTurret = ii.getImage();
    }

    @Override
    public void onAction(BunchOfEnemy bunch)
    {
        if(currentEnemy != null && inRange(currentEnemy) != true)
        {
            bunchOfBullet.removeAll();
        }
        if(currentEnemy == null)
        {
            bunchOfBullet.removeAll();
        }
        currentEnemy = null;
        for(Enemy enemy: bunch.getBunch())
        {
            if(inRange(enemy))
            {
                currentEnemy = enemy;
                break;
            }
        }
        if(System.currentTimeMillis() > sleepFire + fireRate  && currentEnemy != null)
        {
            fireBullet(currentEnemy);
            sleepFire = System.currentTimeMillis();
        }
        if(System.currentTimeMillis() > sleepBulletMove + bulletSpeedRate && currentEnemy != null)
        {
            bulletMove(currentEnemy);
            sleepBulletMove = System.currentTimeMillis();
        }
    }

    @Override
    public boolean inRange(Enemy enemy) {
        if((enemy.getLocationX() - locationX)*(enemy.getLocationX() - locationX) +
                (enemy.getLocationY() - locationY)*(enemy.getLocationY() - locationY) <= RANGE*RANGE)
        {
            return true;
        }
        return false;
    }

    @Override
    public void fireBullet(Enemy enemy) {
        bunchOfBullet.add(new SniperBullet(locationX + sizeX/2, locationY + sizeY/2, checkAngle(enemy)));
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString()
    {
        return "ST " + locationX + " " + locationY;
    }
}
