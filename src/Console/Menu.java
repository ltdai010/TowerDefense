package Console;

import PortableEntity.GameEntity;
import Screen.GameField;
import Screen.GameStage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Button.ControlButton;;

public class Menu extends JFrame{
    private ControlButton continued;
    private ControlButton start;
    private ControlButton instruction;
    private ControlButton quit;
    private ControlButton female_button;
    private ControlButton male_button;
    private ControlButton leaderBoard_button;
    private ControlButton back_button;
    private ControlButton next_button;
    private ControlButton pre_button;
    private AudioInputStream menu_audio;
    private Clip clip;
    private ArrayList<JLabel> instruction_list;
    private ControlButton create;
    private ImageIcon backgroundImage;
    private JLabel background;
    private JLabel warning;
    private JLabel playerNameArea;
    private JTextArea name;
    private JTextArea score;
    private JTextArea stage;
    private JTextField playerName;
    private final int sizeX = 1206;
    private final int sizeY = 630;
    private int instructionPage;
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
        initText();
        initButton();
        initAudio();
        loadBackground("src\\img\\background.png");
        background = new JLabel("", backgroundImage, JLabel.CENTER);
        background.setVisible(false);
        add(background);
        background.setBounds(0,0, sizeX, sizeY);
        background.setVisible(true);
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

        private void initText()
        {
            setInstructionScreen();
            ImageIcon icon = new ImageIcon("src\\img\\character-name-area.png");
            int icon_width = icon.getIconWidth();
            int icon_length = icon.getIconHeight();
            playerName = new JTextField();
            playerName.setOpaque(false);
            playerName.setBounds(440, 290, icon_width / 4,icon_length / 4);
            playerName.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            name = new JTextArea("NAME");
            score = new JTextArea("SCORE");
            stage = new JTextArea("STAGE");
            name.setOpaque(false);
            score.setOpaque(false);
            stage.setOpaque(false);
            name.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            score.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            stage.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
            this.add(name);
            this.add(score);
            this.add(stage);
            name.setBounds(250, 180, 300, 350);
            score.setBounds(850, 180, 300, 350);
            stage.setBounds(550, 180, 300, 350);
            name.setForeground(new Color(217, 171, 7));
            score.setForeground(new Color(217, 171, 7));
            stage.setForeground(new Color(217, 171, 7));
            name.setVisible(false);
            score.setVisible(false);
            stage.setVisible(false);
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
        }

        private void setInstructionScreen()
        {
            instruction_list = new ArrayList<>();
            for(int i = 1; i < 6; ++i)
            {
                ImageIcon ii = new ImageIcon("src\\img\\instruction-" + i +".png");
                Image image = ii.getImage().getScaledInstance(GameEntity.SCREENWIDTH, GameEntity.SCREENHEIGHT,
                        Image.SCALE_SMOOTH);
                instruction_list.add(new JLabel(new ImageIcon(image)));
            }
        }

