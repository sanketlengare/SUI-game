//package ui;
//
//import model.*;
//import percistence.JsonReader;
//import percistence.JsonWriter;
//
//import javax.sound.sampled.*;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
//// represents the main window in which the game is played
//public class Main {
//
//    private static int lw = 0;
//    private static int st = 1;
//    private static int rw = 2;
//    private static int lm = 3;
//    private static int cm = 4;
//    private static int rm = 5;
//    private static int lb = 6;
//    private static int cb1 = 7;
//    private static int cb2 = 8;
//    private static int rb = 9;
//    private static int gk = 10;
//
//
//    private static ArrayList<Player> displayPlayers;
//    private static ArrayList<String> playerAndOVR = new ArrayList<>();
//    private static ArrayList<String> playerName = new ArrayList<>();
//    private static Team teamToPlayAgainst = new Team("");
//    private static ArrayList<String> displayNames = new ArrayList<>();
//    private static Statistics gameStats = new Statistics(0, 0,
//            0, 0, 0, 0);
//
//    private static DisplayTeams displays = new DisplayTeams();
//
//    private static final String STORE = "./data/teams.json";
//
//    private static JsonWriter writer = new JsonWriter(STORE, displays);
//    private static JsonReader reader;
//
//
//    public static void main(String[] args) throws InterruptedException,
//            LineUnavailableException, IOException, UnsupportedAudioFileException {
//
//        Scanner sc = new Scanner(System.in);
//
//        Player messi = new Player("Messi", 100, 65, 100, 1);
//        Player ramos = new Player("Ramos", 30, 99, 60, 3);
//        Player neymar = new Player("Neymar", 95, 50, 90, 1);
//        Player mbappe = new Player("Mbappe", 93, 60, 80, 1);
//        Player ronaldo = new Player("Ronaldo", 99, 70, 99, 1);
//        Player suarez = new Player("Suarez", 92, 45, 90, 1);
//        Player iniesta = new Player("Iniesta", 85, 65, 90, 2);
//        Player xavi = new Player("Xavi", 83, 60, 88, 2);
//        Player busquets = new Player("Busquets", 80, 62, 85, 2);
//        Player alba = new Player("Alba", 60, 88, 58, 3);
//        Player alves = new Player("Alves", 65, 89, 63, 3);
//        Player pique = new Player("Pique", 50, 90, 60, 3);
//        Player mascherano = new Player("Mascherano", 50, 94, 61, 3);
//        Player benzema = new Player("Benzema", 90, 40, 88, 1);
//        Player modric = new Player("Modric", 80, 60, 89, 2);
//        Player kroos = new Player("Kroos", 78, 58, 88, 2);
//        Player casemiro = new Player("Casemiro", 60, 75, 85, 2);
//        Player pepe = new Player("Pepe", 45, 92, 58, 3);
//        Player mendy = new Player("Mendy", 60, 88, 59, 3);
//        Player marcelo = new Player("Marcelo", 65, 88, 55, 3);
//        Player casillas = new Player("Casillas", 40, 98, 40, 4);
//        Player stegen = new Player("Stegen", 40, 98, 40, 4);
//        Player foden = new Player("Foden", 85, 40, 84, 1);
//        Player haaland = new Player("Haaland", 90, 40, 83, 1);
//        Player silva = new Player("Silva", 85, 40, 86, 1);
//        Player bruyne = new Player("Bruyne", 84, 50, 90, 1);
//        Player gundogan = new Player("Gundogan", 82, 60, 86, 1);
//        Player rodri = new Player("Rodri", 82, 40, 86, 1);
//        Player cancelo = new Player("Cancelo", 78, 82, 59, 1);
//        Player laporte = new Player("Laporte", 59, 84, 58, 1);
//        Player dias = new Player("Dias", 59, 82, 58, 1);
//        Player walker = new Player("Walker", 55, 85, 60, 1);
//        Player ederson = new Player("Ederson", 41, 69, 90, 1);
//        Player bale = new Player("Bale", 87, 40, 86, 1);
//
//
//
//        // FC barcelona
//        Team barcelona = new Team("Barcelona");
//        barcelona.makeTeam();
//
//        barcelona.addAndReplace(neymar, lw);
//        barcelona.addAndReplace(suarez, st);
//        barcelona.addAndReplace(messi, rw);
//        barcelona.addAndReplace(iniesta, lm);
//        barcelona.addAndReplace(busquets, cm);
//        barcelona.addAndReplace(xavi, rm);
//        barcelona.addAndReplace(alba, lb);
//        barcelona.addAndReplace(pique, cb1);
//        barcelona.addAndReplace(mascherano, cb2);
//        barcelona.addAndReplace(alves, rb);
//        barcelona.addAndReplace(stegen, gk);
//
//
//        // Real Madrid
//        Team madrid = new Team("Madrid");
//        madrid.makeTeam();
//
//        madrid.addAndReplace(ronaldo, lw);
//        madrid.addAndReplace(benzema, st);
//        madrid.addAndReplace(bale, rw);
//        madrid.addAndReplace(kroos, lm);
//        madrid.addAndReplace(casemiro, cm);
//        madrid.addAndReplace(modric, rm);
//        madrid.addAndReplace(marcelo, lb);
//        madrid.addAndReplace(ramos, cb1);
//        madrid.addAndReplace(pepe, cb2);
//        madrid.addAndReplace(mendy, rb);
//        madrid.addAndReplace(casillas, gk);
//
//        displays.addToPreExisting(barcelona);
//        displays.addToPreExisting(madrid);
//
//
//        displayPlayers = new ArrayList<>();
//
//
//        displayPlayers.add(messi);
//        displayPlayers.add(ramos);
//        displayPlayers.add(neymar);
//        displayPlayers.add(mbappe);
//        displayPlayers.add(ronaldo);
//        displayPlayers.add(suarez);
//        displayPlayers.add(busquets);
//        displayPlayers.add(xavi);
//        displayPlayers.add(alba);
//        displayPlayers.add(alves);
//        displayPlayers.add(mascherano);
//        displayPlayers.add(pique);
//        displayPlayers.add(benzema);
//        displayPlayers.add(modric);
//        displayPlayers.add(kroos);
//        displayPlayers.add(casemiro);
//        displayPlayers.add(pepe);
//        displayPlayers.add(mendy);
//        displayPlayers.add(marcelo);
//        displayPlayers.add(casillas);
//        displayPlayers.add(stegen);
//        displayPlayers.add(bale);
//        displayPlayers.add(foden);
//        displayPlayers.add(haaland);
//        displayPlayers.add(silva);
//        displayPlayers.add(bruyne);
//        displayPlayers.add(gundogan);
//        displayPlayers.add(rodri);
//        displayPlayers.add(cancelo);
//        displayPlayers.add(laporte);
//        displayPlayers.add(dias);
//        displayPlayers.add(walker);
//        displayPlayers.add(ederson);
//        displayPlayers.add(iniesta);
//
//        reader = new JsonReader(STORE, displayPlayers, displays);
//
//        for (Player p : displayPlayers) {
//            displayNames.add(p.getName());
//        }
//
//        System.out.println("##########################");
//        System.out.println("#  Welcome to Siu Game!  #");
//        System.out.println("##########################");
//
//        boolean running = true;
//
//        while (running) {
//            Team teamToPlay = new Team("");
//            teamToPlay.makeTeam();
//            gameStats.refreshGame();
//            System.out.println("\n\tPress enter to start");
//
//
//            String enter = sc.nextLine();
//            if (enter.isEmpty()) {
//                System.out.println("1. Press 1 to create your own team\n2. Press 2 to play with a ready team \n"
//                        + "3. Press 3 to load up saved team\n4. Press 4 to view your custom made team");
//
//                String one = sc.nextLine();
//                if (one.equals("1")) {
//                    for (Player p : displayPlayers) {
//                        p.playerOVR();
//                        playerAndOVR.add(p.getName() + " " + p.getRating());
//                    }
//                    System.out.println("###############################################################");
//                    System.out.print("#  Player Name | Rating   \n");
//                    for (String p : playerAndOVR) {
//                        String max = Collections.max(playerAndOVR, Comparator.comparing(String::length));
//                        int maxLength = max.length();
//                        int numSpacesToAdd = maxLength - p.length();
//                        String spacesAdded = String.join("",
//                                Collections.nCopies(numSpacesToAdd + 1, " "));
//                        System.out.println("#\t" + p.substring(0, p.length() - 2) + spacesAdded
//                                + "\t" + p.substring(p.length() - 2));
//                    }
//                    System.out.println("###############################################################");
//
//                    while (teamToPlay.getPlayerNames().contains("Empty Position")) {
//                        System.out.println("\n Choose the player by the exact spelling as above");
//                        String name = sc.nextLine();
//                        System.out.println("\n Choose the position from 1-11"
//                                + "\n" + "Starting at 1-left wing to 11-goal keeper");
//                        Integer pos = Integer.parseInt(sc.nextLine());
//                        int index = 0;
//                        for (int i = 0; i < displayPlayers.size(); i++) {
//                            if (displayPlayers.get(i).getName().contains(name)) {
//                                teamToPlay.addAndReplace(displayPlayers.get(i), Integer.valueOf(pos) - 1);
//                                index = displayPlayers.indexOf(displayPlayers.get(i));
//                                if (teamToPlay.containsPlayer(displayPlayers.get(i))) {
//                                    displayPlayers.remove(index);
//                                }
//                                playerName = teamToPlay.namesOnly();
//                                for (String s : playerName) {
//                                    System.out.println(s);
//                                }
//                            }
//                        }
//                    }
//
//
//                    System.out.println("\n What would you like to call your team?");
//                    String teamName = sc.nextLine();
//                    teamToPlay.setName(teamName);
//                    System.out.println("\n Your team is ready");
//                    System.out.println("Would you like to save your team? (y/n)");
//                    String save = sc.nextLine();
//
//                    if (save.equals("y")) {
//                        displays.addToMyTeam(teamToPlay);
//                        try {
//                            writer.open();
//                            writer.write(displays);
//                            writer.close();
//                            System.out.println("Saved teams to " + STORE);
//                        } catch (FileNotFoundException e) {
//                            System.out.println("Unable to write to file: " + STORE);
//                        }
//                    } else {
//                        System.out.println("Your team is not saved");
//                    }
//
//                } else if (one.equals("2")) {
//                    System.out.println("\n Choose the team you want to play by selecting the number besides it");
//
//                    for (int i = 0; i < displays.getPreExisting().size(); i++) {
//                        System.out.println("\n" + (i + 1) + ". " + displays.getPreExisting().get(i).getName()
//                                + " Team OVR - " + displays.getPreExisting().get(i).teamOVR());
//                    }
//
////                System.out.println("\n 1. " + displays.getPreExisting().get(0).getName());
////                System.out.println("\n 2. " + displays.getPreExisting().get(1).getName());
//
//                    int teamName = Integer.parseInt(sc.nextLine());
//
//
//                    for (int i = 0; i < displays.getPreExisting().size(); i++) {
//                        if (i == (teamName - 1)) {
//                            teamToPlay = displays.getPreExisting().get(i);
//                        }
//                    }
//
//                } else if (one.equals("3")) {
//
//                    try {
//                        reader.read();
//                    } catch (IOException e) {
//                        System.out.println("FAILED");
//                    }
//                    for (int i = 0; i < displays.getMyTeam().size(); i++) {
//                        for (int j = i + 1; j < displays.getMyTeam().size(); j++) {
//                            if (displays.getMyTeam().get(i).getName().equals(displays.getMyTeam().get(j).getName())) {
//                                displays.getMyTeam().remove(j);
//                            }
//                        }
//                    }
//                    continue;
//
//                } else if (one.equals("4")) {
//
//                    for (Team t : displays.getMyTeam()) {
//                        System.out.println("\n" + (displays.getMyTeam().indexOf(t) + 1) + ". " + t.getName()
//                                + " Team OVR - " + t.teamOVR() + "\n");
//                        for (Object s : t.namesOnly()) {
//                            System.out.println(s);
//                        }
//                    }
//
//                    System.out.println("\n Choose the team you want to play by selecting the number besides it");
//                    int teamName = Integer.parseInt(sc.nextLine());
//
//                    for (int i = 0; i < displays.getMyTeam().size(); i++) {
//                        if (i == (teamName - 1)) {
//                            teamToPlay = displays.getMyTeam().get(i);
//                        }
//                    }
//                }
//            }
//            System.out.println("\n Choose team to play against by selecting the number besides it");
//
//            for (int i = 0; i < displays.getPreExisting().size(); i++) {
//                System.out.println("\n" + (i + 1) + ". " + displays.getPreExisting().get(i).getName()
//                        + " Team OVR - " + displays.getPreExisting().get(i).teamOVR());
//            }
//
//            int teamName = Integer.parseInt(sc.nextLine());
//
//            for (int i = 0; i < displays.getPreExisting().size(); i++) {
//                if (i == (teamName - 1)) {
//                    teamToPlayAgainst = displays.getPreExisting().get(i);
//                }
//                System.out.println("\n");
//            }
//
//            System.out.println("Hit enter to start the game - ");
//            String key = sc.nextLine();
//            System.out.println("\n" + "\t" + teamToPlay.getName() +
//            " vs " + teamToPlayAgainst.getName() + "\n" + "\n");
//
//            File newFile = new File("src/main/ref_whistle.wav");
//            AudioInputStream newAudioStream = AudioSystem.getAudioInputStream(newFile);
//            Clip newClip = AudioSystem.getClip();
//            newClip.open(newAudioStream);
//            newClip.start();
//
//            File file1 = new File("src/main/finalcheering.wav");
//            AudioInputStream audioStream1 = AudioSystem.getAudioInputStream(file1);
//            Clip clip1 = AudioSystem.getClip();
//            clip1.open(audioStream1);
//            clip1.start();
//
//            if (key.isEmpty()) {
//                long startTime = System.currentTimeMillis();
//                long end = startTime + 60000;
//                Random rand = new Random();
//
//                while (System.currentTimeMillis() <= end) {
//
//                    if (System.currentTimeMillis() % 5000 == 0) {
//                        int time = rand.nextInt(8) * 800;
//                        Thread.sleep(time);
//
//
//                        if (gameStats.probGoal(teamToPlay, teamToPlayAgainst) > 92) {
//                            gameStats.setGoalT1();
//                            System.out.println("\t" + teamToPlay.getName() +
//                            "\t" + "\t" + teamToPlayAgainst.getName());
//                            System.out.println("\n" + "\t" + gameStats.getGoalT1() + "\t"
//                                    + "\t" + ":" + "\t" + "\t" + gameStats.getGoalT2());
//                            System.out.println("\n \t" + "Goal scored by "
//                                    + teamToPlay.namesOnly().get(rand.nextInt(11)));
//
//                            if (gameStats.probYellow(teamToPlayAgainst, teamToPlay) > 95) {
//                                gameStats.setYellowT2();
//                                System.out.println("\n" + "Yellow card to " + teamToPlayAgainst.getName()
//                                        + " - " + teamToPlayAgainst.namesOnly().get(rand.nextInt(11)));
//                            }
//                            System.out.println("\n" + gameStats.probPossession(teamToPlay, teamToPlayAgainst));
//
//                            File file = new File("src/main/gooallaazoo.wav");
//                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//                            Clip clip = AudioSystem.getClip();
//                            clip.open(audioStream);
//                            clip.start();
//
//                            System.out.println("\n" + "\t" + "  Yellow cards");
//                            System.out.println("\t" + gameStats.getYellowT1() + "\t" + "\t" + "\t" + "\t"
//                                    + gameStats.getYellowT2());
//                            System.out.println("#######################################");
//
//                        } else if (gameStats.probGoal(teamToPlayAgainst, teamToPlay) > 92) {
//                            gameStats.setGoalT2();
//                            System.out.println("\t" + teamToPlay.getName()
//                            + "\t" + "\t" + teamToPlayAgainst.getName());
//                            System.out.println("\n" + "\t" + gameStats.getGoalT1() + "\t" + "\t" + ":" + "\t" + "\t"
//                                    + gameStats.getGoalT2());
//                            System.out.println("\n \t" + "Goal scored by "
//                                    + teamToPlayAgainst.namesOnly().get(rand.nextInt(11)));
//
//                            if (gameStats.probYellow(teamToPlay, teamToPlayAgainst) > 95) {
//                                gameStats.setYellowT1();
//                                System.out.println("\n" + "Yellow card to " + teamToPlay.getName() + " - "
//                                        + teamToPlay.namesOnly().get(rand.nextInt(11)));
//                            }
//                            System.out.println("\n" + gameStats.probPossession(teamToPlay, teamToPlayAgainst) + "\n");
//
//                            File file = new File("src/main/gooallaazoo.wav");
//                            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//                            Clip clip = AudioSystem.getClip();
//                            clip.open(audioStream);
//                            clip.start();
//
//                            System.out.println("\n" + "\t" + "  Yellow cards");
//                            System.out.println("\t" + gameStats.getYellowT1() + "\t" + "\t" + "\t" + "\t"
//                                    + gameStats.getYellowT2());
//                            System.out.println("#######################################");
//                        }
//                    }
//                }
//
//                if (gameStats.getGoalT1() > gameStats.getGoalT2()) {
//                    System.out.println(teamToPlay.getName() + " has won!!");
//
//                } else if (gameStats.getGoalT1() < gameStats.getGoalT2()) {
//                    System.out.println(teamToPlayAgainst.getName() + " has won!!");
//                } else if (gameStats.getGoalT1() == gameStats.getGoalT2()) {
//                    System.out.println("It was a tie!!");
//                }
//
//                clip1.stop();
//
//                TimeUnit.SECONDS.sleep(1);
//
//                File file = new File("src/main/HEHEHESUIIII.wav");
//                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//                Clip clip = AudioSystem.getClip();
//                clip.open(audioStream);
//                clip.start();
//
//                TimeUnit.SECONDS.sleep(3);
//
//
//                System.out.println("Game over");
//
//                System.out.println("Do you want to continue playing? (y/n)");
//                String out = sc.nextLine();
//
//                if (out.equals("y")) {
//                    continue;
//                } else {
//                    System.exit(0);
//                }
//            }
//        }
//    }
//}
//
//
//
//
