package Bunch;

import Console.Map;
import PortableEntity.GameEntity;
import TileEntity.Mountain;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BunchOfMountain extends  Bunch{
    public BunchOfMountain(Map map, String mapNumber)
    {
        try {
            setupMountain(map.getMap(), mapNumber);
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    ArrayList<Mountain> bunch = new ArrayList<>();

    public  void setupMountain(int[][] map, String mapNumber) throws FileNotFoundException {
        File file = new File("src\\file\\" + mapNumber + ".txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            for (int j = 0; j < 24; ++j)
            {
                if(Integer.parseInt(s[j]) == Map.MOUNTAIN)
                {
                    map[i][j] = Map.MOUNTAIN;
                    add(new Mountain(j*50, i*50));
                }
            }
            ++i;
        }
    }
    @Override
    public void add(GameEntity gameEntity) {
        if(gameEntity instanceof Mountain)
        {
            Mountain mountain = (Mountain)gameEntity;
            bunch.add(mountain);
        }
    }

    @Override
    public void delete(int index) {
        bunch.remove(index);
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i < bunch.size(); ++i)
        {
            g.drawImage(bunch.get(i).getImage(), bunch.get(i).getLocationX(), bunch.get(i).getLocationY(), null);
        }
    }
}
