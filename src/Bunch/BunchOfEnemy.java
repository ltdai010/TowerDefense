package Bunch;

import PortableEntity.Enemy.Enemy;
import PortableEntity.GameEntity;
import Screen.GameField;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BunchOfEnemy extends Bunch{
    GameField gameField;
    public BunchOfEnemy(GameField gameField)
    {
        this.gameField = gameField;
    }
    private ArrayList<Enemy> bunch = new ArrayList<Enemy>();

    public ArrayList<Enemy> getBunch() {
        return bunch;
    }

    public void add(GameEntity enemy)
    {
        if(enemy instanceof Enemy)
        {
            Enemy that = (Enemy) enemy;
            bunch.add(that);
        }
    }

    public void delete(int index)
    {
        bunch.remove(index);
    }

    public void draw(Graphics g)
    {
        ArrayList<Enemy> bunch = new ArrayList<>(this.bunch);
        Collections.sort(bunch, Comparator.comparing(Enemy::getLocationY).thenComparing(Enemy::getLocationX));
        for(int i = 0; i < bunch.size(); ++i)
        {
            bunch.get(i).draw(g);
        }
        for (int i = 0; i < bunch.size();++i)
        {
            bunch.get(i).drawHealthBar(g);
        }
    }

    public void setSleepTime(long sleepTime)
    {
        for (Enemy enemy: bunch)
        {
            enemy.setSleepMove(sleepTime);
        }
    }
    public void onAction()
    {
        for (int i = 0; i < bunch.size(); ++i)
        {
            if(!bunch.get(i).move())
            {
                gameField.getPlayer().addCoin(bunch.get(i).getPrize()/2);
                bunch.remove(i);
                --i;
            }
            else if (bunch.get(i).getHP() <= 0)
            {
                bunch.remove(i);
                --i;
            }
        }
    }
}