package Console;

import Screen.GameField;
import Screen.GameStage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Scanner;

public class Menu extends JFrame{
    private JButton continued;
    private JButton start;
    private JButton instruction;
    private JButton quit;
    private JButton create;
    private TextField playerName;
    private final int sizeX = 1206;
    private final int sizeY = 630;
    public Menu()
    {
        pack();
        setVisible(true);
        setSize(sizeX, sizeY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        continued = new JButton("Continue");
        add(continued);
        continued.setBounds(200, 100, 100, 50);
        start = new JButton("Start");
        add(start);
        start.setBounds(100, 100, 100, 50);
        instruction = new JButton("Instruction");
        add(instruction);
        instruction.setBounds(100, 200, 100, 50);
        quit = new JButton("Quit");
        add(quit);
        quit.setBounds(100, 300, 100, 50);
        create = new JButton("Create");
        create.setBounds(450, 100, 100, 50);
        this.add(create);
        create.setVisible(false);
        initButton();
    }
    private void initButton()
    {
        start.addActionListener(e -> startAction());
        quit.addActionListener(e -> quitAction());
        instruction.addActionListener(e -> instructionAction());
        create.addActionListener(e -> createAction());
        continued.addActionListener(e -> continueAction());
    }

    private void startAction()
    {
        start.setVisible(false);
        instruction.setVisible(false);
        quit.setVisible(false);
        continued.setVisible(false);
        remove(start);
        remove(instruction);
        remove(quit);
        remove(continued);
        playerName = new TextField();
        playerName.setBounds(100, 100, 300,50);
        playerName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 32));
        this.add(playerName);
        create.setVisible(true);
    }

    private void continueAction()
    {
        File file = new File("src\\save\\game\\player.txt");
        if(file.exists())
        {
            try {
                FileReader fileReader = new FileReader(file);
                Scanner scanner = new Scanner(fileReader);
                Player player = new Player(scanner.nextLine());
                player.setScore(scanner.nextInt());
                GameStage gameStage = new GameStage(sizeX, sizeY, player, GameField.CONTINUE);
                this.setVisible(false);
                this.dispose();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void createAction()
    {
        Player player = new Player(playerName.getText());
        GameStage gameStage = new GameStage(sizeX, sizeY, player, GameField.NEW);
        this.setVisible(false);
        this.dispose();
    }
    private void quitAction()
    {
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }
    private void instructionAction()
    {

    }
}
