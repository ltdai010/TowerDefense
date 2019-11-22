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
    public static final int BIG_TREE = 6;
    public static final int SMALL_TREE = 7;
    public static final int BIG_ROCK = 8;
    public static final int SMALL_ROCK = 9;
    public static final int TINY_ROCK = 10;
    public static final int GLASS = 11;
    public static final int WONDERFUL_GLASS = 12;
    private String mapNumber;
    public static final String MAP1 = "map1";
    public static final String MAP2 = "map2";
    public static final String MAP3 = "map3";
    public static final String MAP4 = "map4";
    public static final String MAP5 = "map5";
    public Map(String mapNumber)
    {
        for(int i = 0; i < 12; ++i)
        {
            for (int j = 0; j < 24; ++j)
            {
                map[i][j] = GROUND;
            }
        }
        this.mapNumber = mapNumber;
        bunchOfMountain = new BunchOfMountain(this, mapNumber);
        bunchOfRoad = new BunchOfRoad(this, mapNumber);
        bunchOfGround = new BunchOfGround(this);
        bunchOfTree = new BunchOfTree(this);
        bunchOfGlass = new BunchOfGlass(this);
        bunchOfRock = new BunchOfRock(this);
    }
    public void addObject(int i, int j, int Object)
    {
        map[i][j] = Object;
    }
    public int getObject(int i, int j)
    {
        return map[i][j];
    }

    public String getMapNumber() {
        return mapNumber;
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

    public BunchOfGlass getBunchOfGlass(){
        return bunchOfGlass;
    }

    public BunchOfRock getBunchOfRock() {
        return bunchOfRock;
    }

    public BunchOfTree getBunchOfTree() {
        return bunchOfTree;
    }

    public void draw(Graphics g)
    {
        bunchOfGround.draw(g);
        bunchOfMountain.draw(g);
        bunchOfRoad.draw(g);
        bunchOfTree.draw(g);
        bunchOfGlass.draw(g);
        bunchOfRock.draw(g);
    }
}
