package model;

import org.json.JSONArray;
import org.json.JSONObject;
import percistence.Writable;

import java.util.ArrayList;

import static java.lang.Math.round;

// represents a team
public class Team implements Writable {

    private ArrayList<Player> team;
    private ArrayList<String> playerNames = new ArrayList<String>(11);
    private String teamName;
    Player dummyPlayer = new Player("Empty Position", 0, 0, 0, 0);


    // constructs a team
    // EFFECTS : team is list of players with team name
    public Team(String teamName) {
        this.teamName = teamName;
        team = new ArrayList<Player>(11);

    }


    // EFFECTS : returns the overall rating of team by taking an average
    //           from player ratings
    public int teamOVR() {
        int sum = 0;

        for (Player p : team) {
            sum += p.getRating();
        }
        return (sum / 11);
    }


    // MODIFIES : this
    // EFFECTS  : remove the player if team contains it
    public void removePlayer(Player player) {
        int index = 0;

        for (Player p : team) {
            if (p.getName().contains(player.getName())) {
                index = team.indexOf(p);
                team.set(index, dummyPlayer);
            }
        }
        updatePlayerNames();
    }


    // EFFECTS : creates a list of string that contains
    //           the names of players / empty position
    public void updatePlayerNames() {
        playerNames = new ArrayList<>();
        for (Player p : team) {
            playerNames.add(p.getName());
        }
    }


    // EFFECTS : returns a list of string with only the name of players or empty position
    public ArrayList<String> namesOnly() {
        ArrayList<String> names = new ArrayList<>();
        for (Player p : team) {
            names.add(p.getName());
        }
        return names;
    }


    // MODIFIES : this
    // EFFECTS  : adds a player in the empty positions
    public void addAndReplace(Player player, int position) {
        if (team.contains(player) == false && team.get(position).getName() == "Empty Position") {
            team.set(position, player);
        }
        updatePlayerNames();
    }

    // MODIFIES : this
    // EFFECTS  : adds a player in the empty position
    public void add(Player player, int position) {
        if (team.contains(player) == false && team.get(position).getName() == "Empty Position") {
            team.set(position, player);
            EventLog.getInstance().logEvent(new Event("Player " + player.getName() + " was added to the team"));
        }
    }


    // MODIFIES : this
    // EFFECTS  : adds an empty team with dummy players of name empty position
    public void makeTeam() {
        if (!(team.size() > 0)) {
            for (int i = 0; i < 11; i++) {
                team.add(dummyPlayer);
            }
            updatePlayerNames();
        }

    }


    // REQUIRES : 0 < n < 5 and team to have all 11 players
    // EFFECTS : returns average rating of players with corresponding position n
    public int averageOVR(int n) {
        int sum = 0;
        int num = 0;
        if (!team.contains(dummyPlayer)) {
            for (Player p : team) {
                if (p.getPosition() == n) {
                    sum += p.getRating();
                    num += 1;
                }
            }
            return (sum / num);
        }
        return 0;
    }


    // MODIFIES : this
    // EFFECTS  : sets name of team to str
    public void setName(String str) {
        this.teamName = str;
    }


    // EFFECTS : returns team name
    public String getName() {
        return teamName;
    }


    // EFFECTS : returns a list of string with player
    //           names or empty position
    public ArrayList<String> getPlayerNames() {
        return playerNames;
    }


    // EFFECTS : returns true if team contains player or false
    public boolean containsPlayer(Player p) {
        for (Player player : team) {
            if (player.getName().equals(p.getName())) {
                return true;
            }

        }
        return false;
    }


    // EFFECTS : returns true if team contains player with name as str
    //           otherwise false
    public boolean containsName(String str) {
        for (Player player : team) {
            if (player.getName().equals(str)) {
                return true;
            }

        }
        return false;
    }


    // REFERENCE : code below was referred from the following project :
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS : returns team and player names as json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", teamName);
        json.put("players", playersToJson());
        return json;
    }

    // EFFECTS : returns player name as json object
    private JSONArray playersToJson() {
        JSONArray listOfNames = new JSONArray();

        for (Player p : team) {
            JSONObject json = new JSONObject();
            json.put("name", p.getName());
            listOfNames.put(json);
        }

        return listOfNames;
    }

    public void replace(Player player1, Player player2) {
        for (int i = 0; i < 11; i++) {
            if (team.get(i).getName().equals(player2.getName())) {
                team.set(i, player1);
                EventLog.getInstance().logEvent(new Event("Player "
                        + player2.getName() + " was "
                        + "replaced by " + player1.getName()));
            }
        }
    }
}


