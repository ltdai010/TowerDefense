package TileEntity;

import Bunch.BunchOfEnemy;
import Console.Map;
import PortableEntity.Enemy.BossEnemy;
import PortableEntity.Enemy.NormalEnemy;
import PortableEntity.Enemy.SmallEnemy;
import PortableEntity.Enemy.TankerEnemy;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Spawner implements GameTileEntity {
    private Point point;
    private BunchOfEnemy bunchOfEnemy;
    private String mapNumber;
    private int wave;
    private long startTime;
    private Map map;
    private String type;
    private int sleepTime;
    private Scanner scanner;
    public Spawner(int locationX, int locationY, Map map) {
        point = new Point(locationX, locationY);
        this.mapNumber = map.getMapNumber();
        this.map = map;
        this.sleepTime = 0;
        wave = 1;
        loadScanner();
        startTime = System.currentTimeMillis();
    }

    private void loadScanner()
    {
        try {
            System.out.println(mapNumber);
            scanner = new Scanner( new File("src\\stage\\" + mapNumber + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    private boolean loadSpawn()
    {
        if(System.currentTimeMillis() > startTime + sleepTime)
        {
            String line = scanner.nextLine();
            if(line.equals("end"))
            {
                return false;
            }
            String[] s = line.split(" ");
            switch (s[0])
            {
                case "NE":
                    System.out.println("add");
                    bunchOfEnemy.add(new NormalEnemy(map));
                    break;
                case "SE":
                    bunchOfEnemy.add(new SmallEnemy(map));
                    break;
                case "TE":
                    bunchOfEnemy.add(new TankerEnemy(map));
                    break;
                case "BE":
                    bunchOfEnemy.add(new BossEnemy(map));
                    break;
                default:
                    break;
            }
            sleepTime = Integer.parseInt(s[1]);
            startTime = System.currentTimeMillis();
        }
        return true;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public boolean spawnEnemy()
    {
        if(wave < 6)
        {
            if(!loadSpawn())
            {
                ++wave;
            }
            return true;
        }
        return false;
    }



    public BunchOfEnemy getBunchOfEnemy() {
        return bunchOfEnemy;
    }

    public void setBunchOfEnemy(BunchOfEnemy bunchOfEnemy) {
        this.bunchOfEnemy = bunchOfEnemy;
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
