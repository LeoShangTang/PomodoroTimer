package model;

public class Task {
    private String taskName;
    private String timerType;
    private int numberOfTimes;

    // REQUIRES: getNumberOfTimes > 0, getTimerType must be a valid timer type
    // where valid timer types include "Break Timer" and "Study Timer"
    // EFFECTS: Constructs task with given timer type, task name,
    // and number of times task is repeated
    public Task(String taskName, String timerType, int numberOfTimes) {
        this.taskName = taskName;
        this.timerType = timerType;
        this.numberOfTimes = numberOfTimes;
    }

    // REQUIRES: getNumberOfTimes > 0
    // MODIFIES: this
    // EFFECTS: Changes the numberOfTimes of a task by numberOfTimes
    // and returns new Task
    public void changeNumberOfTimes(int numberOfTimes) {
        this.numberOfTimes = numberOfTimes;
    }

    // EFFECTS: Returns task name
    public String getTaskName() {
        return taskName;
    }

    // EFFECTS: Returns timer type
    public String getTimerType() {
        return timerType;
    }

    // EFFECTS: Returns timer type
    public int getNumberOfTimes() {
        return numberOfTimes;
    }
}
