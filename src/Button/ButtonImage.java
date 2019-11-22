package Button;

import javax.swing.*;
import java.awt.*;

public class ButtonImage extends JButton{
    private ImageIcon image;
    private ImageIcon rollImage;
    public ButtonImage(String imagePath, String rollImagePath, int width, int height){
        image = new ImageIcon(imagePath);
        Image img = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        image = new ImageIcon(img);
        setIcon(image);
        rollImage = new ImageIcon(rollImagePath);
        img = rollImage.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        rollImage = new ImageIcon(img);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setRolloverIcon(rollImage);
        setRolloverEnabled(true);
    }

    public ImageIcon getImage() {
        return image;
    }

    public ImageIcon getRollImage() {
        return rollImage;
    }
}
