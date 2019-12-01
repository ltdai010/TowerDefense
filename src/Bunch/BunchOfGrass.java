package Bunch;

import Console.Map;
import PortableEntity.GameEntity;
import TileEntity.Grass;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BunchOfGrass extends Bunch{
    public BunchOfGrass(Map map)
    {
        try {
            setupGlass(map);
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    ArrayList<Grass> bunch = new ArrayList<>();

    public  void setupGlass(Map map) throws FileNotFoundException {
        File file = new File("src\\file\\" + map.getMapNumber() + ".txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            for (int j = 0; j < 24; ++j)
            {
                if(Integer.parseInt(s[j]) == Map.GRASS)
                {
                    add(new Grass(j*50, i*50, Grass.grass));
                }
                else if (Integer.parseInt(s[j]) == Map.WONDERFUL_GRASS){
                    add(new Grass(j*50, i*50, Grass.wonderful_grass));
                }
            }
            ++i;
        }
    }
    @Override
    public void add(GameEntity gameEntity) {
        if(gameEntity instanceof Grass)
        {
            Grass grass = (Grass) gameEntity;
            bunch.add(grass);
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
