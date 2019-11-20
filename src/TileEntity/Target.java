package TileEntity;

import Console.HealthBar;

import java.awt.*;

public class Target extends Road{
    private Point point;
    public static final int MaxHP = 100;
    private int HP;
    private HealthBar healthBar;
    public Target(int locationX, int locationY) {
        super(locationX, locationY);
        point = new Point(locationX, locationY);
        HP = MaxHP;
        healthBar = new HealthBar(this.getLocationX(), this.getLocationY(), this.getSizeX(), 5);
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
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        healthBar.draw(g);
    }
}
