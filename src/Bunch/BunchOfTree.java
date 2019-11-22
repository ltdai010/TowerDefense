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
    public BunchOfTree(Map map, String mapNumber)
    {
        try {
            setupTree(map.getMap(), mapNumber);
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    ArrayList<Tree> bunch = new ArrayList<>();

    public  void setupTree(int[][] map, String mapNumber) throws FileNotFoundException {
        File file = new File("src\\file\\" + mapNumber + ".txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            String[] s = line.split(" ");
            for (int j = 0; j < 24; ++j)
            {
                int type = 0;
                if(Integer.parseInt(s[j]) == Map.BIG_TREE)
                {
                    map[i][j] = Map.BIG_TREE;
                    type = Tree.big_tree;
                    add(new Tree(j*50, i*50, type));
                }
                else if (Integer.parseInt(s[j]) == Map.SMALL_TREE){
                    map[i][j] = Map.SMALL_TREE;
                    type = Tree.small_tree;
                    add(new Tree(j*50, i*50, type));
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
