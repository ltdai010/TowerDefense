package Bunch;

import Console.Map;
import PortableEntity.GameEntity;
import TileEntity.Road;
import TileEntity.Spawner;
import TileEntity.Target;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BunchOfRoad extends Bunch{
    private ArrayList<Road> bunch = new ArrayList<>();
    private Spawner startPoint;
    private Target target;
    public BunchOfRoad(Map map)
    {
        try {
            setUpRoad(map.getMap());
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public  void setUpRoad(int[][] map) throws FileNotFoundException {
        File file = new File("src\\file\\map.txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            for (int j = 0; j < 24; ++j)
            {
                if(Integer.parseInt(s[j]) == Map.ROAD)
                {
                    map[i][j] = Map.ROAD;
                    add(new Road(j*50, i*50));
                }
                else if(Integer.parseInt(s[j]) == Map.SPAWNER)
                {
                    map[i][j] = Map.ROAD;
                    startPoint = new Spawner(j*50, i*50);
                    bunch.add(startPoint);
                }
                else if(Integer.parseInt(s[j]) == Map.TARGET)
                {
                    map[i][j] = Map.ROAD;
                    target = new Target(j*50, i*50);
                    bunch.add(target);
                }
            }
            ++i;
        }
    }

    public Target getTarget() {
        return target;
    }

    public Spawner getStartPoint() {
        return startPoint;
    }

    @Override
    public void add(GameEntity gameEntity) {
        if(gameEntity instanceof Road)
        {
            Road road = (Road)gameEntity;
            bunch.add(road);
        }
    }

    @Override
    public void delete(int index) {
        bunch.remove(index);
    }

    @Override
    public void draw(Graphics g) {
        for(int i = 0; i < bunch.size(); ++i)
        {
            bunch.get(i).draw(g);
        }
    }
}
