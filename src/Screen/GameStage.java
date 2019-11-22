package Screen;

import Console.Player;
import PortableEntity.GameEntity;
import Screen.GameField;

import javax.swing.*;
public class GameStage extends JFrame {
    private Player player;
    private int sizeX = 1200;
    private int sizeY = 630;
    private boolean quit;
    public GameStage(int sizeX, int sizeY, Player player, int operation) {
        this.player = player;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        initUI(operation);
    }

    private void initUI(int operation)
    {
        pack();
        setVisible(true);
        setSize(sizeX, sizeY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        GameField gameField = new GameField(player, this, operation);
        add(gameField);
    }

}
