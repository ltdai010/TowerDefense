package Button;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ControlButton extends JButton{
    private ImageIcon image;
    private ImageIcon rollImage;
    private AudioInputStream audioInputStream;
    private Clip clip;
    public ControlButton(String imagePath, String rollImagePath, int width, int height){
        loadImage(imagePath, rollImagePath, width, height);
        try {
            loadAudio();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        addActionListener(e -> {
            clip.stop();
            clip.setMicrosecondPosition(0);
            clip.start();
        });
    }

    private void loadAudio() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        audioInputStream = AudioSystem.getAudioInputStream(new File("src\\audio\\button_click_audio.wav"));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    private void loadImage(String imagePath, String rollImagePath, int width, int height)
    {
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

    public void checked()
    {
        this.setRolloverEnabled(false);
        this.setIcon(rollImage);
    }

    public void unchecked()
    {
        this.setIcon(image);
        this.setRolloverEnabled(true);
    }

    public ImageIcon getImage() {
        return image;
    }

    public ImageIcon getRollImage() {
        return rollImage;
    }
}
