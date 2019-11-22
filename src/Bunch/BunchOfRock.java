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
            setupRock(map);
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    ArrayList<Rock> bunch = new ArrayList<>();

    private void setupRock(Map map) throws FileNotFoundException {
        File file = new File("src\\file\\" + map.getMapNumber() + ".txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            for (int j = 0; j < 24; ++j)
            {
                if(Integer.parseInt(s[j]) == Map.BIG_ROCK)
                {
                    add(new Rock(j*50, i*50, Rock.big_rock));
                }
                else if (Integer.parseInt(s[j]) == Map.SMALL_ROCK){
                    add(new Rock(j*50, i*50, Rock.small_rock));
                }
                else if (Integer.parseInt(s[j]) == Map.TINY_ROCK){
                    add(new Rock(j*50, i*50, Rock.tiny_rock));
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

