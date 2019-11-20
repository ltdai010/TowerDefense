package TileEntity;

import java.awt.*;

public class Spawner extends Road {
    private Point point;
    public Spawner(int locationX, int locationY) {
        super(locationX, locationY);
        point = new Point(locationX, locationY);
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }
}
