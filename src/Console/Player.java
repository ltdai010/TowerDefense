package Console;

import Screen.GameField;
import TileEntity.Tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Player implements Comparable<Player>{
    private int coin = 0;
    private String name;
    private JLabel scoreText;
    private JLabel addScore_label;
    private JLabel avatar;
    private long start;
    private int score;
    private JLabel name_label;
    private GameField gameField;
    private File file;
    private ImageIcon characterIcon;
    private String character;
    public static final String FEMALE_CHARACTER = "female-avatar";
    public static final String MALE_CHARACTER = "male-avatar";
    public static final int AVATAR_SIZE = 100;

    private int stage;
    public Player(String name, String character)
    {
        this.name = name;
        this.name_label = new JLabel(name);
        this.character = character;
        avatar = new JLabel();
        loadIcon(character);
        avatar.setBounds(0, 0, AVATAR_SIZE, AVATAR_SIZE);
        avatar.setIcon(characterIcon);
        addScore_label = new JLabel();
        addScore_label.setBounds(1000, 50, 100, 50);
        addScore_label.setFont(new Font(Font.DIALOG, Font.PLAIN, 32));
        name_label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
        name_label.setBounds(AVATAR_SIZE,0, 200, 50);
        scoreText = new JLabel(Integer.toString(coin));
        scoreText.setBounds(1000, 0, 100, 50);
        scoreText.setFont(new Font(Font.DIALOG, Font.PLAIN, 32));
        start = System.currentTimeMillis();
    }

    public Player(int stage, int score, String name)
    {
        this.stage = stage;
        this.name = name;
        this.score = score;
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

    public void setCoin(int coin) {
        this.coin = coin;
        scoreText.setText(Integer.toString(coin));
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
            fileWriter.append(this.stage + "\n");
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
            fileWriter.append(this.name);
            fileWriter.append("\n" + this.character);
            fileWriter.append("\n" + this.coin);
            fileWriter.append("\n" + this.stage);
            for(Tower tower: gameField.getBunchOfTower().getBunch())
            {
                fileWriter.append("\n");
                fileWriter.append(tower.toString());
            }
            fileWriter.append("\nend");
            fileWriter.append("\n" + gameField.getMap().getBunchOfRoad().getTarget().getHP());
            fileWriter.append("\n" + this.score);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }

    public void addScoreAnimate()
    {
        if(System.currentTimeMillis() > start + 1000)
        {
            addScore_label.setText("");
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void addCoin(int coin) {
        this.coin += coin;
        start = System.currentTimeMillis();
        if(coin > 0)
        {
            score+=coin;
            addScore_label.setText("+" + coin);
        }
        else
            addScore_label.setText(Integer.toString(coin));
        scoreText.setText(Integer.toString(this.coin));
    }

    public int getCoin() {
        return coin;
    }

    @Override
    public int compareTo(Player o) {
        if(this.stage != o.stage)
            return o.stage - this.stage;
        if(this.score != o.score)
            return o.score - this.score;
        return this.name.compareTo(o.name);
    }
}