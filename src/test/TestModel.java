import model.DisplayTeams;
import model.Player;
import model.Statistics;
import model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import percistence.JsonReader;
import percistence.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;



public class TestModel {

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

    Team testTeam = new Team("Test team");

    Player messi = new Player("Messi", 100, 65, 100, 1);
    Player ramos = new Player("Ramos", 30, 99, 60, 3);
    Player neymar = new Player("Neymar", 93, 50, 90, 1);
    Player mbappe = new Player("Mbappe", 95, 60, 80, 1);
    Player ronaldo = new Player("Ronaldo", 98, 70, 90, 1);
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
    Player bale = new Player("Bale", 87, 40, 86, 1);

    Player dummyPlayer = new Player("Empty Position", 0, 0, 0, 0);





    @BeforeEach
    public void setup() {
        testTeam.makeTeam();
    }

    @Test
    public void showPositionNameTest() {
        assertEquals("Attacker",messi.showPositionName());
        assertEquals("Defender",ramos.showPositionName());
        assertEquals("Goal Keeper",stegen.showPositionName());
        assertEquals("Mid Fielder",iniesta.showPositionName());
    }

    @Test
    public void playerOVRTest() {

        messi.playerOVR();
        stegen.playerOVR();
        ramos.playerOVR();
        iniesta.playerOVR();

        assertEquals(96,messi.getRating());
        assertEquals(77,ramos.getRating());
        assertEquals(83,iniesta.getRating());
        assertEquals(86,stegen.getRating());
    }

    @Test
    public void showValueTest() {
       messi.playerOVR();
       mbappe.playerOVR();
       ramos.playerOVR();
       iniesta.playerOVR();
       casillas.playerOVR();


        assertEquals(144000000,messi.showValue());
        assertEquals(132000000,mbappe.showValue());
        assertEquals(57750000,ramos.showValue());
        assertEquals(83000000,iniesta.showValue());
        assertEquals(43000000,casillas.showValue());
    }

    @Test
    public void teamOVREmptyTest() {

        assertEquals(0,testTeam.teamOVR());

    }


    @Test
    public void teamOVRPartialTest() {

        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);

