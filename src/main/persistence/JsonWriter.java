package persistence;

import model.TaskQueue;
import org.json.JSONObject;


import java.io.*;

// ADAPTED FROM: JsonSerializationDemo
// Writer that writes JSON data that represents taskQueue to a file
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer, but throws FileNotFoundException if destination file produces error when opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }
    // new file(destination)

    // MODIFIES: this
    // EFFECTS: writes JSON data that represents taskQueue to destination file
    public void write(TaskQueue tq) {
        JSONObject json = tq.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
