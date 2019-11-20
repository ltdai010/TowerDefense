package Bunch;

import PortableEntity.GameEntity;
import TileEntity.Tower.Tower;

import java.awt.*;
import java.util.ArrayList;

public class BunchOfTower extends Bunch{
    public BunchOfTower()
    {

    }

    private ArrayList<Tower> bunch = new ArrayList<Tower>();

    public void add(GameEntity tower)
    {
        if(tower instanceof Tower)
        {
            Tower that = (Tower) tower;
            bunch.add(that);
        }
    }

    public void delete(int index)
    {
        bunch.remove(index);
    }

    public void draw(Graphics g)
    {
        for(int i = 0; i < bunch.size(); ++i)
        {
            g.drawImage(bunch.get(i).getImage(), bunch.get(i).getLocationX(),bunch.get(i).getLocationY(),null);
        }
    }

    public void drawBullet(Graphics g)
    {
        for(int i = 0; i < bunch.size(); ++i)
        {
            bunch.get(i).getBunchOfBullet().draw(g);
        }
    }

    public void onAction(BunchOfEnemy bunchOfEnemy)
    {
        for(int i = 0; i < bunch.size(); ++i)
        {
            if(bunch.get(i) != null)
                bunch.get(i).onAction(bunchOfEnemy);
        }
    }

    public ArrayList<Tower> getBunch() {
        return bunch;
    }

    public void setSleepTime(long sleepTime)
    {
        for(int i = 0; i < bunch.size(); ++i)
        {
            bunch.get(i).setSleepFire(sleepTime);
            bunch.get(i).setSleepBulletMove(sleepTime);
        }
    }
}