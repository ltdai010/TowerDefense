package PortableEntity.Bullet;

import PortableEntity.Enemy.Enemy;
import PortableEntity.GameEntity;

import javax.swing.*;
import java.awt.*;

public abstract class Bullet implements GameEntity {
    protected Image bullet;
    protected int damage;
    protected int locationX;
    protected int locationY;

    public Bullet(int locationX, int locationY)
    {
        setLocation(locationX, locationY);
    }


    public int getDamage() {
        return damage;
    }

    @Override
    public void setLocation(int x, int y)
    {
        this.locationX = x;
        this.locationY = y;
    }



    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public Image getImage()
    {
        return bullet;
    }

    public abstract boolean move(Enemy enemy);
}
