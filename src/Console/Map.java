package Console;

import Bunch.BunchOfGround;
import Bunch.BunchOfMountain;
import Bunch.BunchOfRoad;

import java.awt.*;

public class Map {
    private int[][] map = new int[12][24];
    private BunchOfRoad bunchOfRoad;
    private BunchOfGround bunchOfGround;
    private BunchOfMountain bunchOfMountain;
    public static final int GROUND = 0;
    public static final int ROAD = 1;
    public static final int TOWER = 2;
    public static final int MOUNTAIN = 3;
    public static final int SPAWNER = 5;
    public static final int TARGET = 4;
    public Map()
    {
        for(int i = 0; i < 12; ++i)
        {
            for (int j = 0; j < 24; ++j)
            {
                map[i][j] = GROUND;
            }
        }
        bunchOfMountain = new BunchOfMountain(this);
        bunchOfRoad = new BunchOfRoad(this);
        bunchOfGround = new BunchOfGround(this);
    }
    public void addObject(int i, int j, int Object)
    {
        map[i][j] = Object;
    }
    public int getObject(int i, int j)
    {
        return map[i][j];
    }

    public int[][] getMap() {
        return map;
    }

    public BunchOfGround getBunchOfGround() {
        return bunchOfGround;
    }

    public BunchOfRoad getBunchOfRoad() {
        return bunchOfRoad;
    }


    public void draw(Graphics g)
    {
        bunchOfGround.draw(g);
        bunchOfMountain.draw(g);
        bunchOfRoad.draw(g);
    }
}
