package Bunch;

import Console.Map;
import PortableEntity.GameEntity;
import TileEntity.Glass;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BunchOfGlass extends Bunch{
    public BunchOfGlass(Map map)
    {
        try {
            setupGlass(map.getMap());
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    ArrayList<Glass> bunch = new ArrayList<>();

    public  void setupGlass(int[][] map) throws FileNotFoundException {
        File file = new File("src\\file\\map.txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            for (int j = 0; j < 24; ++j)
            {
                int type = 0;
                if(Integer.parseInt(s[j]) == Map.GLASS)
                {
                    map[i][j] = Map.GLASS;
                    type = Glass.glass;
                    add(new Glass(j*50, i*50, type));
                }
                else if (Integer.parseInt(s[j]) == Map.WONDERFUL_GLASS){
                    map[i][j] = Map.WONDERFUL_GLASS;
                    type = Glass.wonderful_glass;
                    add(new Glass(j*50, i*50, type));
                }
            }
            ++i;
        }
    }
    @Override
    public void add(GameEntity gameEntity) {
        if(gameEntity instanceof Glass)
        {
            Glass glass = (Glass) gameEntity;
            bunch.add(glass);
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
