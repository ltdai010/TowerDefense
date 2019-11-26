package Console;

import PortableEntity.Enemy.Enemy;
import Screen.GameField;
import TileEntity.Tower.MachineGunTower;
import TileEntity.Tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Player{
    private int score = 0;
    private String name;
    private JLabel scoreText;
    private JLabel addScore_label;
    private JLabel avatar;
    private long start;
    private JLabel name_label;
    private GameField gameField;
    private File file;
    private ImageIcon characterIcon;
    public static final String FEMALE_CHARACTER = "female-avatar";
    public static final String MALE_CHARACTER = "male-avatar";
    public static final int AVATAR_SIZE = 100;

    private int stage;
    public Player(String name, String character)
    {
        this.name = name;
        this.name_label = new JLabel(name);
        avatar = new JLabel();
        loadIcon(character);
        avatar.setBounds(0, 0, AVATAR_SIZE, AVATAR_SIZE);
        avatar.setIcon(characterIcon);
        addScore_label = new JLabel();
        addScore_label.setBounds(1000, 50, 100, 50);
        addScore_label.setFont(new Font(Font.DIALOG, Font.PLAIN, 32));
        name_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
        name_label.setBounds(AVATAR_SIZE,0, 200, 50);
        scoreText = new JLabel(Integer.toString(score));
        scoreText.setBounds(1000, 0, 100, 50);
        scoreText.setFont(new Font(Font.DIALOG, Font.PLAIN, 32));
        start = System.currentTimeMillis();
    }

    private void loadIcon(String path)
    {
        ImageIcon ii = new ImageIcon("src\\img\\" + path + ".png");
        Image image = ii.getImage().getScaledInstance(AVATAR_SIZE, AVATAR_SIZE, Image.SCALE_SMOOTH);
        ii = new ImageIcon(image);
        characterIcon = ii;
    }

    public JLabel getAvatar() {
        return avatar;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public void setScore(int score) {
        this.score = score;
        scoreText.setText(Integer.toString(score));
    }

    public JLabel getScoreText() {
        return scoreText;
    }

    public JLabel getName_label() {
        return name_label;
    }

    public JLabel getAddScore_label() {
        return addScore_label;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return stage;
    }

    public GameField getGameField() {
        return gameField;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void saveScore()
    {
        try {
            file = new File("src\\save\\score\\" + this.name +".txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(this.name + "\n");
            fileWriter.append(score + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGame()
    {
        try {
            file = new File("src\\save\\game\\player.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(this.name + "\n");
            fileWriter.append(Integer.toString(score));
            fileWriter.append("\n" + stage);
            for(Tower tower: gameField.getBunchOfTower().getBunch())
            {
                fileWriter.append("\n");
                fileWriter.append(tower.toString());
            }
            fileWriter.append("\nend");
            for (Enemy enemy: gameField.getBunchOfEnemy().getBunch())
            {
                fileWriter.append("\n");
                fileWriter.append(enemy.toString());
            }
            fileWriter.append("\nend");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addScoreAnimate()
    {
        if(System.currentTimeMillis() > start + 1000)
        {
            addScore_label.setText("");
        }
    }


    public String getName() {
        return name;
    }

    public void addScore(int score) {
        this.score += score;
        start = System.currentTimeMillis();
        if(score > 0)
            addScore_label.setText("+" + score);
        else
            addScore_label.setText(Integer.toString(score));
        scoreText.setText(Integer.toString(this.score));
    }

    public int getScore() {
        return score;
    }
}