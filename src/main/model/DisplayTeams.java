package model;

import org.json.JSONArray;
import org.json.JSONObject;
import percistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// represents teams to be displayed
public class DisplayTeams implements Writable {

    private ArrayList<Team> preExisting;
    private ArrayList<Team> myTeam;

    // constructs teams to be displayed
    // EFFECTS : teams can be pre-existing or my teams
    public DisplayTeams() {
        this.preExisting = new ArrayList<>();
        this.myTeam = new ArrayList<>();
    }

    // EFFECTS : returns pre-existing teams
    public ArrayList<Team> getPreExisting() {
        return preExisting;
    }

    // EFFECTS : returns my teams
    public ArrayList<Team> getMyTeam() {
        return myTeam;
    }

    // MODIFIES : this
    // EFFECTS : adds a team to pre-existing teams
    public void addToPreExisting(Team t) {
        this.preExisting.add(t);
    }

    // MODIFIES : this
    // EFFECTS : adds a team to my teams
    public void addToMyTeam(Team t) {
        this.myTeam.add(t);
    }


    // REFERENCE : code below was referred from the following project :
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

    // EFFECTS : converts myTeam to json Object
    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teams",teamsToJson());
        return jsonObject;
    }

    // EFFECTS : returns teams in this (myTeam) as a JSON array
    private JSONArray teamsToJson() {
        JSONArray teams = new JSONArray();

        for (Team t : myTeam) {
            teams.put(t.toJson());
        }

        return teams;
    }
}
