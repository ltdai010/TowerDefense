package Console;

import PortableEntity.GameEntity;

import javax.swing.*;
import java.awt.*;

public class HealthBar implements GameEntity {
    private Image redBar;
    private Image greenBar;
    private int locationX;
    private int locationY;
    private double sizeX;
    private double maxSizeX;
    private double sizeY;
    public HealthBar(int locationX, int locationY, int sizeX, int sizeY)
    {
        this.locationX = locationX;
        this.locationY = locationY;
        this.sizeX = sizeX;
        this.maxSizeX = sizeX;
        this.sizeY = sizeY;
        loadImage();
    }

    public void decreaseGreenBar(double percent)
    {
        if(maxSizeX*percent >= 1)
        {
            this.sizeX = maxSizeX*percent;
            ImageIcon ii = new ImageIcon(greenBar);
            Image image= ii.getImage().getScaledInstance((int)sizeX, (int)sizeY,
                    Image.SCALE_SMOOTH);
            ii = new ImageIcon(image);
            greenBar = ii.getImage();
        }
    }
    @Override
    public void loadImage() {
        ImageIcon ii = new ImageIcon("src\\img\\green_healthbar.png");
        Image image= ii.getImage().getScaledInstance((int)sizeX, (int)sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        greenBar = ii.getImage();
        ii = new ImageIcon("src\\img\\red_healthbar.png");
        image= ii.getImage().getScaledInstance((int)sizeX, (int)sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        redBar = ii.getImage();
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public int getLocationX() {
        return locationX;
    }

    @Override
    public int getLocationY() {
        return locationY;
    }

    @Override
    public int getSizeX() {
        return (int)sizeX;
    }

    @Override
    public int getSizeY() {
        return (int)sizeY;
    }

    @Override
    public void setLocation(int x, int y) {
        this.locationX = x;
        this.locationY = y;
    }

    public void setSize(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
    }

    public void draw(Graphics g)
    {
        g.drawImage(redBar, locationX,locationY, null);
        g.drawImage(greenBar, locationX, locationY, null);
    }
}
