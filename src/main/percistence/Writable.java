package percistence;

import org.json.JSONObject;


// REFERENCE : code below was referred from the following project :
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public interface Writable {

    // EFFECTS : returns this as JSON object
    JSONObject toJson();
}
