package TileEntity;

import javax.swing.*;
import java.awt.*;

public class Glass implements GameTileEntity{
    private int locationX;
    private int locationY;
    private static final int sizeX = 70;
    private static final int sizeY = 70;
    private Image glassImage;
    private int type;
    public static final int glass = 0;
    public static final int wonderful_glass = 1;
    public Glass (int locationX, int locationY, int type)
    {
        this.locationX = locationX;
        this.locationY = locationY;
        this.type = type;
        loadImage();
    }

    @Override
    public void loadImage() {
        String path = "";
        switch (type)
        {
            case glass:
                path = "src\\img\\glass.png";
                break;
            case wonderful_glass:
                path = "src\\img\\wonderful-glass.png";
                break;
            default:
                break;
        }
        ImageIcon ii = new ImageIcon(path);
        Image image= ii.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        glassImage = ii.getImage();
    }

    @Override
    public Image getImage() {
        return glassImage;
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
        this.locationY = y;
    }
}
