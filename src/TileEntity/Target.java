package TileEntity;

import Console.HealthBar;
import PortableEntity.GameEntity;

import javax.swing.*;
import java.awt.*;

public class Target implements GameTileEntity {
    private Point point;
    public static final int MaxHP = 100;
    private int HP;
    private JLabel HP_label;
    public static final int HB_width = 300;
    public static final int HB_height = 40;
    private HealthBar healthBar;
    public Target(int locationX, int locationY)
    {
        point = new Point(locationX, locationY);
        HP = MaxHP;
        healthBar = new HealthBar(GameEntity.SCREENWIDTH - HB_width, 520, HB_width, HB_height);
        HP_label = new JLabel(HP + "/" + MaxHP);
        HP_label.setFont(new Font(Font.MONOSPACED, Font.ITALIC + Font.BOLD, 24));
        HP_label.setBounds(GameEntity.SCREENWIDTH - HB_width + 100, 520 + HB_height, 150, 30);
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void decreaseHP(int HP) {
        this.HP -= HP;
        HP_label.setText(this.HP + "/" + MaxHP);
        healthBar.decreaseGreenBar((double)this.HP/(double)MaxHP);
        healthBar.getGreenBar().getWidth(null);
    }

    public JLabel getHP_label() {
        return HP_label;
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
