package model;

import exceptions.NegativeNumberOrZero;
import exceptions.OptionNotInList;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;

// MAYBE USE THE SINGLETON DESIGN PATTERN
// TaskQueue is a list or queue of tasks
public class TaskQueue implements Writable {
    private LinkedList<Task> taskQueue;

    // EFFECTS: Constructs task queue
    public TaskQueue() {
        this.taskQueue = new LinkedList<Task>();
    }

    // REQUIRES: When adding a task that already exists in the queue, the task must have the same timer type
    // MODIFIES: this
    // EFFECTS: add task to the end of the queue. If task already exists in the queue, add the number of repetitions to
    // the already existing task
    public void addTask(Task task) {
        if (!memberOfQueue(task.getTaskName())) {
            this.taskQueue.addLast(task);
            EventLog.getInstance().logEvent(new Event(" Added task: " + task.getTaskName()));
        } else {
            for (Task t : this.taskQueue) {
                if (t.getTaskName().equals(task.getTaskName())) {
                    t.changeNumberOfTimes(t.getNumberOfTimes() + task.getNumberOfTimes());
                    EventLog.getInstance().logEvent(new Event(" Added " + task.getNumberOfTimes()
                            + " repititions: " + task.getTaskName()));
                }
            }
        }
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
                    EventLog.getInstance().logEvent(new Event(" Removed task: " + taskName));
                    break;
                } else {
                    t.changeNumberOfTimes(t.getNumberOfTimes() - numOfTimes);
                    EventLog.getInstance().logEvent(new Event(" Removed "
                            + numOfTimes + " repititions: " + taskName));
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

    @Override
    // ADAPTED FROM: JsonSerializationDemo
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("taskQueue", taskToJson());
        return json;
    }

    // EFFECTS: returns tasks in this workroom as a JSON array
    // ADAPTED FROM: JsonSerializationDemo
    private JSONArray taskToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task task : taskQueue) {
            jsonArray.put(task.toJson());
        }

        return jsonArray;
    }
}
