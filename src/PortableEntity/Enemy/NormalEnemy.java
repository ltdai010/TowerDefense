package PortableEntity.Enemy;

import Console.Map;

import javax.swing.*;
import java.awt.*;

public class NormalEnemy extends Enemy {
    private static final double MAXHP = 20;
    private static final int damage = 5;
    private static final int speed = 10;
    private static final double defaultArmor = 1;
    private static final int sizeX = 60;
    private static final int sizeY = 60;
    private static final int prize = 10;

    public NormalEnemy(Map map) {
        super(map);
        loadImage();
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
    public int getPrize() {
        return prize;
    }

    @Override
    public void loadImage()
    {
        ImageIcon ii = new ImageIcon("src\\img\\normal_enemy.png");
        Image image = ii.getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        enemy = ii.getImage();
        rotatedEnemy = ii.getImage();
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public double getMaxHP() {
        return MAXHP;
    }

    @Override
    public double getDefaultArmor() {
        return defaultArmor;
    }

    @Override
    public  int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "NE ";
    }
}
