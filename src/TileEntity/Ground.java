package TileEntity;

import javax.swing.*;
import java.awt.*;

public class Ground implements GameTileEntity {
    private int locationX;
    private int locationY;
    private static final int sizeX = 50;
    private static final int sizeY = 50;
    private Image groundImage;
    private int type;
    public static final int up_left_road = 7;
    public static final int up_road = 8;
    public static final int up_right_road = 9;
    public static final int left_road = 4;
    public static final int right_road = 6;
    public static final int low_left_road = 1;
    public static final int low_road = 2;
    public static final int low_right_road = 3;
    public static final int ground = 5;
    public static final int up_left_edge = 10;
    public static final int up_right_edge = 11;
    public static final int low_left_edge = 12;
    public static final int low_right_edge = 13;

    public Ground(int locationX, int locationY, int type)
    {
        this.locationX = locationX;
        this.locationY = locationY;
        this.type = type;
        loadImage();
    }

    @Override
    public void loadImage() {
        String path = "src\\img\\ground.png";
        switch (type)
        {
            case ground:
                path = "src\\img\\ground.png";
                break;
            case low_left_road:
                path = "src\\img\\low-left-road.png";
                break;
            case low_road:
                path ="src\\img\\low-road.png";
                break;
            case low_right_road:
                path ="src\\img\\low-right-road.png";
                break;
            case left_road:
                path ="src\\img\\left-road.png";
                break;
            case right_road:
                path ="src\\img\\right-road.png";
                break;
            case up_left_road:
                path ="src\\img\\up-left-road.png";
                break;
            case up_road:
                path ="src\\img\\up-road.png";
                break;
            case up_right_road:
                path ="src\\img\\up-right-road.png";
                break;
            case up_left_edge:
                path = "src\\img\\up-left-edge.png";
                break;
            case up_right_edge:
                path ="src\\img\\up-right-edge.png";
                break;
            case low_left_edge:
                path ="src\\img\\low-left-edge.png";
                break;
            case low_right_edge:
                path ="src\\img\\low-right-edge.png";
                break;
            default:
                break;
        }
        ImageIcon ii = new ImageIcon(path);
        Image image= ii.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        groundImage = ii.getImage();
    }

    @Override
    public Image getImage() {
        return groundImage;
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
