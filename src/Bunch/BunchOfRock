package Bunch;

import Console.Map;
import PortableEntity.GameEntity;
import TileEntity.Rock;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BunchOfRock extends Bunch{
    public BunchOfRock(Map map)
    {
        try {
            setupRock(map.getMap());
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    ArrayList<Rock> bunch = new ArrayList<>();

    public  void setupRock(int[][] map) throws FileNotFoundException {
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
                if(Integer.parseInt(s[j]) == Map.BIG_ROCK)
                {
                    map[i][j] = Map.BIG_ROCK;
                    type = Rock.big_rock;
                    add(new Rock(j*50, i*50, type));
                }
                else if (Integer.parseInt(s[j]) == Map.SMALL_ROCK){
                    map[i][j] = Map.SMALL_ROCK;
                    type = Rock.small_rock;
                    add(new Rock(j*50, i*50, type));
                }
                else if (Integer.parseInt(s[j]) == Map.TINY_ROCK){
                    map[i][j] = Map.TINY_ROCK;
                    type = Rock.tiny_rock;
                    add(new Rock(j*50, i*50, type));
                }
            }
            ++i;
        }
    }
    @Override
    public void add(GameEntity gameEntity) {
        if(gameEntity instanceof Rock)
        {
            Rock rock = (Rock) gameEntity;
            bunch.add(rock);
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

