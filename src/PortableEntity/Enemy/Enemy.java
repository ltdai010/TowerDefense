package PortableEntity.Enemy;

import Console.HealthBar;
import Console.Map;
import PortableEntity.GameEntity;

import java.awt.*;

public abstract class Enemy implements GameEntity {

    protected int locationX;
    protected int locationY;
    protected Point point;
    protected int[][] map = new int[12][24];
    protected Map Map;
    protected HealthBar healthBar;
    protected Image enemy;
    protected Image rotatedEnemy;
    protected long sleepMove;
    protected double armor;
    protected double HP;
    protected static int UP = 1;
    protected static int DOWN = 2;
    protected static int RIGHT = 3;
    protected static int LEFT = 4;
    protected int direction = RIGHT;
    public Enemy(Map map)
    {
        copyMap(map);
        Map = map;
        HP = getMaxHP();
        armor = getDefaultArmor();
        setLocation((int)map.getBunchOfRoad().getStartPoint().getPoint().getX(),
                (int)map.getBunchOfRoad().getStartPoint().getPoint().getY());
        point = new Point(locationX + getSizeX()/2, locationY + getSizeY()/2);
        healthBar = new HealthBar(locationX, locationY, getSizeX(), 5);
    }

    public void copyMap(Map map)
    {
        for(int i = 0; i < 12; ++i)
        {
            for(int j = 0; j < 24; ++j)
            {
                this.map[i][j] = map.getObject(i, j);
            }
        }
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public Point getPoint() {
        return point;
    }

    public void setSleepMove(long sleepMove) {
        this.sleepMove = sleepMove;
    }
    public Image getImage()
    {
        return this.enemy;
    }

    public long getSleepMove() {
        return sleepMove;
    }


    public double getHP() {
        return HP;
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

    public void decreaseHP(int damage)
    {
        HP -= damage/armor;
        healthBar.decreaseGreenBar(HP/getMaxHP());
    }

    public abstract int getSizeX();
    public abstract int getSizeY();
    public abstract int getDamage();
    public abstract int getSpeed();
    public abstract double getDefaultArmor();
    public abstract int getPrize();
    public void setArmor(double times)
    {
        this.armor/=times;
    }
    public abstract double getMaxHP();
    public void draw(Graphics g)
    {
        g.drawImage(rotatedEnemy, locationX, locationY, null);
    }

    public boolean move()
    {
        if(System.currentTimeMillis() > sleepMove + getSpeed())
        {
            int j = locationX/50;
            int i = locationY/50;
            map[i][j] = 0;
            if(locationY > i*50 && j < 23 && map[i][j + 1] == Map.ROAD)
            {
                --locationY;
            }
            else if(j < 23 && map[i][j + 1] == Map.ROAD && locationY == i * 50)
            {
                if (!checkPreMove(RIGHT)){
                    setDirection(RIGHT);
                    rotatedEnemy = rotateImageByDegrees(toBufferedImage(enemy), 0);
                }
                ++locationX;
            }
            else if(j > 0 && map[i][j - 1] == Map.ROAD)
            {
                --locationX;
            }
            else if(i < 11 && map[i + 1][j] == Map.ROAD)
            {
                if (!checkPreMove(DOWN)){
                    setDirection(DOWN);
                    rotatedEnemy = rotateImageByDegrees(toBufferedImage(enemy), 90);
                }
                ++locationY;
            }
            else if(i > 0 && map[i - 1][j] == Map.ROAD)
            {
                if (!checkPreMove(UP)){
                    setDirection(UP);
                    rotatedEnemy = rotateImageByDegrees(toBufferedImage(enemy), -90);
                }
                --locationY;
            }
            else
            {
                Map.getBunchOfRoad().getTarget().decreaseHP(getDamage());
                return false;
            }
            healthBar.setLocation(locationX, locationY);
            setPoint(new Point(locationX + getSizeX()/2, locationY + getSizeY()/2));
            sleepMove = System.currentTimeMillis();
        }
        return true;
    }
    public void drawHealthBar(Graphics g)
    {
        healthBar.draw(g);
    }
    public boolean checkPreMove(int direction){
        return (this.direction == direction);
    }
}