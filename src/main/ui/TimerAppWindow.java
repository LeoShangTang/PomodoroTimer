package ui;

import exceptions.NegativeNumberOrZero;
import exceptions.OptionNotInList;
import model.CountDownTimer;
import model.Task;
import model.TaskQueue;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TimerAppWindow {

    private static final String JSON_STORE = "./data/taskqueue.json";
    private TaskQueue queue;
    private CountDownTimer countDownTimer;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECT: Used to initialize queue and run timer from Main
    public TimerAppWindow() {
        initializeQueue();
        initializeJsonWriter();
        initializeJsonReader();
    }


    //REQUIRES: User must enter "Break" or "Work" when they are asked the timer type.
    // The user must enter an integer greater than 0 when asked the number of times a task should be repeated
    //  When user is asked if they would like to add another task, they must input "yes" or "no".
    //MODIFIES: this
    //EFFECTS: User inputs name of task, timer type, times task should be repeated, and if user wants to add
    //another task. Once User inputs task name, timer type, and number of repititions, the task is added to queue
    public void addTask(Task task) {
        queue.addTask(task);
    }

    //REQUIRES: Task must be a member of queue. The user must enter an integer greater than 0 when asked the number of
    // times a task should be repeated.
    //MODIFIES: this, Task
    //EFFECTS: User removes task from queue by typing name of task that they want to remove. User can remove task by
    // specific number of repititions.
    private void removeTask(String name, int numOfTimes) throws OptionNotInList, NegativeNumberOrZero {
        Boolean removeTaskRunning = true;
        try {
            queue.removeTask(name, numOfTimes);
        } catch (OptionNotInList e) {
            System.out.println("Invalid Option");

        } catch (NegativeNumberOrZero e) {
            System.out.println("Invalid number");
        }

    }


    // EFFECTS: saves the workroom to file
    // ADAPTED FROM: JsonSerializationDemo
    private void saveTaskQueue() {
        try {
            jsonWriter.open();
            jsonWriter.write(queue);
            jsonWriter.close();
            System.out.println("Saved to: " + JSON_STORE);
            System.out.println("Remember to load your data after you quit the program");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save data to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    // ADAPTED FROM: JsonSerializationDemo
    private void loadTaskQueue() {
        try {
            queue = jsonReader.read();
            System.out.println("Loaded previously saved data from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //EFFECTS: Initializes queue
    private void initializeQueue() {
        queue = new TaskQueue();
    }


    //EFFECTS: Initializes JsonWriter
    private void initializeJsonWriter() {
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    //EFFECTS: Initializes JsonReader
    private void initializeJsonReader() {
        jsonReader = new JsonReader(JSON_STORE);
    }

    public TaskQueue getTaskQueue() {
        return queue;
    }

}
