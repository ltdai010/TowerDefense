package TileEntity;

import javax.swing.*;
import java.awt.*;

public class Tree implements GameTileEntity{
    private int locationX;
    private int locationY;
    private static final int sizeX = 50;
    private static final int sizeY = 50;
    private Image treeImage;
    private int type;
    public static final int big_tree = 0;
    public static final int small_tree = 1;
    public Tree (int locationX, int locationY, int type)
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
            case big_tree:
                path = "src\\img\\big-tree.png";
                break;
            case small_tree:
                path = "src\\img\\small-tree.png";
                break;
            default:
                break;
        }
        ImageIcon ii = new ImageIcon(path);
        Image image= ii.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        treeImage = ii.getImage();
    }

    @Override
    public Image getImage() {
        return treeImage;
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
