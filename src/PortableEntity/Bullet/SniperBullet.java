package PortableEntity.Bullet;

import PortableEntity.Enemy.Enemy;
import TileEntity.Tower.SniperTower;

import javax.swing.*;
import java.awt.*;

public class SniperBullet extends Bullet {
    public static final int damage = 5;
    public static final int speed = 6;
    public static final int sizeX = 20;
    public static final int sizeY = 20;

    public SniperBullet(int locationX, int locationY, double angle) {
        super(locationX, locationY);
        super.damage = SniperBullet.damage;
        loadImage();
        bullet = rotateImageByDegrees(toBufferedImage(bullet), angle);
    }

    @Override
    public void loadImage()
    {
        ImageIcon ii = new ImageIcon("src\\img\\sniper_bullet.png");
        Image image= ii.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        bullet = ii.getImage();
    }

    @Override
    public int getSizeX() {
        return sizeX;
    }

    @Override
    public int getSizeY() {
        return sizeY;
    }

    @Override
    public boolean move(Enemy enemy)
    {
        double deltaX = enemy.getPoint().getX() - locationX;
        double deltaY = enemy.getPoint().getY() - locationY;
        if(Math.abs(deltaX) < enemy.getSizeX()/2 && Math.abs(deltaY) < enemy.getSizeY()/2)
        {
            return false;
        }
        if(deltaX == 0 && deltaY == 0)
        {
            return false;
        }
        else if(deltaX == 0)
        {
            deltaY = speed;
        }
        else if(deltaY == 0)
        {
            deltaX = speed;
        }
        else
        {
            double k = Math.abs(deltaY / deltaX);
            deltaX = Math.sqrt(speed*speed / (k * k + 1));
            deltaY = (deltaX * k);
        }
        if(locationX < enemy.getPoint().getX())
        {
            locationX += deltaX;
        }
        if(locationX > enemy.getPoint().getX())
        {
            locationX -= deltaX;
        }
        if(locationY < enemy.getPoint().getY())
        {
            locationY += deltaY;
        }
        if(locationY > enemy.getPoint().getY())
        {
            locationY -= deltaY;
        }
        return true;
    }
}
