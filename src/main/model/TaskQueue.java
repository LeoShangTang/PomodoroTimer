package model;

import java.util.LinkedList;

public class TaskQueue {
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

    // REQUIRES: numOfTimes < number of times that the task
    // MODIFIES: this, Task
    // EFFECTS: Removes numOfTimes by how many times the task is
    // being repeated in the Queue. If numOfTimes is equivalent to
    // the number of times that the task is being repeated, remove task
    // from queue completely
    public void removeTask(String task, int numOfTimes) {
        for (Task t : this.taskQueue) {
            if (t.getTaskName() == task) {
                if (numOfTimes == t.getNumberOfTimes()) {
                    this.taskQueue.remove(t);
                } else {
                    t.changeNumberOfTimes(t.getNumberOfTimes() - numOfTimes);
                }
            }
        }
    }

    //REQUIRES: task name must be a part of taskqueue
    //EFFECT: Takes task name and returns index in taskqueue
    public int getIndexOfTask(String task) {
        for (Task t : this.taskQueue) {
            if (t.getTaskName() == task) {
                return this.taskQueue.indexOf(t);
            }
        }
        return 0;
    }

    //EFFECT: Returns length of taskqueue
    public int getQueueLength() {
        return this.taskQueue.size();
    }
}