        assertEquals(32,testTeam.teamOVR());

    }

    @Test
    public void teamOVRCompleteTest() {

        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);
        testTeam.addAndReplace(busquets, cm);
        testTeam.addAndReplace(xavi, rm);
        testTeam.addAndReplace(alba, lb);
        testTeam.addAndReplace(pique, cb1);
        testTeam.addAndReplace(mascherano, cb2);
        testTeam.addAndReplace(alves, rb);
        testTeam.addAndReplace(stegen, gk);

        assertEquals(82,testTeam.teamOVR());

    }



    @Test
    public void removePlayerNotFoundTest() {

        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);
        testTeam.addAndReplace(busquets, cm);
        testTeam.addAndReplace(xavi, rm);
        testTeam.addAndReplace(alba, lb);
        testTeam.addAndReplace(pique, cb1);
        testTeam.addAndReplace(mascherano, cb2);
        testTeam.addAndReplace(alves, rb);
        testTeam.addAndReplace(stegen, gk);

        testTeam.removePlayer(ronaldo);

        assertFalse(testTeam.containsPlayer(ronaldo));
    }

    @Test
    public void removePlayerFoundTest() {

        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);
        testTeam.addAndReplace(busquets, cm);
        testTeam.addAndReplace(xavi, rm);
        testTeam.addAndReplace(alba, lb);
        testTeam.addAndReplace(pique, cb1);
        testTeam.addAndReplace(mascherano, cb2);
        testTeam.addAndReplace(alves, rb);
        testTeam.addAndReplace(stegen, gk);

        testTeam.removePlayer(messi);

        assertFalse(testTeam.containsPlayer(messi));
    }

    @Test
    public void updatePlayerNamesCompleteTest() {

        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);
        testTeam.addAndReplace(busquets, cm);
        testTeam.addAndReplace(xavi, rm);
        testTeam.addAndReplace(alba, lb);
        testTeam.addAndReplace(pique, cb1);
        testTeam.addAndReplace(mascherano, cb2);
        testTeam.addAndReplace(alves, rb);
        testTeam.addAndReplace(stegen, gk);

        assertFalse(testTeam.containsName("Empty Position"));
    }

    @Test
    public void updatePlayerNamesEmptyTest() {
        testTeam.updatePlayerNames();

        assertTrue(testTeam.containsName("Empty Position"));
    }

    @Test
    public void namesOnlyEmptyTeam() {
        assertTrue(testTeam.namesOnly().contains("Empty Position"));
        assertFalse(testTeam.namesOnly().contains("Messi"));
    }

    @Test
    public void namesOnlyCompleteTeam() {

        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);
        testTeam.addAndReplace(busquets, cm);
        testTeam.addAndReplace(xavi, rm);
        testTeam.addAndReplace(alba, lb);
        testTeam.addAndReplace(pique, cb1);
        testTeam.addAndReplace(mascherano, cb2);
        testTeam.addAndReplace(alves, rb);
        testTeam.addAndReplace(stegen, gk);

        assertTrue(testTeam.namesOnly().contains("Messi"));
        assertFalse(testTeam.namesOnly().contains("Empty Position"));
    }

    @Test
    public void addAndReplaceOneTest() {

        testTeam.addAndReplace(messi, lw);
        assertTrue(testTeam.containsPlayer(messi));
    }

    @Test
    public void add() {

        testTeam.add(messi, lw);
        assertTrue(testTeam.containsPlayer(messi));
    }

    @Test
    public void addAndReplaceFullTest() {

        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);
        testTeam.addAndReplace(busquets, cm);
        testTeam.addAndReplace(xavi, rm);
        testTeam.addAndReplace(alba, lb);
        testTeam.addAndReplace(pique, cb1);
        testTeam.addAndReplace(mascherano, cb2);
        testTeam.addAndReplace(alves, rb);
        testTeam.addAndReplace(stegen, gk);

        testTeam.addAndReplace(ronaldo,rw);

        assertFalse(testTeam.containsPlayer(ronaldo));
    }

    @Test
    public void addFullTest() {

        testTeam.add(neymar, lw);
        testTeam.add(suarez, st);
        testTeam.add(messi, rw);
        testTeam.add(iniesta, lm);
        testTeam.add(busquets, cm);
        testTeam.add(xavi, rm);
        testTeam.add(alba, lb);
        testTeam.add(pique, cb1);
        testTeam.add(mascherano, cb2);
        testTeam.add(alves, rb);
        testTeam.add(stegen, gk);

        testTeam.add(ronaldo,rw);

        assertFalse(testTeam.containsPlayer(ronaldo));
    }

    @Test
    public void addAlreadyContainsPlayerTest() {

        testTeam.add(messi, lw);
        testTeam.add(messi,st);

        testTeam.removePlayer(messi);

        assertFalse(testTeam.containsPlayer(messi));
    }


    @Test
    public void addAndReplaceAlreadyContainsPlayerTest() {

        testTeam.addAndReplace(messi, lw);
        testTeam.addAndReplace(messi,st);

        testTeam.removePlayer(messi);

        assertFalse(testTeam.containsPlayer(messi));
    }

    @Test
    public void addAndReplaceAlreadyContainsPositionTest() {

        testTeam.addAndReplace(messi, lw);
        testTeam.addAndReplace(ronaldo, lw);

        assertTrue(testTeam.containsPlayer(messi));
        assertFalse(testTeam.containsPlayer(ronaldo));
    }

    @Test
    public void addAlreadyContainsPositionTest() {

        testTeam.add(messi, lw);
        testTeam.add(ronaldo, lw);

        assertTrue(testTeam.containsPlayer(messi));
        assertFalse(testTeam.containsPlayer(ronaldo));
    }


    @Test
    public void makeTeamNewTest() {
        Team newTestTeam = new Team("tester");

        newTestTeam.makeTeam();

        assertTrue(newTestTeam.containsName("Empty Position"));
        assertTrue(newTestTeam.containsPlayer(dummyPlayer));

    }

    @Test
    public void makeTeamFullTest() {

        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);
        testTeam.addAndReplace(busquets, cm);
        testTeam.addAndReplace(xavi, rm);
        testTeam.addAndReplace(alba, lb);
        testTeam.addAndReplace(pique, cb1);
        testTeam.addAndReplace(mascherano, cb2);
        testTeam.addAndReplace(alves, rb);
        testTeam.addAndReplace(stegen, gk);

        testTeam.makeTeam();

        assertFalse(testTeam.containsName("Empty Position"));
        assertTrue(testTeam.containsPlayer(neymar));
        assertTrue(testTeam.containsPlayer(neymar));
    }

    @Test
    public void averageOVRPartialTest() {
        testTeam.addAndReplace(messi, lw);
        testTeam.addAndReplace(ronaldo,st);

        assertEquals(0,testTeam.averageOVR(1));
    }

    @Test void averageOVREmptyTest() {
        assertEquals(0,testTeam.averageOVR(1));
    }

    @Test
    public void averageOVRFullTest() {
        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);
        testTeam.addAndReplace(busquets, cm);
        testTeam.addAndReplace(xavi, rm);
        testTeam.addAndReplace(alba, lb);
        testTeam.addAndReplace(pique, cb1);
        testTeam.addAndReplace(mascherano, cb2);
        testTeam.addAndReplace(alves, rb);
        testTeam.addAndReplace(stegen, gk);

        assertEquals(90,testTeam.averageOVR(1));
        assertEquals(80,testTeam.averageOVR(2));
        assertEquals(77,testTeam.averageOVR(3));
        assertEquals(86,testTeam.averageOVR(4));
    }

    @Test
    public void containsPlayerEmptyTest() {
        assertFalse(testTeam.containsPlayer(neymar));
    }

    @Test
    public void containsPlayerFullTest() {
        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);
        testTeam.addAndReplace(messi, rw);
        testTeam.addAndReplace(iniesta, lm);
        testTeam.addAndReplace(busquets, cm);
        testTeam.addAndReplace(xavi, rm);
        testTeam.addAndReplace(alba, lb);
        testTeam.addAndReplace(pique, cb1);
        testTeam.addAndReplace(mascherano, cb2);
        testTeam.addAndReplace(alves, rb);
        testTeam.addAndReplace(stegen, gk);

        assertTrue(testTeam.containsPlayer(neymar));
        assertFalse(testTeam.containsPlayer(ronaldo));
    }

    @Test
    public void containsNameEmptyTest() {
        assertFalse(testTeam.containsName("Neymar"));
        assertTrue(testTeam.containsName("Empty Position"));
    }

    @Test
    public void containsNamePartialTest() {
        testTeam.addAndReplace(neymar, lw);
        testTeam.addAndReplace(suarez, st);

        assertTrue(testTeam.containsName("Neymar"));
        assertTrue(testTeam.containsName("Empty Position"));
    }

    @Test
    public void probGoalBetween0And7Test() {
        // FC barcelona
        Team barcelona = new Team("Barcelona");
        barcelona.makeTeam();

        Player player1 = new Player("p1", 100, 100, 100, 3);
        Player player2 = new Player("p2", 100, 100, 100, 3);
        Player player3 = new Player("p3", 100, 100, 100, 3);
        Player player4 = new Player("p4", 100, 100, 100, 3);

        barcelona.addAndReplace(neymar, lw);
        barcelona.addAndReplace(suarez, st);
        barcelona.addAndReplace(messi, rw);
        barcelona.addAndReplace(iniesta, lm);
        barcelona.addAndReplace(busquets, cm);
        barcelona.addAndReplace(xavi, rm);
        barcelona.addAndReplace(player1, lb);
        barcelona.addAndReplace(player2, cb1);
        barcelona.addAndReplace(player3, cb2);
        barcelona.addAndReplace(player4, rb);
        barcelona.addAndReplace(stegen, gk);


        // Real Madrid
        Team madrid = new Team("Madrid");
        madrid.makeTeam();

        madrid.addAndReplace(ronaldo, lw);
        madrid.addAndReplace(benzema, st);
        madrid.addAndReplace(bale, rw);
        madrid.addAndReplace(kroos, lm);
        madrid.addAndReplace(casemiro, cm);
        madrid.addAndReplace(modric, rm);
        madrid.addAndReplace(marcelo, lb);
        madrid.addAndReplace(ramos, cb1);
        madrid.addAndReplace(player3, cb2);
        madrid.addAndReplace(player4, rb);
        madrid.addAndReplace(casillas, gk);


        Statistics gameStatsTest = new Statistics(0,0,0,0,0,0);

        assertEquals(2,barcelona.averageOVR(1)- madrid.averageOVR(3));

        assertTrue(gameStatsTest.probGoal(barcelona,madrid) >= 80);
        assertTrue(gameStatsTest.probGoal(barcelona,madrid) <= 100);

        assertTrue(gameStatsTest.probGoal(madrid,barcelona) >= 80);
        assertTrue(gameStatsTest.probGoal(madrid,barcelona) <= 100);

        assertFalse(gameStatsTest.probGoal(barcelona,madrid) > 100);
        assertFalse(gameStatsTest.probGoal(barcelona,madrid) < 80);
    }

    @Test
    public void probGoalGreaterThan7Test() {
        // FC barcelona
        Team barcelona = new Team("Barcelona");
        barcelona.makeTeam();

        Player player1 = new Player("p1", 100, 65, 100, 1);
        Player player2 = new Player("p2", 100, 65, 100, 1);
        Player player3 = new Player("p3", 100, 65, 100, 2);
        Player player4 = new Player("p4", 100, 65, 100, 2);
        Player player5 = new Player("p5", 100, 65, 100, 2);

        barcelona.addAndReplace(player1, lw);
        barcelona.addAndReplace(player2, st);
        barcelona.addAndReplace(messi, rw);
        barcelona.addAndReplace(player3, lm);
        barcelona.addAndReplace(player4, cm);
        barcelona.addAndReplace(player5, rm);
        barcelona.addAndReplace(alba, lb);
        barcelona.addAndReplace(pique, cb1);
        barcelona.addAndReplace(mascherano, cb2);
        barcelona.addAndReplace(alves, rb);
        barcelona.addAndReplace(stegen, gk);


        // Real Madrid
        Team madrid = new Team("Madrid");
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


        Statistics gameStatsTest = new Statistics(0,0,0,0,0,0);

        assertEquals(20,barcelona.averageOVR(1)- madrid.averageOVR(3));

        assertTrue(gameStatsTest.probGoal(barcelona,madrid) >= 85);
        assertTrue(gameStatsTest.probGoal(barcelona,madrid) <= 95);

        assertTrue(gameStatsTest.probGoal(madrid,barcelona) >= 85);
        assertTrue(gameStatsTest.probGoal(madrid,barcelona) <= 95);

        assertFalse(gameStatsTest.probGoal(barcelona,madrid) > 95);
        assertFalse(gameStatsTest.probGoal(barcelona,madrid) < 85);
    }

    @Test
    public void probYellowGreater7Test() {
        Team barcelona = new Team("Barcelona");
        barcelona.makeTeam();

        Player player1 = new Player("p1", 100, 100, 100, 3);
        Player player2 = new Player("p2", 100, 100, 100, 3);
        Player player3 = new Player("p3", 100, 100, 100, 3);
        Player player4 = new Player("p4", 100, 100, 100, 3);


        barcelona.addAndReplace(neymar, lw);
        barcelona.addAndReplace(suarez, st);
        barcelona.addAndReplace(messi, rw);
        barcelona.addAndReplace(iniesta, lm);
        barcelona.addAndReplace(busquets, cm);
        barcelona.addAndReplace(xavi, rm);
        barcelona.addAndReplace(player4, lb);
        barcelona.addAndReplace(player3, cb1);
        barcelona.addAndReplace(player2, cb2);
        barcelona.addAndReplace(player1, rb);
        barcelona.addAndReplace(stegen, gk);


        // Real Madrid
        Team madrid = new Team("Madrid");
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


        Statistics gameStatsTest = new Statistics(0,0,0,0,0,0);

        assertEquals(24,barcelona.averageOVR(3)- madrid.averageOVR(3));

        assertTrue(gameStatsTest.probYellow(barcelona,madrid) >= 85);
        assertTrue(gameStatsTest.probYellow(barcelona,madrid) <= 100);

        assertTrue(gameStatsTest.probYellow(madrid,barcelona) >= 85);
        assertTrue(gameStatsTest.probYellow(madrid,barcelona) <= 100);

        assertFalse(gameStatsTest.probYellow(barcelona,madrid) > 100);
        assertFalse(gameStatsTest.probYellow(barcelona,madrid) < 85);
    }

    @Test
    public void probYellowBetween0And7Test() {
        Team barcelona = new Team("Barcelona");
        barcelona.makeTeam();

        Player player1 = new Player("p1", 100, 100, 100, 3);
        Player player3 = new Player("p3", 100, 100, 100, 3);
        Player player4 = new Player("p4", 100, 100, 100, 3);

        barcelona.addAndReplace(neymar, lw);
        barcelona.addAndReplace(suarez, st);
        barcelona.addAndReplace(messi, rw);
        barcelona.addAndReplace(iniesta, lm);
        barcelona.addAndReplace(busquets, cm);
        barcelona.addAndReplace(xavi, rm);
        barcelona.addAndReplace(player4, lb);
        barcelona.addAndReplace(player3, cb1);
        barcelona.addAndReplace(alves, cb2);
        barcelona.addAndReplace(pique, rb);
        barcelona.addAndReplace(stegen, gk);


        // Real Madrid
        Team madrid = new Team("Madrid");
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
        madrid.addAndReplace(player1, rb);
        madrid.addAndReplace(casillas, gk);


        Statistics gameStatsTest = new Statistics(0,0,0,0,0,0);

        assertEquals(6,barcelona.averageOVR(3)- madrid.averageOVR(3));

        assertTrue(gameStatsTest.probYellow(barcelona,madrid) >= 80);
        assertTrue(gameStatsTest.probYellow(barcelona,madrid) <= 100);

        assertTrue(gameStatsTest.probYellow(madrid,barcelona) >= 80);
        assertTrue(gameStatsTest.probYellow(madrid,barcelona) <= 100);

        assertFalse(gameStatsTest.probYellow(barcelona,madrid) > 100);
        assertFalse(gameStatsTest.probYellow(barcelona,madrid) < 80);
    }

    @Test
    public void probPossessionTest() {



        // FC barcelona
        Team barcelona = new Team("Barcelona");
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


        // Real Madrid
        Team madrid = new Team("Madrid");
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


        Statistics gameStatsTest = new Statistics(0,0,0,0,0,0);


        gameStatsTest.probPossession(barcelona,madrid);

        assertEquals(3,barcelona.teamOVR()- madrid.teamOVR());

        assertTrue(gameStatsTest.getPossessionT1() >= 50);
        assertTrue(gameStatsTest.getPossessionT2() <= 50);

        assertFalse(gameStatsTest.getPossessionT1() < 50);
        assertFalse(gameStatsTest.getPossessionT2() > 50);

        gameStatsTest.probPossession(madrid,barcelona);

        assertTrue(gameStatsTest.getPossessionT1() <= 50);
        assertTrue(gameStatsTest.getPossessionT2() >= 50);

        assertFalse(gameStatsTest.getPossessionT1() > 50);
        assertFalse(gameStatsTest.getPossessionT2() < 50);
    }

    @Test
    public void updatePlayerNamesTest() {
        testTeam.addAndReplace(messi, lw);
        testTeam.addAndReplace(ronaldo,st);

        ArrayList<String> names = testTeam.getPlayerNames();

        assertEquals(11,names.size());
        assertTrue(testTeam.containsName("Messi") && names.contains("Messi"));
        assertFalse(testTeam.containsName("Neymar") && names.contains("Neymar"));
        assertTrue(testTeam.containsName("Empty Position") && names.contains("Empty Position"));
    }

    @Test
    public void setNameTest() {
        testTeam.setName("Liverpool");

        assertEquals("Liverpool",testTeam.getName());
    }

    @Test
    public void setGoalT1Test() {
        Statistics gameStatsTest = new Statistics(0,0,0,0,0,0);
        gameStatsTest.setGoalT1();

        assertEquals(1,gameStatsTest.getGoalT1());

        gameStatsTest.setGoalT1();

        assertEquals(2,gameStatsTest.getGoalT1());
    }

    @Test
    public void setGoalT2Test() {
        Statistics gameStatsTest = new Statistics(0,0,0,0,0,0);
        gameStatsTest.setGoalT2();

        assertEquals(1,gameStatsTest.getGoalT2());

        gameStatsTest.setGoalT2();

        assertEquals(2,gameStatsTest.getGoalT2());
    }

    @Test
    public void setYellowT1Test() {
        Statistics gameStatsTest = new Statistics(0,0,0,0,0,0);
        gameStatsTest.setYellowT1();

        assertEquals(1,gameStatsTest.getYellowT1());

        gameStatsTest.setYellowT1();

        assertEquals(2,gameStatsTest.getYellowT1());
    }

    @Test
    public void setYellowT2Test() {
        Statistics gameStatsTest = new Statistics(0,0,0,0,0,0);
        gameStatsTest.setYellowT2();

        assertEquals(1,gameStatsTest.getYellowT2());

        gameStatsTest.setYellowT2();

        assertEquals(2,gameStatsTest.getYellowT2());
    }

    @Test
    public void testWriterInvalidFile() {
        try {
            DisplayTeams dt = new DisplayTeams();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json",dt);
            writer.open();
        } catch (IOException e) {
            // PASS
        }
    }

    @Test
    public void testWriterEmptyWorkroom() {
        try {
            DisplayTeams dt = new DisplayTeams();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json",dt);
            writer.open();
            writer.write(dt);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json",new ArrayList<Player>(),dt);
            reader.read();
            assertEquals(0,dt.getMyTeam().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralDisplayTeams() {
        try {
            ArrayList<Player> playersList = new ArrayList<>();

            playersList.add(messi);
            playersList.add(ramos);
            playersList.add(mbappe);
            playersList.add(ronaldo);
            playersList.add(suarez);
            playersList.add(xavi);
            playersList.add(alba);
            playersList.add(alves);
            playersList.add(mascherano);
            playersList.add(pique);
            playersList.add(benzema);
            playersList.add(modric);
            playersList.add(kroos);
            playersList.add(casemiro);
            playersList.add(pepe);
            playersList.add(mendy);
            playersList.add(marcelo);
            playersList.add(casillas);
            playersList.add(stegen);
            playersList.add(bale);
            playersList.add(iniesta);

            Team madrid = new Team("Madrid");
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

            Team barcelona = new Team("Barcelona");
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

            DisplayTeams dt = new DisplayTeams();
            dt.addToMyTeam(madrid);
            dt.addToMyTeam(barcelona);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json", dt);
            writer.open();
            writer.write(dt);
            writer.close();

            dt.getMyTeam().remove(0);

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json", playersList, dt);
            reader.read();

            Team saved = dt.getMyTeam().get(0);
            assertEquals(3, dt.getMyTeam().size());
            assertEquals("Barcelona", saved.getName());
            assertTrue(saved.containsPlayer(messi));
            assertTrue(saved.containsName("Messi"));
        } catch (IOException e) {
            // PASS
        }
    }

    @Test
    public void testNonExistentFile() {
        DisplayTeams dt = new DisplayTeams();
        JsonReader reader = new JsonReader("./data/noSuchFile.json",
                new ArrayList<Player>(), dt);
        try {
            reader.read();
            fail("IOException expected");
            assertEquals(0, dt.getMyTeam().size());
        } catch (IOException e) {
            // PASS
        }
    }

    @Test
    public void testReaderEmptyDisplayTeams() {
        DisplayTeams dt = new DisplayTeams();
        JsonReader reader = new JsonReader("./data/testReader.json",
                new ArrayList<Player>(), dt);
        try {
            reader.read();
            assertEquals("Madrid", dt.getMyTeam().get(0).getName());
            assertEquals(1,dt.getMyTeam().size());
            assertFalse(dt.getMyTeam().get(0).containsName("Ronaldo"));
        } catch (IOException e) {
            fail("Couldnt read from file");
        }

    }

    @Test
    public void testReaderWorkingDisplayTeams() {
        ArrayList<Player> playersList = new ArrayList<>();

        playersList.add(messi);
        playersList.add(ramos);
        playersList.add(mbappe);
        playersList.add(ronaldo);
        playersList.add(suarez);
        playersList.add(xavi);
        playersList.add(alba);
        playersList.add(alves);
        playersList.add(mascherano);
        playersList.add(pique);
        playersList.add(benzema);
        playersList.add(modric);
        playersList.add(kroos);
        playersList.add(casemiro);
        playersList.add(pepe);
        playersList.add(mendy);
        playersList.add(marcelo);
        playersList.add(casillas);
        playersList.add(stegen);
        playersList.add(bale);
        playersList.add(iniesta);

        Team madrid = new Team("Madrid");
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



        DisplayTeams dt = new DisplayTeams();
        JsonReader reader = new JsonReader("./data/testReaderWorking.json",playersList,dt);

        try {
            reader.read();
            assertEquals(2, dt.getMyTeam().size());
            ArrayList<String> names = madrid.namesOnly();
            assertTrue(dt.getMyTeam().get(0).containsPlayer(ronaldo));
            assertEquals("Madrid", dt.getMyTeam().get(0).getName());
            assertEquals(79, dt.getMyTeam().get(0).teamOVR());
        } catch (IOException e) {
            fail("Couldn't read the file");
        }
    }

    @Test
    public void testAddToPreExisting() {
        DisplayTeams dt = new DisplayTeams();
        Team madrid = new Team("Madrid");
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

        assertEquals(0,dt.getPreExisting().size());
        dt.addToPreExisting(madrid);
        assertEquals(1, dt.getPreExisting().size());
        assertEquals("Madrid",dt.getPreExisting().get(0).getName());
    }

    @Test
    public void testAddToMyTeams() {
        DisplayTeams dt = new DisplayTeams();
        Team madrid = new Team("Madrid");
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

        assertEquals(0,dt.getMyTeam().size());
        dt.addToMyTeam(madrid);
        assertEquals(1, dt.getMyTeam().size());
        assertEquals("Madrid",dt.getMyTeam().get(0).getName());
    }

    @Test
    public void testToJson() {
        ArrayList<Player> playersList = new ArrayList<>();

        playersList.add(messi);
        playersList.add(ramos);
        playersList.add(mbappe);
        playersList.add(ronaldo);
        playersList.add(suarez);
        playersList.add(xavi);
        playersList.add(alba);
        playersList.add(alves);
        playersList.add(mascherano);
        playersList.add(pique);
        playersList.add(benzema);
        playersList.add(modric);
        playersList.add(kroos);
        playersList.add(casemiro);
        playersList.add(pepe);
        playersList.add(mendy);
        playersList.add(marcelo);
        playersList.add(casillas);
        playersList.add(stegen);
        playersList.add(bale);
        playersList.add(iniesta);

        Team madrid = new Team("Madrid");
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

        DisplayTeams dt = new DisplayTeams();
        JsonReader reader = new JsonReader("./data/tesReader.json",playersList,dt);
        assertEquals("{\"teams\":[]}", dt.toJson().toString());
    }

    @Test
    void testJson() {
        ArrayList<Player> playersList = new ArrayList<>();

        playersList.add(messi);
        playersList.add(ramos);
        playersList.add(mbappe);
        playersList.add(ronaldo);
        playersList.add(suarez);
        playersList.add(xavi);
        playersList.add(alba);
        playersList.add(alves);
        playersList.add(mascherano);
        playersList.add(pique);
        playersList.add(benzema);
        playersList.add(modric);
        playersList.add(kroos);
        playersList.add(casemiro);
        playersList.add(pepe);
        playersList.add(mendy);
        playersList.add(marcelo);
        playersList.add(casillas);
        playersList.add(stegen);
        playersList.add(bale);
        playersList.add(iniesta);

        Team madrid = new Team("Madrid");
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

        DisplayTeams dt = new DisplayTeams();
        dt.addToMyTeam(madrid);
        JsonWriter writer = new JsonWriter("./data/testReaderN.json", dt);
        try {
            writer.open();
            writer.write(dt);
            writer.close();
            assertEquals("{\"teams\":[{\"players\":[{\"name\":\"Ronaldo\"},{\"name\":\"Benzema\"}," +
                    "{\"name\":\"Bale\"},{\"name\":\"Kroos\"},{\"name\":\"Casemiro\"},{\"name\":\"Modric\"}" +
                    ",{\"name\":\"Marcelo\"},{\"name\":\"Ramos\"},{\"name\":\"Pepe\"},{\"name\":\"Mendy\"}," +
                    "{\"name\":\"Casillas\"}],\"name\":\"Madrid\"}]}", dt.toJson().toString());
        } catch (Exception e) {
            fail("Failed to open file.");
        }
    }

    @Test
    void testReplace() {
        testTeam.addAndReplace(messi, 0);
        testTeam.addAndReplace(neymar, 1);

        testTeam.replace(ronaldo, messi);
        assertTrue(testTeam.containsPlayer(ronaldo));
        assertFalse(testTeam.containsPlayer(messi));

        testTeam.replace(xavi, stegen);
        assertFalse(testTeam.containsPlayer(xavi));
        assertFalse(testTeam.containsPlayer(stegen));
    }

}
