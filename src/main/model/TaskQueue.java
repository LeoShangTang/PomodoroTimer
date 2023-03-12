package model;

import exceptions.NegativeNumberOrZero;
import exceptions.OptionNotInList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

public class TaskQueue implements Writable {
    private LinkedList<Task> taskQueue;

    // EFFECTS: Constructs task queue
    public TaskQueue() {
        this.taskQueue = new LinkedList<Task>();
    }

    // REQUIRES: Queue cannot have a task with the same name
    // as the task being added
    // MODIFIES: this
    // EFFECTS: add task to the end of the queue
    public void addTask(Task task) {
        this.taskQueue.addLast(task);
    }

    // MODIFIES: this, Task
    // EFFECTS: Removes numOfTimes by how many times the task is
    // being repeated in the Queue. If numOfTimes is equivalent or greater to
    // the number of times that the task is being repeated, remove task
    // from queue completely
    public void removeTask(String taskName, int numOfTimes) throws NegativeNumberOrZero, OptionNotInList {
        if (!memberOfQueue(taskName)) {
            throw new OptionNotInList();
        }
        if (numOfTimes <= 0) {
            throw new NegativeNumberOrZero();
        }
        for (Task t : this.taskQueue) {
            if (t.getTaskName().equals(taskName)) {
                if (numOfTimes >= t.getNumberOfTimes()) {
                    this.taskQueue.remove(t);
                    break;
                } else {
                    t.changeNumberOfTimes(t.getNumberOfTimes() - numOfTimes);
                }
            }
        }
    }

    //EFFECT: Takes taskName and returns the getNumberOfTimes of task with taskName.
    //  Returns -1 if taskName is not a part of taskQueue
    public int retrieveRepetitions(String taskName) {
        for (Task t : this.taskQueue) {
            if (t.getTaskName().equals(taskName)) {
                return t.getNumberOfTimes();
            }
        }
        return -1;
    }

    //EFFECT: Returns true if there is a task in Queue such that its name is equal to taskName
    public boolean memberOfQueue(String taskName) {
        for (Task t : this.taskQueue) {
            if (t.getTaskName().equals(taskName)) {
                return true;
            }
        }
        return false;
    }

    //EFFECT: Returns true if queue is empty and false otherwise
    public boolean emptyQueue() {
        return this.taskQueue.size() == 0;
    }

    //EFFECT: Takes task name and returns index in taskQueue. Returns -1 if taskName is not a part of taskQueue
    public int getIndexOfTask(String task) {
        for (Task t : this.taskQueue) {
            if (t.getTaskName().equals(task)) {
                return this.taskQueue.indexOf(t);
            }
        }
        return -1;
    }

    //EFFECT: Returns length of taskQueue
    public int getQueueLength() {
        return this.taskQueue.size();
    }

    //EFFECT: Returns taskQueue
    public LinkedList<Task> getTaskQueue() {
        return this.taskQueue;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("taskQueue", taskToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray taskToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task task : taskQueue) {
            jsonArray.put(task.toJson());
        }

        return jsonArray;
    }
}
