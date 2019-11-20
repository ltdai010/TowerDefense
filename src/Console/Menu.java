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
    private ButtonImage continued;
    private ButtonImage start;
    private ButtonImage instruction;
    private ButtonImage quit;
    private JButton create;
    private ImageIcon background;
    private JLabel back;
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
        continued = new ButtonImage("src\\img\\continued.png", "src\\img\\roll-continued.png", 100, 100);
        add(continued);
        continued.setBounds(400, 440, 100, 100);
        start = new ButtonImage("src\\img\\play.png","src\\img\\roll-play.png", 150, 150);
        add(start);
        start.setBounds(520, 410, 150, 150);
        instruction = new ButtonImage("src\\img\\about.png", "src\\img\\roll-about.png",100,100);
        add(instruction);
        instruction.setBounds(700, 440, 100, 100);
        quit = new ButtonImage("src\\img\\close.png", "src\\img\\roll-close.png", 50, 50);
        add(quit);
        quit.setBounds(1120, 10, 50, 50);
        create = new JButton("Create");
        create.setBounds(450, 100, 100, 50);
        this.add(create);
        create.setVisible(false);
        loadBackground();
        back = new JLabel("",background, JLabel.CENTER);
        back.setBounds(0,0, sizeX, sizeY);
        add(back);
        setIconImage(new ImageIcon("src\\img\\game-icon.png").getImage());
        setTitle("Tower Defense Game");
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
    public void loadBackground(){
        background = new ImageIcon("src\\img\\background.png");
        Image image = background.getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        background = new ImageIcon(image);
    }
}
