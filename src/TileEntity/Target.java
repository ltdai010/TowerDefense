package TileEntity;

import Console.HealthBar;

import java.awt.*;

public class Target implements GameTileEntity {
    private Point point;
    public static final int MaxHP = 100;
    private int HP;
    private HealthBar healthBar;
    public Target(int locationX, int locationY)
    {
        point = new Point(locationX, locationY);
        HP = MaxHP;
        healthBar = new HealthBar(1050, 520, 150, 20);
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void decreaseHP(int HP) {
        this.HP -= HP;
        healthBar.decreaseGreenBar((double)this.HP/(double)MaxHP);
        healthBar.getGreenBar().getWidth(null);
    }

    public void draw(Graphics g)
    {
        healthBar.draw(g);
    }
    @Override
    public void loadImage() {

    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public int getLocationX() {
        return 0;
    }

    @Override
    public int getLocationY() {
        return 0;
    }

    @Override
    public int getSizeX() {
        return 0;
    }

    @Override
    public int getSizeY() {
        return 0;
    }

    @Override
    public void setLocation(int x, int y) {

    }
}
