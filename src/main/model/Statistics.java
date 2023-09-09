package model;

import java.util.ArrayList;

// represents statistics
public class Statistics {

    private static int goalT1;
    private static int goalT2;
    private static int yellowT1;
    private static int yellowT2;
    private int possessionT1;
    private int possessionT2;

    private ArrayList<String> history;


    // constructs statistics
    // EFFECTS : statistics is goal, yellow and possession respectively for team 1 and team 2
    public Statistics(int goalT1, int goalT2, int yellowT1, int yellowT2,
                      int possessionT1, int possessionT2) {

        this.goalT1 = goalT1;
        this.goalT2 = goalT2;
        this.yellowT1 = yellowT1;
        this.yellowT2 = yellowT2;
        this.possessionT1 = possessionT1;
        this.possessionT2 = possessionT2;
        this.history = new ArrayList<>();

    }



    // EFFECTS  : returns a random integer depending on the range that
    //            corresponds to difference between ratings of attacker of team1
    //            and defenders of team2
    public int probGoal(Team team1, Team team2) {

        if (Math.abs(team1.averageOVR(1) - team2.averageOVR(3)) >= 7) {
            int randomInt = (int) Math.floor(Math.random() * (95 - 85 + 1) + 85);
            return randomInt;
        } else {
            int randomInt = (int) Math.floor(Math.random() * (100 - 80 + 1) + 80);
            return randomInt;
        }
    }


    // EFFECTS  : returns a random integer depending on the range that
    //            corresponds to difference between ratings of defenders of team1
    //            and defenders of team2
    public int probYellow(Team team1, Team team2) {
        if (Math.abs(team1.averageOVR(3) - team2.averageOVR(3)) >= 7) {
            int randomInt = (int) Math.floor(Math.random() * (100 - 85 + 1) + 85);
            return randomInt;

        } else {
            int randomInt1 = (int) Math.floor(Math.random() * (100 - 80 + 1) + 80);
            return randomInt1;
        }
    }



    // MODIFIES : this
    // EFFECTS  : returns the possession of team1 and team2 depending
    //            on differences between team overall ratings
    public String probPossession(Team t1, Team t2) {
        int someNum = (int) Math.floor(Math.random() * (60 - 50 + 1) + 50);
        int otherNum = 100 - someNum;

        if (t1.teamOVR() > t2.teamOVR()) {
            this.possessionT1 = someNum;
            this.possessionT2 = otherNum;
            return "\t \t Possession" + "\n" + t1.getName() + " - " + someNum + "%" + "\t" + "\t" + t2.getName() + " - "
                    + otherNum + "%";
        } else {
            this.possessionT1 = otherNum;
            this.possessionT2 = someNum;
            return "\t \t Possession" + "\n" + t1.getName() + " - " + otherNum + "%" + "\t" + "\t" + t2.getName()
                    + " - " + someNum + "%";
        }
    }



    // MODIFIES : this
    // EFFECTS  : increases goalT1 by 1
    public void setGoalT1() {
        goalT1++;
    }


    // MODIFIES : this
    // EFFECTS  : increases goalT2 by 1
    public void setGoalT2() {
        goalT2++;
    }


    // MODIFIES : this
    // EFFECTS  : increases yellowT1 by 1
    public void setYellowT1() {
        yellowT1++;
    }


    // MODIFIES : this
    // EFFECTS  : increases yellowT2 by 1
    public void setYellowT2() {
        yellowT2++;
    }


    // EFFECTS : returns possessionT1
    public int getPossessionT1() {
        return this.possessionT1;
    }


    // EFFECTS : returns possessionT2
    public int getPossessionT2() {
        return this.possessionT2;
    }


    // EFFECTS : returns goalT1
    public int getGoalT1() {
        return goalT1;
    }


    // EFFECTS : returns goalT2
    public int getGoalT2() {
        return goalT2;
    }


    // EFFECTS : returns yellowT1
    public int getYellowT1() {
        return yellowT1;
    }


    // EFFECTS : returns yellowT2
    public int getYellowT2() {
        return yellowT2;
    }

}