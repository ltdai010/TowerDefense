package TileEntity;

import java.awt.*;

public class Spawner implements GameTileEntity {
    private Point point;
    public Spawner(int locationX, int locationY) {
        point = new Point(locationX, locationY);
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
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
