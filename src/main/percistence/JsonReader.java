package percistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// REFERENCE : code below was referred from the following project :
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {

    private String source;
    private ArrayList<Player> players;
    private DisplayTeams dt;

    // EFFECTS: constructs reader to read from source file, using list of players and display teams
    public JsonReader(String source, ArrayList<Player> players, DisplayTeams dt) {
        this.source = source;
        this.players = players;
        this.dt = dt;
    }

    // EFFECTS: reads DisplayTeams from file and returns it;
    public DisplayTeams read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        EventLog.getInstance().logEvent(new Event("A save team was loaded"));
        return parseTeams(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS : parses DisplayTeams from JSON object and returns it
    private DisplayTeams parseTeams(JSONObject jsonObject) {
        addTeams(dt, jsonObject);
        return dt;
    }

    // MODIFIES : DisplayTeams
    // EFFECTS : parses Team from JSON object and adds them to DisplayTeams
    private void addTeams(DisplayTeams teams, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("HOLY BABA");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(teams, nextTeam);
        }

    }

    // MODIFIES : DisplayTeams
    // EFFECTS : parses player name from JSON object adds them to team
    private void addTeam(DisplayTeams teams, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Team newTeam = new Team(name);
        newTeam.makeTeam();
        teams.addToMyTeam(newTeam);
        for (Object json : jsonObject.getJSONArray("players")) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayer(newTeam, nextPlayer.getString("name"));
        }
    }

    // EFFECTS : compares play name with player in players
    private void addPlayer(Team team, String name) {
        for (int i = 0; i < 11; i++) {
            for (Player p : players) {
                if (p.getName().contains(name)) {
                    team.addAndReplace(p, i);
                }
            }
        }
    }
}



