package persistence;

import org.json.JSONObject;

// ADAPTED FROM: JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns as JSON object
    JSONObject toJson();
}
