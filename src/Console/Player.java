package Console;

import Bunch.BunchOfEnemy;
import PortableEntity.Enemy.Enemy;
import Screen.GameField;
import TileEntity.Tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Player{
    private int score = 0;
    private String name;
    private JLabel scoreText;
    private GameField gameField;
    private File file;
    public Player(String name)
    {
        this.name = name;
    }

    public void setGameField(GameField gameField) {
        this.gameField = gameField;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScoreText()
    {
        scoreText = new JLabel(Integer.toString(score));
        scoreText.setBounds(1000, 0, 100, 50);
        scoreText.setFont(new Font(Font.DIALOG, Font.PLAIN, 32));
        gameField.add(scoreText);
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
            FileWriter fileWriter = new FileWriter(file, true);
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
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(this.name + "\n");
            fileWriter.append(Integer.toString(score));
            fileWriter.append("\n");
            for(Tower tower: gameField.getBunchOfTower().getBunch())
            {
                fileWriter.append("\n");
                fileWriter.append(tower.toString());
            }
            fileWriter.append("end");
            for (Enemy enemy: gameField.getBunchOfEnemy().getBunch())
            {
                fileWriter.append("\n");
                fileWriter.append(enemy.toString());
            }
            fileWriter.append("end");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    public void addScore(int score) {
        this.score += score;
        scoreText.setText(Integer.toString(this.score));
    }

    public int getScore() {
        return score;
    }
}