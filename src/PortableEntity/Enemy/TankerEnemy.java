package PortableEntity.Enemy;

import Console.Map;

import javax.swing.*;
import java.awt.*;

public class TankerEnemy extends Enemy {
    private static final double MAXHP = 50;
    private static final int damage = 10;
    private static final double defaultArmor = 2;
    private static final int speed = 15;
    private static final int sizeX = 50;
    private static final int sizeY = 50;
    private static final int prize = 50;

    public TankerEnemy(Map map) {
        super(map);
        loadImage();
    }



    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getPrize() {
        return prize;
    }

    @Override
    public double getDefaultArmor() {
        return defaultArmor;
    }

    @Override
    public double getMaxHP() {
        return MAXHP;
    }

    @Override
    public void loadImage()
    {
        ImageIcon ii = new ImageIcon("src\\img\\tanker_enemy.png");
        Image image = ii.getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        enemy = ii.getImage();
        rotatedEnemy = ii.getImage();
    }

    @Override
    public String toString() {
        return "TE ";
    }
}
