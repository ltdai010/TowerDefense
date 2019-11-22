package Bunch;

import Console.Map;
import PortableEntity.GameEntity;
import TileEntity.Tree;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BunchOfTree extends Bunch{
    public BunchOfTree(Map map)
    {
        try {
            setupTree(map);
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    ArrayList<Tree> bunch = new ArrayList<>();

    public  void setupTree(Map map) throws FileNotFoundException {
        File file = new File("src\\file\\" + map.getMapNumber() + ".txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            for (int j = 0; j < 24; ++j)
            {
                if(Integer.parseInt(s[j]) == Map.BIG_TREE)
                {
                    add(new Tree(j*50, i*50, Tree.big_tree));
                }
                else if (Integer.parseInt(s[j]) == Map.SMALL_TREE){
                    add(new Tree(j*50, i*50, Tree.small_tree));
                }
            }
            ++i;
        }
    }
    @Override
    public void add(GameEntity gameEntity) {
        if(gameEntity instanceof Tree)
        {
            Tree tree = (Tree) gameEntity;
            bunch.add(tree);
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
