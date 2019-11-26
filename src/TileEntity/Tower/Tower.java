package TileEntity.Tower;

import Bunch.BunchOfBullet;
import Bunch.BunchOfEnemy;
import Console.Player;
import PortableEntity.Bullet.Bullet;
import PortableEntity.Enemy.Enemy;
import TileEntity.GameTileEntity;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.awt.*;

public abstract class Tower implements GameTileEntity {
    protected Image turret;
    protected Image rotatedTurret;
    protected AudioInputStream audioInputStream;
    protected Clip clip;
    protected int locationX;
    protected int locationY;
    protected long sleepFire;
    protected long sleepBulletMove;
    protected Enemy currentEnemy = null;
    protected BunchOfBullet bunchOfBullet;

    public Tower(int locationX, int locationY, Player player)
    {
        bunchOfBullet = new BunchOfBullet(player);
        setLocation(locationX,locationY);
    }

    public void setSleepFire(long sleepFire) {
        this.sleepFire = sleepFire;
    }

    public long getSleepFire() {
        return sleepFire;
    }

    public void setSleepBulletMove(long sleepBulletMove) {
        this.sleepBulletMove = sleepBulletMove;
    }

    public long getSleepBulletMove() {
        return sleepBulletMove;
    }


    @Override
    public void setLocation(int x, int y)
    {
        this.locationX = x;
        this.locationY = y;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public Image getImage()
    {
        return rotatedTurret;
    }

    public BunchOfBullet getBunchOfBullet() {
        return bunchOfBullet;
    }

    public abstract void loadAudio();

    public abstract void onAction(BunchOfEnemy bunch);

    public abstract boolean inRange(Enemy enemy);

    public abstract void fireBullet(Enemy enemy);

    public abstract int getPrice();

    protected double checkAngle(Enemy enemy)
    {
        double x = enemy.getPoint().getX() - (double)locationX;
        double y = (double)locationY - enemy.getPoint().getY();
        double k = y/x;
        double angle = Math.atan(k)/Math.PI*180;
        if(k <= 1 && k >= -1 && x >= 0)
        {
            rotatedTurret = rotateImageByDegrees(toBufferedImage(turret), - angle);
            return - angle;
        }
        else if((k >= 1 && y >= 0) || (k <= -1 && y >= 0))
        {
            if(k >= 1)
            {
                rotatedTurret = rotateImageByDegrees(toBufferedImage(turret), -angle);
                return -angle;
            }
            else
            {
                rotatedTurret = rotateImageByDegrees(toBufferedImage(turret), -(180 + angle));
                return -(180 + angle);
            }
        }
        else if(k >= -1 && x <= 0 && k <= 1)
        {
            rotatedTurret = rotateImageByDegrees(toBufferedImage(turret), -(180 + angle));
            return -(180 + angle);
        }
        if(k >= 1 )
        {
            rotatedTurret = rotateImageByDegrees(toBufferedImage(turret), -(180 + angle));
            return -(180 + angle);
        }
        rotatedTurret = rotateImageByDegrees(toBufferedImage(turret), -angle);
        return -angle;
    }

    public void bulletMove(Enemy enemy)
    {
        bunchOfBullet.move(enemy);
    }
}
