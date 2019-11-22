package TileEntity;

import javax.swing.*;
import java.awt.*;

public class Rock implements GameTileEntity{
    private int locationX;
    private int locationY;
    private static final int sizeX = 50;
    private static final int sizeY = 50;
    private Image rockImage;
    private int type;
    public static final int big_rock = 0;
    public static final int small_rock = 1;
    public static final int tiny_rock = 2;
    public Rock (int locationX, int locationY, int type)
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
            case big_rock:
                path = "src\\img\\big-rock.png";
                break;
            case small_rock:
                path = "src\\img\\small-rock.png";
                break;
            case tiny_rock:
                path = "src\\img\\tiny-rock.png";
                break;
            default:
                break;
        }
        ImageIcon ii = new ImageIcon(path);
        Image image= ii.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        ii=new ImageIcon(image);
        rockImage = ii.getImage();
    }

    @Override
    public Image getImage() {
        return rockImage;
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
