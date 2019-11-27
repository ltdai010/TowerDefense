package Console;

import Screen.GameField;
import Screen.GameStage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import Button.ControlButton;;

public class Menu extends JFrame{
    private ControlButton continued;
    private ControlButton start;
    private ControlButton instruction;
    private ControlButton quit;
    private ControlButton female_button;
    private ControlButton male_button;
    private AudioInputStream menu_audio;
    private Clip clip;
    private ControlButton create;
    private ImageIcon background;
    private JLabel back;
    private JLabel warning;
    private JLabel playerNameArea;
    private JTextField playerName;
    private final int sizeX = 1206;
    private final int sizeY = 630;
    private String character;

    public Menu()
    {
        pack();
        setVisible(true);
        setSize(sizeX, sizeY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        ImageIcon icon = new ImageIcon("src\\img\\character-name-area.png");
        int icon_width = icon.getIconWidth();
        int icon_length = icon.getIconHeight();
        playerName = new JTextField();
        playerName.setOpaque(false);
        playerName.setBounds(440, 290, icon_width / 4,icon_length / 4);
        playerName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        Image img = icon.getImage().getScaledInstance(icon_width / 2, icon_length / 2, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        playerNameArea = new JLabel("",icon,JLabel.CENTER);
        playerNameArea.setLayout(new BorderLayout());
        playerNameArea.add(playerName);
        playerNameArea.setBounds(440, 290, icon_width / 2, icon_length / 2);
        playerName.setBorder(null);
        warning = new JLabel("Please enter your nickname and choose a character!");
        warning.setFont(new Font(Font.DIALOG, Font.ITALIC, 16));
        warning.setBounds(400, 400, 500, 30);
        warning.setForeground(Color.RED);
        loadBackground("src\\img\\background.png");
        initButton();
        initAudio();
        back = new JLabel("",background, JLabel.CENTER);
        back.setVisible(false);
        add(back);
        back.setBounds(0,0, sizeX, sizeY);
        back.setVisible(true);
        setIconImage(new ImageIcon("src\\img\\game-icon.png").getImage());
        setTitle("Tower Defense Game");
        }

        private void initAudio() {
        try {
            menu_audio = AudioSystem.getAudioInputStream(new File("src\\audio\\menu.wav"));
            clip = AudioSystem.getClip();
            clip.open(menu_audio);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        }

        private void initButton()
        {
            female_button = new ControlButton("src\\img\\female-character.png", "src\\img\\roll-female-character.png", 150, 450);
            male_button = new ControlButton("src\\img\\male-character.png", "src\\img\\roll-male-character.png", 150, 450);
            female_button.setBounds(200, 100, 150, 450);
            male_button.setBounds(800, 100, 150, 450);
            continued = new ControlButton("src\\img\\continued.png", "src\\img\\roll-continued.png", 100, 100);
            add(continued);
            continued.setBounds(400, 440, 100, 100);
            start = new ControlButton("src\\img\\play.png","src\\img\\roll-play.png", 150, 150);
            add(start);
            start.setBounds(520, 410, 150, 150);
            instruction = new ControlButton("src\\img\\about.png", "src\\img\\roll-about.png",100,100);
            add(instruction);
            instruction.setBounds(700, 440, 100, 100);
            quit = new ControlButton("src\\img\\close.png", "src\\img\\roll-close.png", 50, 50);
            add(quit);
            quit.setBounds(1120, 10, 50, 50);
            create = new ControlButton("src\\img\\creat_button.png", "src\\img\\roll-creat_button.png",120, 50);
            create.setBounds(520, 340, 120, 50);
            start.addActionListener(e -> startAction());
            quit.addActionListener(e -> quitAction());
            instruction.addActionListener(e -> instructionAction());
            create.addActionListener(e -> createAction());
            continued.addActionListener(e -> continueAction());
            male_button.addActionListener(e -> chooseMale());
            female_button.addActionListener(e -> chooseFemale());
        }

        private void chooseMale()
        {
            character = Player.MALE_CHARACTER;
            male_button.checked();
            female_button.unchecked();
        }

        private void chooseFemale()
        {
            character = Player.FEMALE_CHARACTER;
            female_button.checked();
            male_button.unchecked();
        }

        private void startAction()
        {
            start.setVisible(false);
            instruction.setVisible(false);
            quit.setVisible(false);
            continued.setVisible(false);
            back.setVisible(false);
            remove(back);
            remove(start);
            remove(instruction);
            remove(quit);
            remove(continued);
            this.add(female_button);
            this.add(male_button);
            this.add(warning);
            this.add(create);
            this.add(playerNameArea);
            warning.setVisible(false);
            create.setVisible(true);
            playerNameArea.setVisible(true);
            loadBackground("src\\img\\after_menu_background.png");
            back = new JLabel("",background, JLabel.CENTER);
            back.setBounds(0,0, sizeX, sizeY);
            add(back);
        }

        private void continueAction()
        {
            File file = new File("src\\save\\game\\player.txt");
            if(file.exists())
            {
                try {
                    FileReader fileReader = new FileReader(file);
                    Scanner scanner = new Scanner(fileReader);
                    Player player = new Player(scanner.nextLine(), character);
                    player.setScore(scanner.nextInt());
                    fileReader.close();
                    clip.stop();
                    menu_audio.close();
                    clip.close();
                    GameStage gameStage = new GameStage(sizeX, sizeY, player, GameField.CONTINUE);
                    this.setVisible(false);
                    this.dispose();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void createAction()
        {
            if(character == null)
            {
                warning.setVisible(true);
                return;
            }
            if(playerName.getText().equals(""))
            {
                warning.setVisible(true);
                return;
            }
            Player player = new Player(playerName.getText(), character);
            clip.stop();
            try {
                menu_audio.close();
            } catch (IOException e) {
            e.printStackTrace();
        }
        clip.close();
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
    public void loadBackground(String path){
        background = new ImageIcon(path);
        Image image = background.getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        background = new ImageIcon(image);
    }
}
