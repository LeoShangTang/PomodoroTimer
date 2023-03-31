package persistence;

import org.json.JSONObject;

// ADAPTED FROM: JsonSerializationDemo
// Includes toJson() method for JsonWriter and JsonReader to return JSON object
public interface Writable {
    // EFFECTS: returns as JSON object
    JSONObject toJson();
}
