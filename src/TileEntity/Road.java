package TileEntity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Road implements GameTileEntity {
    private int startPointX = 0;
    private int startPointY = SCREENHEIGHT/2;
    private Image road;
    private int locationX;
    private int locationY;
    private static final int sizeX = 50;
    private static final int sizeY = 50;

    public Road(int locationX, int locationY)
    {
        setLocation(locationX, locationY);
        loadImage();
    }

    @Override
    public void loadImage()
    {
        ImageIcon ii = new ImageIcon("src\\img\\road.png");
        Image image= ii.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        road = ii.getImage();
    }

    @Override
    public int getLocationY() {
        return locationY;
    }

    @Override
    public int getLocationX() {
        return locationX;
    }

    @Override
    public Image getImage()
    {
        return road;
    }

    public int getSizeY() {
        return sizeY;
    }

    public int getSizeX() {
        return sizeX;
    }

    @Override
    public void setLocation(int x, int y)
    {
        this.locationX = x;
        this.locationY = y;
    }

    public void draw(Graphics g)
    {
        g.drawImage(road, locationX, locationY, null);
    }
}

