package ui;

import model.*;
import model.Event;
import model.exceptions.LogException;
import percistence.JsonReader;
import percistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// Represents the main game frame
public class Game implements LogPrinter {

    JFrame gameFrame = new JFrame();
    JPanel mainPanel;
    Timer timer;
    JPanel playersPanel;
    Container frame;
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JButton button3 = new JButton();
    Font font = new Font("Maiandra GD", Font.BOLD, 14);
    Font font2 = new Font("Maiandra GD", Font.PLAIN, 18);
    Font font3 = new Font("Maiandra GD", Font.PLAIN, 28);
    Font font4 = new Font("Maiandra GD", Font.ROMAN_BASELINE, 28);
    private static ArrayList<Player> displayPlayers = new ArrayList<>();
    Team teamToPlay;
    Team teamToPlayAgainst;
    Team barcelona;
    Team madrid;
    static Team fakeTeamToPlay = new Team("fakeTeam");
    private DefaultListModel playerNames;
    private DefaultListModel displayPlayerNames;
    private static JList dragSrc;
    private static JList dragLoc;
    JPanel buttonPanel;
    JButton nextButton1;
    JButton nextButton2;
    JButton yesButton;
    JButton noButton;
    JButton barcaButton;
    JButton madridButton;
    JButton exitButton;
    JButton barcaButton1;
    JButton madridButton1;
    JPanel teamNamePanel;
    JPanel saveTeamPanel;
    JPanel teamToPlayAgainstPanel;
    JPanel possessionPanel;
    JPanel gameOverPanel;
    JPanel startGamePanel;
    JPanel goalScoredPanel;
    JPanel savedTeamPanel;
    JPanel yellowCardsPanel;
    JPanel yellowCardToPanel;
    JPanel chooseTeamPanel;
    JTextField teamNameField;
    private static DisplayTeams displayTeams = new DisplayTeams();
    private static final String STORE = "./data/teams.json";
    private static JsonWriter writer = new JsonWriter(STORE, displayTeams);
    private static JsonReader reader = new JsonReader(STORE, displayPlayers, displayTeams);
    ImageIcon imageIcon = new ImageIcon("src/main/ui/field.jpg");
    JLabel teamToPlayName;
    JLabel teamToPlayScore;
    JLabel teamToPlayAgainstScore;
    JLabel teamToPlayAgainstName;
    JLabel goalScoredBy;
    JLabel yellowCardGivenToLabel;
    JLabel gameOverLabel;
    JLabel yellowCardToLabel;
    JLabel teamToPlayPossession;
    JLabel teamToPlayAgainstPossession;
    JLabel teamToPlayYellowCards;
    JLabel teamToPlayAgainstYellowCards;
    Statistics gameStatistics = new Statistics(0, 0, 0, 0, 0, 0);

    Player messi = new Player("Messi", 100, 65, 100, 1);
    Player ramos = new Player("Ramos", 30, 99, 60, 3);
    Player neymar = new Player("Neymar", 95, 50, 90, 1);
    Player mbappe = new Player("Mbappe", 93, 60, 80, 1);
    Player ronaldo = new Player("Ronaldo", 99, 70, 99, 1);
    Player suarez = new Player("Suarez", 92, 45, 90, 1);
    Player iniesta = new Player("Iniesta", 85, 65, 90, 2);
    Player xavi = new Player("Xavi", 83, 60, 88, 2);
    Player busquets = new Player("Busquets", 80, 62, 85, 2);
    Player alba = new Player("Alba", 60, 88, 58, 3);
    Player alves = new Player("Alves", 65, 89, 63, 3);
    Player pique = new Player("Pique", 50, 90, 60, 3);
    Player mascherano = new Player("Mascherano", 50, 94, 61, 3);
    Player benzema = new Player("Benzema", 90, 40, 88, 1);
    Player modric = new Player("Modric", 80, 60, 89, 2);
    Player kroos = new Player("Kroos", 78, 58, 88, 2);
    Player casemiro = new Player("Casemiro", 60, 75, 85, 2);
    Player pepe = new Player("Pepe", 45, 92, 58, 3);
    Player mendy = new Player("Mendy", 60, 88, 59, 3);
    Player marcelo = new Player("Marcelo", 65, 88, 55, 3);
    Player casillas = new Player("Casillas", 40, 98, 40, 4);
    Player stegen = new Player("Stegen", 40, 98, 40, 4);
    Player foden = new Player("Foden", 85, 40, 84, 1);
    Player haaland = new Player("Haaland", 90, 40, 83, 1);
    Player silva = new Player("Silva", 85, 40, 86, 1);
    Player bruyne = new Player("Bruyne", 84, 50, 90, 1);
    Player gundogan = new Player("Gundogan", 82, 60, 86, 1);
    Player rodri = new Player("Rodri", 82, 40, 86, 1);
    Player cancelo = new Player("Cancelo", 78, 82, 59, 1);
    Player laporte = new Player("Laporte", 59, 84, 58, 1);
    Player dias = new Player("Dias", 59, 82, 58, 1);
    Player walker = new Player("Walker", 55, 85, 60, 1);
    Player ederson = new Player("Ederson", 41, 69, 90, 1);
    Player bale = new Player("Bale", 87, 40, 86, 1);

    private static int lw = 0;
    private static int st = 1;
    private static int rw = 2;
    private static int lm = 3;
    private static int cm = 4;
    private static int rm = 5;
    private static int lb = 6;
    private static int cb1 = 7;
    private static int cb2 = 8;
    private static int rb = 9;
    private static int gk = 10;

