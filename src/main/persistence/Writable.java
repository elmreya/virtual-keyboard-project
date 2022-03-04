package persistence;

import org.json.JSONObject;

public interface Writable {
    //Returns this as a JSON object
    JSONObject toJson();
}
