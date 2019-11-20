package Bunch;

import Console.Player;
import PortableEntity.Bullet.Bullet;
import PortableEntity.Enemy.Enemy;
import PortableEntity.GameEntity;

import java.awt.*;
import java.util.ArrayList;

public class BunchOfBullet extends Bunch {
    private Player player;
    public BunchOfBullet(Player player)
    {
        this.player = player;
    }
    private ArrayList<Bullet> bunch = new ArrayList<Bullet>();

    public void add(GameEntity bullet)
    {
        if(bullet instanceof Bullet)
        {
            Bullet that = (Bullet)bullet;
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
            if(bunch.get(i) != null)
                g.drawImage(bunch.get(i).getImage(), bunch.get(i).getLocationX(), bunch.get(i).getLocationY(),null);
        }
    }
    public void move(Enemy enemy)
    {
        for (int i = 0; i < bunch.size(); ++i)
        {
            if (!bunch.get(i).move(enemy))
            {
                if(!(desTroyTarget(enemy, i)))
                {
                    bunch.remove(i);
                    --i;
                }
                else
                {
                    removeAll();
                }
            }
        }
    }

    public boolean desTroyTarget(Enemy enemy, int i)
    {
        enemy.decreaseHP(bunch.get(i).getDamage());
        if(enemy.getHP() <= 0)
        {
            player.addScore(enemy.getPrize());
            return true;
        }
        return false;
    }
    public void removeAll()
    {
        for (int i = 0; i < bunch.size(); ++i)
        {
            bunch.remove(i);
            --i;
        }
    }

    public ArrayList<Bullet> getBunch() {
        return bunch;
    }
}