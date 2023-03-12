package persistence;

import model.Task;
import model.TaskQueue;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// ADAPTED FROM: JsonSerializationDemo
// Reads taskqueue from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads taskqueue from file and returns read value and throws IOException if there is an error
    // when reading file
    public TaskQueue read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTaskQueue(jsonObject);
    }

    // EFFECTS: returns source file values read as a string
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private TaskQueue parseTaskQueue(JSONObject jsonObject) {
        TaskQueue tq = new TaskQueue();
        addTasks(tq, jsonObject);
        return tq;
    }

    // MODIFIES: tq
    // EFFECTS: parses tasks from JSON object and adds them to the taskQueue
    private void addTasks(TaskQueue tq, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("taskQueue");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(tq, nextTask);
        }
    }

    // MODIFIES: tq
    // EFFECTS: parses task from JSON object and adds it to the taskQueue
    private void addTask(TaskQueue tq, JSONObject jsonObject) {

        String taskName = jsonObject.getString("taskName");
        String timerType = jsonObject.getString("timerType");
        int numberOfTimes = jsonObject.getInt("numberOfTimes");

        Task task = new Task(taskName, timerType, numberOfTimes);
        tq.addTask(task);
    }
}