    // constructs a game frame
    // EFFECTS : sets up a window in which SUI-GAME is played
    public Game() {
        initializeFakeTeam();
        gameFrame.setTitle("SUI-GAME");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setSize(1000, 800);
        gameFrame.setLayout(null);
        frame = gameFrame.getContentPane();
        gameFrame.setVisible(true);
        addPanel();
    }

    // MODIFIES : this
    // EFFECTS : creates a main panel with menu
    public void addPanel() {
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1000, 800);
        mainPanel.setBackground(new Color(0x1345));
        mainPanel.setLayout(null);
        frame.add(mainPanel);
        addMenu();
    }


    // MODIFIES : this
    // EFFECTS : adds buttons to the main panel
    public void addMenu() {
        JLabel suiGameLabel = new JLabel("SUI-GAME", SwingConstants.CENTER);
        suiGameLabel.setBounds(50, 50, 900, 200);
        Font font4 = new Font("Maiandra GD", Font.BOLD, 110);
        suiGameLabel.setFont(font4);
        suiGameLabel.setForeground(new Color(0xD7A1F9));
        mainPanel.add(suiGameLabel);
        menuButtonMethod();
        mainPanel.add(button1);
        mainPanel.add(button2);
        mainPanel.add(button3);
        frame.repaint();
    }

    // EFFECTS : initializes menu buttons
    private void menuButtonMethod() {
        button1.setBounds(350, 275, 300, 50);
        button1.setText("SELECT TO MAKE YOUR OWN TEAM");
        button1.setFont(font);
        button1.setBackground(new Color(0xD0F0C0));
        b1Action();
        button2.setBounds(350, 350, 300, 50);
        button2.setText("SELECT TO PLAY WITH A TEAM");
        button2.setFont(font);
        button2.setBackground(new Color(0xD0F0C0));
        b2Action();
        button3.setBounds(350, 425, 300, 50);
        button3.setText("SELECT TO PLAY WITH YOUR TEAM");
        button3.setFont(font);
        button3.setBackground(new Color(0xD0F0C0));
        b3Action();
    }

    // EFFECTS : creates a saveTeamPanel
    public void b3Action() {
        button3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedTeamPanel = new JPanel();
                savedTeamPanel.setBounds(0, 0, 1000, 800);
                savedTeamPanel.setBackground(Color.black);
                savedTeamPanel.setLayout(null);
                mySavedTeamPanelMethod();
            }
        });
    }


    // MODIFIES : this
    // EFFECTS : if reader reads a saved team then move into a new
    //           frame and add button for team, else do nothing
    public void mySavedTeamPanelMethod() {
        try {
            reader.read();
        } catch (IOException e) {
            System.out.println("FAILED");
        }
        ArrayList<Team> myTeams = displayTeams.getMyTeam();
        if (!myTeams.isEmpty()) {
            mainPanel.setVisible(false);
            frame.add(savedTeamPanel);
            JLabel heading = new JLabel(" ", SwingConstants.CENTER);
            heading.setText("CHOOSE YOUR TEAM");
            heading.setFont(font);
            heading.setForeground(Color.WHITE);
            heading.setBounds(300, 250, 400, 50);
            savedTeamPanel.add(heading);
            saveTeamButtons(myTeams);
        } else {
            JOptionPane.showMessageDialog(frame, "NO SAVED TEAM");
        }
    }

    // MODIFIES : this
    // EFFECTS : adds buttons of every saved team to this
    private void saveTeamButtons(ArrayList<Team> myTeams) {
        for (int i = 0; i < myTeams.size(); i++) {
            JButton tempTeamButton = new JButton();
            tempTeamButton.setBounds(350, 350 + i * 50, 300, 40);
            tempTeamButton.setBackground(new Color(0xD0F0C0));
            tempTeamButton.setFocusable(false);
            tempTeamButton.setFont(font);
            tempTeamButton.setText(displayTeams.getMyTeam().get(i).getName());
            savedTeamPanel.add(tempTeamButton);
            int finalI = i;
            tempTeamButton.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    teamToPlay = myTeams.get(finalI);
                    savedTeamPanel.setVisible(false);
                    teamToPlayAgainstPanelMethod1();
                }
            });
        }
    }


    // MODIFIES : this
    // EFFECTS : creates a teamToPlayAgainstPanel and adds to frame
    public void teamToPlayAgainstPanelMethod1() {
        teamToPlayAgainstPanel = new JPanel();
        teamToPlayAgainstPanel.setBounds(0, 0, 1000, 800);
        teamToPlayAgainstPanel.setBackground(new Color(0x355E3B));
        teamToPlayAgainstPanel.setLayout(null);
        frame.add(teamToPlayAgainstPanel);
        teamToPlayAgainstPanelMethod2();
    }

    // MODIFIES : this
    // EFFECTS : sets mainPanel off, creates chooseTeamPanel and adds
    //           to frame
    public void b2Action() {
        button2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                initializeDisplayPlayers();
                teamToPlay = new Team("");
                teamToPlay.makeTeam();
                chooseTeamPanel = new JPanel();
                chooseTeamPanel.setBounds(0, 0, 1000, 800);
                chooseTeamPanel.setBackground(new Color(0x12345));
                chooseTeamPanel.setLayout(null);
                frame.add(chooseTeamPanel);
                chooseTeamPanelMethod();
            }
        });
    }

    // MODIFIES : this
    // EFFECTS : adds buttons and text to chooseTeamPanel
    public void chooseTeamPanelMethod() {
        initializeDisplayTeams();

        JLabel text = new JLabel();
        text.setText("SELECT THE TEAM YOU WANT TO PLAY WITH");
        text.setFont(font);
        text.setForeground(Color.WHITE);
        text.setBounds(325, 275, 400, 50);

        barcaButton1 = new JButton();
        barcaButton1.setText("BARCELONA \n TEAM OVR - " + displayTeams.getPreExisting().get(0).teamOVR());
        barcaButton1.setFont(font);
        barcaButton1.setBounds(350, 350, 300, 70);
        barcaButton1.setBackground(new Color(0xD0F0C0));
        barcaButton1Method();

        madridButton1 = new JButton();
        madridButton1.setText("REAL MADRID \n TEAM OVR - " + displayTeams.getPreExisting().get(1).teamOVR());
        madridButton1.setFont(font);
        madridButton1.setBounds(350, 450, 300, 70);
        madridButton1.setBackground(new Color(0xD0F0C0));
        madridButton1Method();

        chooseTeamPanel.add(text);
        chooseTeamPanel.add(barcaButton1);
        chooseTeamPanel.add(madridButton1);
    }

    // MODIFIES : this, teamToPlay
    // EFFECTS : sets teamToPlay to barcelona
    public void barcaButton1Method() {
        barcaButton1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamToPlay = barcelona;
                chooseTeamPanel.setVisible(false);
                teamToPlayAgainstPanelMethod1();
            }
        });
    }

    // MODIFIES : this, teamToPlay
    // EFFECTS : sets teamToPlay to madrid
    public void madridButton1Method() {
        madridButton1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamToPlay = madrid;
                chooseTeamPanel.setVisible(false);
                teamToPlayAgainstPanelMethod1();
            }
        });

    }


    // MODIFIES : this
    // EFFECTS : adds buttons and panels to frame
    public void b1Action() {
        button1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                initializeDisplayPlayers();
                teamToPlay = new Team("");
                teamToPlay.makeTeam();
                GridLayout layout = new GridLayout(1, 2);
                playersPanel = new JPanel(layout);
                playersPanel.setBounds(0, 0, 1000, 700);
                displayPlayerNames = new DefaultListModel();
                playerNames = new DefaultListModel();
                playerPanelMethod();
                frame.add(playersPanel);
                initializeButtonPanelMethod();
                nextButton1Method();
                nextButton1Action();
                buttonPanel.add(nextButton1);
                frame.add(buttonPanel);
            }
        });
    }

    // EFFECTS : creates a button panel
    private void initializeButtonPanelMethod() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(new Color(0x728C69));
        buttonPanel.setBounds(0, 700, 1000, 300);
    }

    // MODIFIES : nextButton1
    // EFFECTS : initializes nextButton1
    private void nextButton1Method() {
        nextButton1 = new JButton();
        nextButton1.setText("NEXT");
        nextButton1.setFont(font);
        nextButton1.setBounds(395, 15, 200, 40);
        nextButton1.setBackground(new Color(0xD0F0C0));
    }

    // MODIFIES : this
    // EFFECTS : if teamToPlay contains only players then form team
    public void nextButton1Action() {
        nextButton1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultListModel teamNames = (DefaultListModel) dragLoc.getModel();
                if (!teamNames.contains("Empty Position")) {
                    formTeam();
                    playersPanel.setVisible(false);
                    buttonPanel.setVisible(false);
                    teamNamePanelMethod();
                } else {
                    JOptionPane.showMessageDialog(frame, "TEAM INCOMPLETE");
                }
            }
        });
    }

    // MODIFIES : this
    // EFFECTS : adds panel, label, field and button to frame
    private void teamNamePanelMethod() {
        teamNamePanel = new JPanel();
        teamNamePanel.setBackground(new Color(0x12345));
        teamNamePanel.setBounds(0, 0, 1000, 800);
        teamNamePanel.setLayout(null);
        frame.add(teamNamePanel);

        JLabel text = new JLabel();
        text.setText("WHAT WOULD YOU LIKE TO NAME YOUR TEAM?");
        text.setFont(font);
        text.setForeground(Color.WHITE);
        text.setBounds(330, 300, 400, 50);

        teamNameField = new JTextField();
        teamNameField.setFont(font);
        teamNameField.setBackground(Color.WHITE);
        teamNameField.setBounds(350, 350, 300, 50);

        nextButton2 = new JButton();
        nextButton2.setText("NEXT");
        nextButton2.setFont(font);
        nextButton2.setBounds(400, 425, 200, 50);
        nextButton2.setBackground(new Color(0xD0F0C0));
        nextButton2Action();

        teamNamePanel.add(text);
        teamNamePanel.add(teamNameField);
        teamNamePanel.add(nextButton2);
    }

    // MODIFIES : this
    // EFFECTS : initializes saveTeamPanel and adds to saveTeamPanel to frame
    private void nextButton2Action() {
        nextButton2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamNamePanel.setVisible(false);
                teamToPlay.setName(teamNameField.getText());
                saveTeamPanel = new JPanel();
                saveTeamPanel.setBounds(0, 0, 1000, 800);
                saveTeamPanel.setBackground(new Color(0x12345));
                saveTeamPanel.setLayout(null);
                frame.add(saveTeamPanel);
                saveTeamPanelMethod();
            }
        });
    }


    // MODIFIES : this, displayTeams
    // EFFECTS : saves team and sets saveTeamPanel to off
    private void yesButtonAction() {
        yesButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                displayTeams.addToMyTeam(teamToPlay);
                try {
                    writer.open();
                    writer.write(displayTeams);
                    writer.close();
                } catch (FileNotFoundException f) {
                    System.out.println("Unable to write to file: " + STORE);
                }
                saveTeamPanel.setVisible(false);
                teamToPlayAgainstPanelMethod1();
            }
        });
    }


    // MODIFIES : this
    // EFFECTS : adds text and button to saveTeamPanel
    private void saveTeamPanelMethod() {
        JLabel text = new JLabel();
        text.setText("DO WANT TO SAVE YOUR TEAM?");
        text.setFont(font);
        text.setForeground(Color.WHITE);
        text.setBounds(380, 300, 400, 50);

        yesButton = new JButton();
        yesButton.setText("YES");
        yesButton.setFont(font);
        yesButton.setBounds(350, 350, 300, 50);
        yesButton.setBackground(new Color(0xD0F0C0));
        yesButtonAction();

        noButton = new JButton();
        noButton.setText("NO");
        noButton.setFont(font);
        noButton.setBounds(350, 425, 300, 50);
        noButton.setBackground(new Color(0xD0F0C0));
        noButton2Action();

        saveTeamPanel.add(text);
        saveTeamPanel.add(yesButton);
        saveTeamPanel.add(noButton);
    }

    // MODIFIES : this
    // EFFECTS : adds text and buttons to teamToPlayAgainstPanel
    public void teamToPlayAgainstPanelMethod2() {
        initializeDisplayTeams();
        JLabel text = new JLabel(" ", SwingConstants.CENTER);
        text.setText("SELECT THE TEAM YOU WANT TO PLAY AGAINST");
        text.setFont(font);
        text.setForeground(Color.WHITE);
        text.setBounds(300, 275, 400, 50);

        barcaButton = new JButton();
        barcaButton.setText("BARCELONA \n TEAM OVR - " + displayTeams.getPreExisting().get(0).teamOVR());
        barcaButton.setFont(font);
        barcaButton.setBounds(350, 350, 300, 70);
        barcaButton.setBackground(new Color(0xD0F0C0));
        barcaButtonMethod();

        madridButton = new JButton();
        madridButton.setText("REAL MADRID \n TEAM OVR - " + displayTeams.getPreExisting().get(1).teamOVR());
        madridButton.setFont(font);
        madridButton.setBounds(350, 450, 300, 70);
        madridButton.setBackground(new Color(0xD0F0C0));
        madridButtonMethod();

        teamToPlayAgainstPanel.add(text);
        teamToPlayAgainstPanel.add(barcaButton);
        teamToPlayAgainstPanel.add(madridButton);
    }

    // MODIFIES : teamToPlayAgainst
    // EFFECTS : sets teamToPlayAgainst to a team
    public void barcaButtonMethod() {
        barcaButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamToPlayAgainst = displayTeams.getPreExisting().get(0);
                try {
                    startGamePanelMethod();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (UnsupportedAudioFileException ex) {
                    throw new RuntimeException(ex);
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    // MODIFIES : teamToPlayAgainst
    // EFFECTS : sets teamToPlayAgainst to a team
    public void madridButtonMethod() {
        madridButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                teamToPlayAgainst = displayTeams.getPreExisting().get(1);
                try {
                    startGamePanelMethod();
                } catch (InterruptedException
                         | UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    // MODIFIES : this
    // EFFECTS : sets background to frame
    public void startGamePanelMethod() throws InterruptedException,
            UnsupportedAudioFileException, LineUnavailableException, IOException {
        teamToPlayAgainstPanel.setVisible(false);
        try {
            JLabel backgroundImage = new JLabel(new ImageIcon(ImageIO.read(
                    new File("src/main/ui/field1.png"))));
            gameFrame.setContentPane(backgroundImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameStarter();
    }

    // MODIFIES : this
    // EFFECTS : adds startGamePanel to gameFrame
    public void gameStarter() throws InterruptedException,
            UnsupportedAudioFileException, LineUnavailableException, IOException {
        yellowCardsToPanelMethod();
        yellowCardsPanelMethod();
        possessionPanelMethod();
        goalScoredPanelMethod();

        startGamePanel = new JPanel();
        startGamePanel.setBounds(200, 100, 600, 600);
        startGamePanel.setBackground(new Color(0, 0, 0, 150));
        startGamePanel.setLayout(null);
        gameFrame.add(startGamePanel);
        gameStarterMethod();
    }

    // MODIFIES : this
    // EFFECTS : adds a panel to gameFrame
    private void goalScoredPanelMethod() {
        goalScoredPanel = new JPanel();
        goalScoredPanel.setBounds(230, 250, 540, 100);
        goalScoredPanel.setBackground(new Color(0, 49, 82, 160));
        goalScoredPanel.setLayout(null);
        gameFrame.add(goalScoredPanel);
    }

    // MODIFIES : this
    // EFFECTS : adds a panel to gameFrame
    private void possessionPanelMethod() {
        possessionPanel = new JPanel();
        possessionPanel.setBounds(230, 400, 540, 50);
        possessionPanel.setBackground(new Color(0, 49, 82, 160));
        possessionPanel.setLayout(null);
        gameFrame.add(possessionPanel);
    }

    // MODIFIES : this
    // EFFECTS : adds a panel to gameFrame
    private void yellowCardsPanelMethod() {
        yellowCardsPanel = new JPanel();
        yellowCardsPanel.setBounds(230, 500, 540, 50);
        yellowCardsPanel.setBackground(new Color(0, 49, 82, 160));
        yellowCardsPanel.setLayout(null);
        gameFrame.add(yellowCardsPanel);
    }

    // MODIFIES : this
    // EFFECTS : adds a panel to gameFrame
    private void yellowCardsToPanelMethod() {
        yellowCardToPanel = new JPanel();
        yellowCardToPanel.setBounds(230, 600, 540, 100);
        yellowCardToPanel.setBackground(new Color(0, 49, 82, 160));
        yellowCardToPanel.setLayout(null);
        gameFrame.add(yellowCardToPanel);
    }


    // MODIFIES : this
    // EFFECTS : adds labels and panels to this
    public void gameStarterMethod() throws InterruptedException, UnsupportedAudioFileException,
            LineUnavailableException, IOException {
        panelsMethod1();
        panelsMethod2();
        JLabel goalScored = panelsMethod3();
        JLabel possessionLabel = panelsMethod4();
        JLabel yellowCardsLabel = panelsMethod5();
        panelsMethod6();
        yellowCardToPanel.add(yellowCardGivenToLabel);
        yellowCardToPanel.add(yellowCardToLabel);
        yellowCardsPanel.add(yellowCardsLabel);
        yellowCardsPanel.add(teamToPlayYellowCards);
        yellowCardsPanel.add(teamToPlayAgainstYellowCards);
        possessionPanel.add(teamToPlayPossession);
        possessionPanel.add(teamToPlayAgainstPossession);
        possessionPanel.add(possessionLabel);
        goalScoredPanel.add(goalScored);
        goalScoredPanel.add(goalScoredBy);
        startGamePanel.add(teamToPlayAgainstScore);
        startGamePanel.add(teamToPlayScore);
        startGamePanel.add(teamToPlayName);
        startGamePanel.add(teamToPlayAgainstName);
        teamToPlayAgainstPanel.setVisible(false);
        startGamePanel.setVisible(true);
        playGame();
    }

    // EFFECTS : creates a panel
    private void panelsMethod6() {
        yellowCardToLabel = new JLabel("Yellow Card To", SwingConstants.CENTER);
        yellowCardToLabel.setFont(font2);
        yellowCardToLabel.setBackground(new Color(17, 30, 108, 200));
        yellowCardToLabel.setForeground(Color.WHITE);
        yellowCardToLabel.setBounds(190, 0, 160, 50);
        yellowCardToLabel.setOpaque(true);

        yellowCardGivenToLabel = new JLabel(" ", SwingConstants.CENTER);
        yellowCardGivenToLabel.setFont(font2);
        yellowCardGivenToLabel.setForeground(Color.WHITE);
        yellowCardGivenToLabel.setBounds(180, 50, 160, 50);
    }

    // EFFECTS : creates panels
    private JLabel panelsMethod5() {
        teamToPlayAgainstPossession = new JLabel("0%", SwingConstants.CENTER);
        teamToPlayAgainstPossession.setText(gameStatistics.getPossessionT2() + "%");
        teamToPlayAgainstPossession.setFont(font3);
        teamToPlayAgainstPossession.setForeground(Color.WHITE);
        teamToPlayAgainstPossession.setBounds(340, 0, 200, 50);

        JLabel yellowCardsLabel = new JLabel("Yellow Cards", SwingConstants.CENTER);
        yellowCardsLabel.setFont(font2);
        yellowCardsLabel.setBackground(new Color(17, 30, 108, 200));
        yellowCardsLabel.setForeground(Color.WHITE);
        yellowCardsLabel.setBounds(190, 0, 160, 50);
        yellowCardsLabel.setOpaque(true);

        teamToPlayYellowCards = new JLabel("0", SwingConstants.CENTER);
//        teamToPlayYellowCards.setText(String.valueOf(gameStatistics.getYellowT1()));
        teamToPlayYellowCards.setFont(font3);
        teamToPlayYellowCards.setForeground(Color.WHITE);
        teamToPlayYellowCards.setBounds(0, 0, 200, 50);

        teamToPlayAgainstYellowCards = new JLabel("0", SwingConstants.CENTER);
//        teamToPlayAgainstYellowCards.setText(String.valueOf(gameStatistics.getYellowT2()));
        teamToPlayAgainstYellowCards.setFont(font3);
        teamToPlayAgainstYellowCards.setForeground(Color.WHITE);
        teamToPlayAgainstYellowCards.setBounds(340, 0, 200, 50);
        return yellowCardsLabel;
    }

    // EFFECTS : creates panels
    private JLabel panelsMethod4() {
        goalScoredBy = new JLabel(" ", SwingConstants.CENTER);
        goalScoredBy.setFont(font2);
        goalScoredBy.setForeground(Color.WHITE);
        goalScoredBy.setBounds(190, 50, 160, 50);

        JLabel possessionLabel = new JLabel("Possession", SwingConstants.CENTER);
        possessionLabel.setFont(font2);
        possessionLabel.setBackground(new Color(17, 30, 108, 200));
        possessionLabel.setForeground(Color.WHITE);
        possessionLabel.setBounds(190, 0, 160, 50);
        possessionLabel.setOpaque(true);

        teamToPlayPossession = new JLabel("0%", SwingConstants.CENTER);
//        teamToPlayPossession.setText(gameStatistics.getPossessionT1() + "%");
        teamToPlayPossession.setFont(font3);
        teamToPlayPossession.setForeground(Color.WHITE);
        teamToPlayPossession.setBounds(0, 0, 200, 50);
        return possessionLabel;
    }

    // EFFECTS : creates a label
    private JLabel panelsMethod3() {
        JLabel goalScored = new JLabel("Goal Scored By", SwingConstants.CENTER);
        goalScored.setFont(font2);
        goalScored.setBackground(new Color(17, 30, 108, 200));
        goalScored.setForeground(Color.WHITE);
        goalScored.setBounds(190, 0, 160, 50);
        goalScored.setOpaque(true);
        return goalScored;
    }

    // EFFECTS : creates a panel
    private void panelsMethod2() {
        teamToPlayAgainstScore = new JLabel("0", SwingConstants.CENTER);
//        teamToPlayAgainstScore.setText(String.valueOf(gameStatistics.getGoalT2()));
        teamToPlayAgainstScore.setFont(font3);
        teamToPlayAgainstScore.setBackground(new Color(15, 82, 186));
        teamToPlayAgainstScore.setForeground(Color.WHITE);
        teamToPlayAgainstScore.setBounds(320, 45, 60, 60);
        teamToPlayAgainstScore.setOpaque(true);
    }

    // EFFECTS : creates panels
    private void panelsMethod1() {
        teamToPlayName = new JLabel("hello", SwingConstants.CENTER);
        teamToPlayName.setText(teamToPlay.getName());
        teamToPlayName.setFont(font2);
        teamToPlayName.setBackground(new Color(0x710C04));
        teamToPlayName.setForeground(Color.WHITE);
        teamToPlayName.setBounds(30, 50, 200, 50);
        teamToPlayName.setOpaque(true);

        teamToPlayScore = new JLabel("0", SwingConstants.CENTER);
//        teamToPlayScore.setText(String.valueOf(gameStatistics.getGoalT1()));
        teamToPlayScore.setFont(font3);
        teamToPlayScore.setBackground(new Color(15, 82, 186));
        teamToPlayScore.setForeground(Color.WHITE);
        teamToPlayScore.setBounds(220, 45, 60, 60);
        teamToPlayScore.setOpaque(true);

        teamToPlayAgainstName = new JLabel("hello", SwingConstants.CENTER);
        teamToPlayAgainstName.setText(teamToPlayAgainst.getName());
        teamToPlayAgainstName.setFont(font2);
        teamToPlayAgainstName.setBackground(new Color(0x710C04));
        teamToPlayAgainstName.setForeground(Color.WHITE);
        teamToPlayAgainstName.setBounds(370, 50, 200, 50);
        teamToPlayAgainstName.setOpaque(true);
    }


    // MODIFIES : this
    // EFFECTS : starts game
    public void playGame() throws InterruptedException,
            UnsupportedAudioFileException, IOException, LineUnavailableException {
        long startTime = System.currentTimeMillis();
        long end = startTime + 47000;
        Random rand = new Random();
        if (timer != null && timer.isRunning()) {
            return;
        }
        int index = 0;
        Clip clip1 = playAudioMethod();
        timer = new javax.swing.Timer(50, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMethod1(rand);
                if (System.currentTimeMillis() >= end) {
                    timer.stop();
                    endGameMethod(clip1);
                }
            }
        });
        timer.start();
    }


    // EFFECTS : if current time mod 5000 < 200 then initialise game
    private void gameMethod1(Random rand) {
        if (System.currentTimeMillis() % 5000 < 200) {

            int time = rand.nextInt(8) * 1000;
            try {
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            norm(rand);
        }
    }

    // EFFECTS : stops clip1 and ends game
    private void endGameMethod(Clip clip1) {
        try {
            clip1.stop();
            stopGameSound();
        } catch (InterruptedException | UnsupportedAudioFileException | IOException
                 | LineUnavailableException ex) {
            throw new RuntimeException(ex);
        } catch (LogException e) {
            throw new RuntimeException(e);
        }
    }

    // EFFECTS : plays background audio
    private Clip playAudioMethod() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException, InterruptedException {
        File newFile = new File("src/main/ref_whistle.wav");
        AudioInputStream newAudioStream = AudioSystem.getAudioInputStream(newFile);
        Clip newClip = AudioSystem.getClip();
        newClip.open(newAudioStream);
        newClip.start();

        TimeUnit.SECONDS.sleep(1);

        File file1 = new File("src/main/finalcheering.wav");
        AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file1);
        Clip clip1 = AudioSystem.getClip();
        clip1.open(audioStream1);
        clip1.start();
        return clip1;
    }

    // EFFECTS : plays end game audio
    public void stopGameSound() throws InterruptedException,
            UnsupportedAudioFileException, IOException, LineUnavailableException, LogException {


        TimeUnit.SECONDS.sleep(1);
        File file = new File("src/main/HEHEHESUIIII.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
        TimeUnit.SECONDS.sleep(3);
        gameOverPanel();

    }

    // MODIFIES : this
    // EFFECTS : adds gameOverPanel to this
    public void gameOverPanel() throws LogException {
        yellowCardToPanel.setVisible(false);
        yellowCardsPanel.setVisible(false);
        possessionPanel.setVisible(false);
        goalScoredPanel.setVisible(false);
        startGamePanel.setVisible(false);

        gameFrame.setBackground(new Color(0x12345));

        gameOverPanel = new JPanel();
        gameOverPanel.setBounds(0, 0, 1000, 800);
        gameOverPanel.setBackground(new Color(0x12345));
        gameOverPanel.setLayout(null);
        gameFrame.add(gameOverPanel);
        gameOverPanelMethod();
    }

    // MODIFIES : this
    // EFFECTS : adds label and button to this
    public void gameOverPanelMethod() throws LogException {
        gameOverLabel = new JLabel(" ", SwingConstants.CENTER);
        gameOverLabel.setFont(font4);
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setBackground(new Color(0xD8B863));
        gameOverLabel.setBounds(250, 275, 500, 100);
        gameOverLabel.setOpaque(true);
        setTextMethod();
        gameOverPanel.add(gameOverLabel);

//        playAgainButton = new JButton();
//        playAgainButton.setText("PLAY AGAIN");
//        playAgainButton.setFont(font);
//        playAgainButton.setBounds(550, 475, 150, 75);
//        playAgainButtonAction();

        exitButton = new JButton();
        exitButton.setText("EXIT");
        exitButton.setFont(font);
        exitButton.setBounds(425, 475, 150, 75);
        exitButton.setBackground(new Color(0xD0F0C0));
        exitButtonAction();

//        gameOverPanel.add(playAgainButton);
        gameOverPanel.add(exitButton);
    }

//    public void playAgainButtonAction() {
//        playAgainButton.addActionListener(new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                gameFrame.setContentPane(frame);
//                addPanel();
//            }
//        });
//    }

    // EFFECTS : ends code
    public void exitButtonAction() throws LogException {
        printLog(EventLog.getInstance());
        exitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


    // MODIFIES : this
    // EFFECTS : checks for goal difference and sets text
    public void setTextMethod() {
        if (gameStatistics.getGoalT1() > gameStatistics.getGoalT2()) {
            gameOverLabel.setText(teamToPlay.getName().toUpperCase() + " HAS WON");
        } else if (gameStatistics.getGoalT2() > gameStatistics.getGoalT1()) {
            gameOverLabel.setText(teamToPlayAgainst.getName().toUpperCase() + " HAS WON");
        } else if (gameStatistics.getGoalT1() == gameStatistics.getGoalT2()) {
            gameOverLabel.setText("IT WAS A TIE");
        }
    }


    // MODIFIES : gameStatistics
    // EFFECTS : sets goal, yellow and possession depending on team ratings
    private void norm(Random rand) {
        if (gameStatistics.probGoal(teamToPlay, teamToPlayAgainst) > 93) {
            probGoalMethod1(rand);
        } else if (gameStatistics.probGoal(teamToPlayAgainst, teamToPlay) > 93) {
            probGoalMethod2(rand);
        }
    }

    // MODIFIES : gameStatistics
    // EFFECTS : sets goal, yellow and possession in favor of teamToPlay game
    private void probGoalMethod1(Random rand) {
        gameStatistics.setGoalT1();
        teamToPlayScore.setText(String.valueOf(gameStatistics.getGoalT1()));
        goalScoredBy.setText(teamToPlay.namesOnly().get(rand.nextInt(11)));
        if (gameStatistics.probYellow(teamToPlayAgainst, teamToPlay) > 95) {
            gameStatistics.setYellowT2();
            teamToPlayAgainstYellowCards.setText(String.valueOf(gameStatistics.getYellowT2()));
            yellowCardGivenToLabel.setText(teamToPlayAgainst.namesOnly().get(rand.nextInt(11)));
        }
        gameStatistics.probPossession(teamToPlay, teamToPlayAgainst);
        teamToPlayPossession.setText(gameStatistics.getPossessionT1() + "%");
        teamToPlayAgainstPossession.setText(gameStatistics.getPossessionT2() + "%");
        playGameAudio();
    }

    // MODIFIES : gameStatistics
    // EFFECTS : sets goal, yellow and possession in favor of teamToPlay game
    private void probGoalMethod2(Random rand) {
        gameStatistics.setGoalT2();
        teamToPlayAgainstScore.setText(String.valueOf(gameStatistics.getGoalT2()));
        goalScoredBy.setText(teamToPlayAgainst.namesOnly().get(rand.nextInt(11)));
        if (gameStatistics.probYellow(teamToPlay, teamToPlayAgainst) > 95) {
            gameStatistics.setYellowT1();
            teamToPlayYellowCards.setText(String.valueOf(gameStatistics.getYellowT1()));
            yellowCardGivenToLabel.setText(teamToPlay.namesOnly().get(rand.nextInt(11)));
        }
        gameStatistics.probPossession(teamToPlay, teamToPlayAgainst);
        teamToPlayPossession.setText(gameStatistics.getPossessionT1() + "%");
        teamToPlayAgainstPossession.setText(gameStatistics.getPossessionT2() + "%");
        playGameAudio();
    }

    // EFFECTS : plays goal audio
    private void playGameAudio() {
        File file = new File("src/main/gooallaazoo.wav");
        AudioInputStream audioStream = null;
        try {
            audioStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }
        try {
            clip.open(audioStream);
        } catch (LineUnavailableException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        clip.start();
    }

    // MODIFIES : this
    // EFFECTS : sets saveTeamPanel off
    private void noButton2Action() {
        noButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTeamPanel.setVisible(false);
                teamToPlayAgainstPanelMethod1();
            }
        });
    }

    // MODIFIES : teamToPlay
    // EFFECTS : for name in names adds player in teamToPlay
    private void formTeam() {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < dragLoc.getModel().getSize(); i++) {
            names.add((String) dragLoc.getModel().getElementAt(i));
        }
        for (int i = 0; i < 11; i++) {
            for (Player p : displayPlayers) {
                if (names.get(i).contains(p.getName())) {
                    teamToPlay.addAndReplace(p, i);
                }
            }
        }
    }

    // MODIFIES : this
    // EFFECTS : initializes JList and adds to playersPanel
    private void playerPanelMethod() {
        addToDisplayPlayerNames();
        addToPlayerNames();

        dragSrc = new JList();
        dragSrc.setModel(displayPlayerNames);
        dragSrc.setBackground(new Color(0xFFFAFA));
        dragSrc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dragSrc.setBorder(BorderFactory.createTitledBorder("PLAYERS"));
        dragSrc.setFixedCellHeight(20);
        dragSrc.setDragEnabled(true);
        dragSrc.setTransferHandler(new TransferPlayers());
        dragSrc.setDropMode(DropMode.INSERT);

        dragLoc = new JList(playerNames);
        dragLoc.setBackground(new Color(0xD0F0C0));
        dragLoc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dragLoc.setBorder(BorderFactory.createTitledBorder("TEAM"));
        dragLoc.setFixedCellHeight(50);
        dragLoc.setTransferHandler(new TransferPlayers());

        playersPanel.add(dragSrc);
        playersPanel.add(dragLoc);
    }

    // MODIFIES : displayPlayers
    // EFFECTS : adds players to displayPlayers
    private void initializeDisplayPlayers() {
        displayPlayers.add(messi);
        displayPlayers.add(ramos);
        displayPlayers.add(neymar);
        displayPlayers.add(mbappe);
        displayPlayers.add(ronaldo);
        displayPlayers.add(suarez);
        displayPlayers.add(busquets);
        displayPlayers.add(xavi);
        displayPlayers.add(alba);
        displayPlayers.add(alves);
        displayPlayers.add(mascherano);
        displayPlayers.add(pique);
        displayPlayers.add(benzema);
        displayPlayers.add(modric);
        displayPlayers.add(kroos);
        addMorePlayers();
    }

    // MODIFIES : displayPlayers
    // EFFECTS : adds players to displayPlayers
    private void addMorePlayers() {
        displayPlayers.add(casemiro);
        displayPlayers.add(pepe);
        displayPlayers.add(mendy);
        displayPlayers.add(marcelo);
        displayPlayers.add(casillas);
        displayPlayers.add(stegen);
        displayPlayers.add(bale);
        displayPlayers.add(iniesta);
        displayPlayers.add(foden);
        displayPlayers.add(haaland);
        displayPlayers.add(silva);
        displayPlayers.add(bruyne);
        displayPlayers.add(gundogan);
        displayPlayers.add(rodri);
        displayPlayers.add(cancelo);
        displayPlayers.add(laporte);
        displayPlayers.add(dias);
        displayPlayers.add(walker);
        displayPlayers.add(ederson);
    }


    // MODIFIES : displayPlayerNames
    // EFFECTS : for Player in displayPlayer add player name to displayPlayerNames
    private void addToDisplayPlayerNames() {
        for (Player p : displayPlayers) {
            displayPlayerNames.addElement(p.getName());
        }
    }

    // MODIFIES : playerNames
    // EFFECTS : for player in teamToPlay add player name to playerNames
    private void addToPlayerNames() {
        for (Object s : teamToPlay.namesOnly()) {
            playerNames.addElement(s);
        }
    }

    // MODIFIES : displayTeams
    // EFFECTS : adds teams to displayTeams
    private void initializeDisplayTeams() {
        displayTeams = new DisplayTeams();
        initializeBarca();
        initializeMadrid();
        displayTeams.addToPreExisting(barcelona);
        displayTeams.addToPreExisting(madrid);
    }

    // MODIFIES : barcelona
    // EFFECTS : adds to players to barcelona
    private void initializeBarca() {
        barcelona = new Team("Barcelona");
        barcelona.makeTeam();

        barcelona.addAndReplace(neymar, lw);
        barcelona.addAndReplace(suarez, st);
        barcelona.addAndReplace(messi, rw);
        barcelona.addAndReplace(iniesta, lm);
        barcelona.addAndReplace(busquets, cm);
        barcelona.addAndReplace(xavi, rm);
        barcelona.addAndReplace(alba, lb);
        barcelona.addAndReplace(pique, cb1);
        barcelona.addAndReplace(mascherano, cb2);
        barcelona.addAndReplace(alves, rb);
        barcelona.addAndReplace(stegen, gk);
    }

    // MODIFIES : madrid
    // EFFECTS : adds players to madrid
    private void initializeMadrid() {
        madrid = new Team("Madrid");
        madrid.makeTeam();

        madrid.addAndReplace(ronaldo, lw);
        madrid.addAndReplace(benzema, st);
        madrid.addAndReplace(bale, rw);
        madrid.addAndReplace(kroos, lm);
        madrid.addAndReplace(casemiro, cm);
        madrid.addAndReplace(modric, rm);
        madrid.addAndReplace(marcelo, lb);
        madrid.addAndReplace(ramos, cb1);
        madrid.addAndReplace(pepe, cb2);
        madrid.addAndReplace(mendy, rb);
        madrid.addAndReplace(casillas, gk);
    }


    // EFFECTS : runs Game
    public static void main(String[] args) {
        new Game();
    }

    @Override
    public void printLog(EventLog el) throws LogException {
        for (Event e : el) {
            System.out.println(e.toString());
        }
    }

    // EFFECTS : returns a jList
    public static JList getDragSrc() {
        return dragSrc;
    }

    // EFFECTS : returns a jList
    public static JList getDragLoc() {
        return dragLoc;
    }

    // EFFECTS : initializes a team
    public void initializeFakeTeam() {
        fakeTeamToPlay.makeTeam();
    }

    // EFFECTS : replaces players in a team
    public static void replacePlayer(String playerToAdd,String playerToRemove) {
        Player player1 = new Player("",0,0,0,0);
        Player player2 = new Player("",0,0,0,0);
        for (Player p : displayPlayers) {
            if (p.getName().equals(playerToAdd)) {
                player1 = p;
            }
        }
        for (Player p : displayPlayers) {
            if (p.getName().equals(playerToRemove)) {
                player2 = p;
            }
        }
        fakeTeamToPlay.replace(player1, player2);
    }

    // EFFECTS : adds player to a team
    public static void addPlayer(String playerName, int pos) {
        for (Player p : displayPlayers) {
            if (p.getName().equals(playerName)) {
                fakeTeamToPlay.add(p,pos);
            }
        }
    }
}


