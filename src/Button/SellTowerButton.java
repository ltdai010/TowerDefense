package Button;

import Bunch.BunchOfTower;
import Console.Map;
import PortableEntity.GameEntity;
import Screen.GameField;
import TileEntity.Tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SellTowerButton extends JButton implements MouseListener {
    protected int locationX;
    protected int locationY;
    protected boolean clicked;
    protected GameField gameField;
    protected Map map;
    protected Image buttonImage;
    protected int imageLocationX;
    protected int imageLocationY;
    public static final int sizeX = 75;
    public static final int sizeY = 75;
    protected ImageIcon buttonIcon;
    protected BunchOfTower bunchOfTower;
    public SellTowerButton(int locationX, int locationY, BunchOfTower bunch, GameField gameField, Map map)
    {
        super();
        this.locationX = locationX;
        this.locationY = locationY;
        setBackground(null);
        setBorder(null);
        loadImage();
        setIcon(buttonIcon);
        setBounds(locationX,locationY, sizeX, sizeY);
        setVisible(true);
        this.map = map;
        this.gameField = gameField;
        clicked = false;
        toImage();
        this.bunchOfTower = bunch;
        this.addMouseListener(this);
    }

    public void loadImage() {
        ImageIcon button = new ImageIcon("src\\img\\shovel.png");
        Image image= button.getImage().getScaledInstance(sizeX, sizeY,
                Image.SCALE_SMOOTH);
        this.buttonIcon = new ImageIcon(image);
    }

    public void toImage()
    {
        buttonImage = buttonIcon.getImage();
        ImageIcon ii = new ImageIcon(buttonImage);
        Image image= ii.getImage().getScaledInstance(50, 50,
                Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        buttonImage = ii.getImage();
    }

    public void onClickDraw(Graphics g)
    {
        if(clicked == true)
        {
            imageLocationX = (int)MouseInfo.getPointerInfo().getLocation().getX() - (int)gameField.getLocationOnScreen().getX();
            imageLocationY = (int) MouseInfo.getPointerInfo().getLocation().getY() - (int)gameField.getLocationOnScreen().getY();
            imageLocationX = imageLocationX/50;
            imageLocationX = imageLocationX*50;
            imageLocationY = imageLocationY/50;
            imageLocationY = imageLocationY*50;
            if(buttonImage != null)
                g.drawImage(buttonImage, imageLocationX, imageLocationY, null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        imageLocationX = (int)MouseInfo.getPointerInfo().getLocation().getX() - (int)gameField.getLocationOnScreen().getX();
        imageLocationY = (int) MouseInfo.getPointerInfo().getLocation().getY() - (int)gameField.getLocationOnScreen().getY();
        if(clicked == false && (imageLocationX > locationX && imageLocationX < locationX + sizeX)
                && (imageLocationY > locationY && imageLocationY < locationY + sizeY))
        {
            clicked = true;
            return;
        }
        if(clicked == true && availableLocation(imageLocationX, imageLocationY))
        {
            clicked = false;
            imageLocationX = imageLocationX/50;
            imageLocationX = imageLocationX*50;
            imageLocationY = imageLocationY/50;
            imageLocationY = imageLocationY*50;
            remove();
        }
        else if(clicked == true && (imageLocationX >= 0 && imageLocationX <= GameEntity.SCREENWIDTH)
                && (imageLocationY >= 525 && imageLocationY <= 600))
        {
            clicked = false;
        }
    }

    public void remove()
    {
        for (int i = 0; i < bunchOfTower.getBunch().size(); ++i)
        {
            if(bunchOfTower.getBunch().get(i).getLocationX() == imageLocationX
                    && bunchOfTower.getBunch().get(i).getLocationY() == imageLocationY)
            {
                gameField.getPlayer().addScore(bunchOfTower.getBunch().get(i).getPrice()/2);
                bunchOfTower.getBunch().remove(i);
            }
        }
    }

    public boolean availableLocation(int imageLocationX, int imageLocationY)
    {
        if(map.getObject(imageLocationY/50, imageLocationX/50) == Map.TOWER)
        {
            map.addObject(imageLocationY/50, imageLocationX/50, Map.MOUNTAIN);
            return true;
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
