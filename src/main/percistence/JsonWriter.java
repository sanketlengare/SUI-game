package percistence;

import model.DisplayTeams;
import model.Event;
import model.EventLog;
import model.Team;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.util.ArrayList;

// REFERENCE : code below was referred from the following project :
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git



// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;
    private DisplayTeams teams;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination, DisplayTeams teams) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of DisplayTeams to file
    public void write(DisplayTeams teams) {
        JSONArray jsonBaba = new JSONArray();
        EventLog.getInstance().logEvent(new Event("A team was saved"));
        for (Team t : teams.getMyTeam()) {
            JSONObject json = t.toJson();
            jsonBaba.put(json);
        }
        JSONObject json = new JSONObject();
        json.put("HOLY BABA", jsonBaba);
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
