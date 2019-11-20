package Bunch;

import PortableEntity.GameEntity;
import TileEntity.Ground;
import TileEntity.Road;

import java.awt.*;
import java.util.ArrayList;
import Console.Map;

public class BunchOfGround extends Bunch {
    private ArrayList<Ground> bunch = new ArrayList<>();
    public BunchOfGround(Map map)
    {
        setupGround(map);
    }

    public void setupGround(Map map)
    {
        for (int i = 0; i < 12; ++i)
        {
            for (int j = 0; j < 24; ++j)
            {
                if(map.getMap()[i][j] != Map.ROAD)
                {
                    int type = Ground.ground;
                    if(i > 0 && j > 0 && j < 23 && i < 11)
                    {
                        if(map.getMap()[i - 1][j] == Map.ROAD)
                        {
                            type = Ground.low_road;
                            if(map.getMap()[i][j + 1] == Map.ROAD)
                            {
                                type = Ground.up_right_edge;
                            }
                            else if(map.getMap()[i][j - 1] == Map.ROAD)
                            {
                                type = Ground.up_left_edge;
                            }
                        }
                        else if(map.getMap()[i + 1][j] == Map.ROAD)
                        {
                            type = Ground.up_road;
                            if(map.getMap()[i][j + 1] == Map.ROAD)
                            {
                                type = Ground.low_right_edge;
                            }
                            else if(map.getMap()[i][j - 1] == Map.ROAD)
                            {
                                type = Ground.low_left_edge;
                            }
                        }
                        else if(map.getMap()[i][j - 1] == Map.ROAD)
                        {
                            type = Ground.right_road;
                        }
                        else if(map.getMap()[i][j + 1] == Map.ROAD)
                        {
                            type = Ground.left_road;
                        }
                        else if(map.getMap()[i - 1][j - 1] == Map.ROAD)
                        {
                            type = Ground.low_right_road;
                        }
                        else if(map.getMap()[i + 1][j + 1] == Map.ROAD)
                        {
                            type = Ground.up_left_road;
                        }
                        else if(map.getMap()[i + 1][j - 1] == Map.ROAD)
                        {
                            type = Ground.up_right_road;
                        }
                        else if(map.getMap()[i - 1][j + 1] == Map.ROAD)
                        {
                            type = Ground.low_left_road;
                        }
                    }
                    else if(j == 0 && i > 0 && i < 11)
                    {
                        if(map.getMap()[i - 1][j] == Map.ROAD)
                        {
                            type = Ground.low_road;
                            if(map.getMap()[i][j + 1] == Map.ROAD)
                            {
                                type = Ground.up_right_edge;
                            }
                        }
                        else if(map.getMap()[i + 1][j] == Map.ROAD)
                        {
                            type = Ground.up_road;
                            if(map.getMap()[i][j + 1] == Map.ROAD)
                            {
                                type = Ground.low_right_edge;
                            }
                        }
                        else if(map.getMap()[i][j + 1] == Map.ROAD)
                        {
                            type = Ground.left_road;
                        }
                        else if(map.getMap()[i + 1][j + 1] == Map.ROAD)
                        {
                            type = Ground.up_left_road;
                        }
                        else if(map.getMap()[i - 1][j + 1] == Map.ROAD)
                        {
                            type = Ground.low_left_road;
                        }
                    }
                    else if(j == 23 && i > 0 && i < 11)
                    {
                        if(map.getMap()[i - 1][j] == Map.ROAD)
                        {
                            type = Ground.low_road;
                            if(map.getMap()[i][j - 1] == Map.ROAD)
                            {
                                type = Ground.up_left_edge;
                            }
                        }
                        else if(map.getMap()[i + 1][j] == Map.ROAD)
                        {
                            type = Ground.up_road;
                            if(map.getMap()[i][j - 1] == Map.ROAD)
                            {
                                type = Ground.low_left_edge;
                            }
                        }
                        else if(map.getMap()[i][j - 1] == Map.ROAD)
                        {
                            type = Ground.right_road;
                        }
                        else if(map.getMap()[i - 1][j - 1] == Map.ROAD)
                        {
                            type = Ground.low_right_road;
                        }
                        else if(map.getMap()[i + 1][j - 1] == Map.ROAD)
                        {
                            type = Ground.up_right_road;
                        }
                    }
                    bunch.add(new Ground(j*50, i*50, type));
                }
            }
        }
    }
    @Override
    public void add(GameEntity gameEntity) {
        if(gameEntity instanceof Ground)
        {
            Ground ground = (Ground) gameEntity;
            bunch.add(ground);
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