        private void initButton()
        {
            pre_button = new ControlButton("src\\img\\prew.png", "src\\img\\roll_prew.png", 100, 100);
            next_button = new ControlButton("src\\img\\next.png", "src\\img\\roll_next.png", 100, 100);
            pre_button.setBounds(100, 500, 100, 100);
            next_button.setBounds(1000, 500, 100, 100);
            pre_button.setVisible(false);
            next_button.setVisible(false);
            this.add(next_button);
            this.add(pre_button);
            back_button = new ControlButton("src\\img\\close.png", "src\\img\\roll-close.png", 50, 50);
            add(back_button);
            back_button.setBounds(1120, 10, 50, 50);
            back_button.setVisible(false);
            leaderBoard_button = new ControlButton("src\\img\\ranking_icon.png", "src\\img\\roll_ranking_icon.png", 50, 50);
            add(leaderBoard_button);
            leaderBoard_button.setBounds(30, 10, 50, 50);
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
            leaderBoard_button.addActionListener(e -> {
                try {
                    leaderBoardAction();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
            back_button.addActionListener(e -> backAction());
            next_button.addActionListener(e-> nextAction());
            pre_button.addActionListener(e -> preAction());
        }

        private void nextAction()
        {
            if(++instructionPage < instruction_list.size())
            {
                background.setVisible(false);
                remove(background);
                background = instruction_list.get(instructionPage);
                background.setVisible(true);
                add(background);
                background.setBounds(0, 0, GameEntity.SCREENWIDTH, GameEntity.SCREENHEIGHT);
            }
            else
            {
                instructionPage--;
            }
        }

        private void preAction()
        {
            if(--instructionPage >= 0)
            {
                background.setVisible(false);
                remove(background);
                background = instruction_list.get(instructionPage);
                background.setVisible(true);
                add(background);
                background.setBounds(0, 0, GameEntity.SCREENWIDTH, GameEntity.SCREENHEIGHT);
            }
            else
            {
                instructionPage++;
            }
        }

        private void backAction()
        {
            destroy();
            Menu menu = new Menu();
        }

        private void destroy()
        {
            this.removeAll();
            this.clip.stop();
            this.clip.close();
            this.setVisible(false);
            this.dispose();
        }

        public void leaderBoardAction() throws FileNotFoundException {
            destroyMainMenu();
            back_button.setVisible(true);
            listFilesForFolder(new File("src\\save\\score\\"));
            name.setVisible(true);
            score.setVisible(true);
            stage.setVisible(true);
            loadBackground("src\\img\\score_board.png");
            background = new JLabel("", backgroundImage, JLabel.CENTER);
            background.setBounds(0,0, sizeX, sizeY);
            add(background);
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

        public void startAction()
        {
            destroyMainMenu();
            this.add(female_button);
            this.add(male_button);
            this.add(warning);
            this.add(create);
            this.add(playerNameArea);
            back_button.setVisible(true);
            warning.setVisible(false);
            create.setVisible(true);
            playerNameArea.setVisible(true);
            loadBackground("src\\img\\after_menu_background.png");
            background = new JLabel("", backgroundImage, JLabel.CENTER);
            background.setBounds(0,0, sizeX, sizeY);
            add(background);
        }

        private void continueAction()
        {
            File file = new File("src\\save\\game\\player.txt");
            if(file.exists())
            {
                try {
                    FileReader fileReader = new FileReader(file);
                    Scanner scanner = new Scanner(fileReader);
                    Player player = new Player(scanner.nextLine(), scanner.nextLine());
                    player.setCoin(scanner.nextInt());
                    fileReader.close();
                    clip.stop();
                    menu_audio.close();
                    clip.close();
                    GameStage gameStage = new GameStage(sizeX, sizeY, player, GameField.CONTINUE);
                    this.setVisible(false);
                    this.dispose();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    public void listFilesForFolder(File folder) throws FileNotFoundException {
        ArrayList<Player> arrayList = new ArrayList<>();
        for (File fileEntry : folder.listFiles()) {
            Scanner scanner = new Scanner(fileEntry);
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                arrayList.add(new Player(scanner.nextInt(), scanner.nextInt(), fileEntry.getName().substring(0, fileEntry.getName().length() - 4)));
            }
        }
        Collections.sort(arrayList);
        int i = 0;
        while (i < 5 && i < arrayList.size())
        {
            name.append("\n" + arrayList.get(i).getName());
            score.append("\n" + arrayList.get(i).getScore());
            stage.append("\n" + arrayList.get(i).getStage());
            ++i;
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
        destroyMainMenu();
        instructionPage = 0;
        background = instruction_list.get(instructionPage);
        background.setBounds(0, 0, GameEntity.SCREENWIDTH, GameEntity.SCREENHEIGHT);
        add(background);
        back_button.setVisible(true);
        pre_button.setVisible(true);
        next_button.setVisible(true);
    }

    public void loadBackground(String path){
        backgroundImage = new ImageIcon(path);
        Image image = backgroundImage.getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(image);
    }

    private void destroyMainMenu()
    {
        start.setVisible(false);
        instruction.setVisible(false);
        quit.setVisible(false);
        continued.setVisible(false);
        background.setVisible(false);
        leaderBoard_button.setVisible(false);
        remove(background);
        remove(start);
        remove(instruction);
        remove(quit);
        remove(continued);
        remove(leaderBoard_button);
    }
}
