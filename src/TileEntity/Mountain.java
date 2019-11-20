package TileEntity;

import javax.swing.*;
import java.awt.*;

public class Mountain implements GameTileEntity{
    private int locationX;
    private int locationY;
    private static final int sizeX = 50;
    private static final int sizeY = 50;
    private Image mountain;
    public Mountain(int locationX, int locationY)
    {
        this.locationX = locationX;
        this.locationY = locationY;
        loadImage();
    }
    @Override
    public void loadImage() {
        ImageIcon ii = new ImageIcon("src\\img\\mountain.png");
        Image image= ii.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        mountain = ii.getImage();
    }

    @Override
    public Image getImage() {
        return mountain;
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
        return sizeX;
    }

    @Override
    public int getSizeY() {
        return sizeY;
    }

    @Override
    public void setLocation(int x, int y) {
        this.locationX = x;
        this.locationY = x;
    }

}
