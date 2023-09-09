package model;

import org.json.JSONObject;
import percistence.Writable;

// represents a player
public class Player {

    private int rating;
    private String name;
    private int attack;
    private int defense;
    private int passing;
    private int value;
    private final int position;
    private String positionName;
    private int attackervalue = 1500000;
    private int midfieldervalue = 1000000;
    private int defendervalue = 750000;
    private int keepervalue = 500000;
    double attackr = 0;
    double midr = 0;
    double defendr = 0;
    double keeperr = 0;


    // constructs a player
    // EFFECTS : player has name, attack rating, defense rating, passing rating
    //           and position
    public Player(String name, int attack, int defense, int passing, int position) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.passing = passing;
        this.position = position;

        if (position == 1) {
            attackr = 0.7 * attack + 0.2 * passing + 0.1 * defense;
            this.rating = (int) attackr;

        } else if (position == 2) {
            midr = 0.4 * attack + 0.4 * passing + 0.2 * defense;
            this.rating = (int) midr;

        } else if (position == 3) {
            defendr = 0.2 * attack + 0.2 * passing + 0.6 * defense;
            this.rating = (int) defendr;
        } else {
            keeperr = 0.1 * attack + 0.1 * passing + 0.8 * defense;
            this.rating = (int) keeperr;
        }


    }

    // EFFECTS : returns player name
    public String getName() {
        return this.name;
    }




    // EFFECTS : returns player position
    public int getPosition() {
        return position;
    }



    // EFFECTS : returns the position name of a player
    //           depending on the position
    public String showPositionName() {

        String pos = "";

        if (position == 1) {
            pos = this.positionName = "Attacker";
        } else if (position == 2) {
            pos = this.positionName = "Mid Fielder";
        } else if (position == 3) {
            pos = this.positionName = "Defender";
        } else  {
            pos = this.positionName = "Goal Keeper";
        }
        return pos;
    }


    public int getRating() {
        return rating;
    }


    // EFFECTS : returns the value of a player depending
    //           on the position
    public int showValue() {

        int value = 0;

        if (position == 1) {
            value = attackervalue * this.getRating();

        } else if (position == 2) {
            value = midfieldervalue * this.getRating();

        } else if (position == 3) {
            value = defendervalue * this.getRating();

        } else {
            value = keepervalue * this.getRating();

        }
        return value;
    }

    // MODIFIES : this
    // EFFECTS  : sets an overall rating of a player depending on
    //            3 types of ratings
    public void playerOVR() {
        if (position == 1) {
            attackr = 0.7 * attack + 0.2 * passing + 0.1 * defense;
            this.rating = (int) attackr;

        } else if (position == 2) {
            midr = 0.4 * attack + 0.4 * passing + 0.2 * defense;
            this.rating = (int) midr;

        } else if (position == 3) {
            defendr = 0.2 * attack + 0.2 * passing + 0.6 * defense;
            this.rating = (int) defendr;
        } else {
            keeperr = 0.1 * attack + 0.1 * passing + 0.8 * defense;
            this.rating = (int) keeperr;
        }
    }

}
