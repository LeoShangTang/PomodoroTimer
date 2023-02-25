
package ui;

import model.Task;
import model.TaskQueue;

import java.util.Scanner;

public class TimerApp {

    private TaskQueue queue;

    // EFFECT:
    public TimerApp() {

        initQueue();
        runTimer();

    }

    // EFFECT:
    private void runTimer() {
        instructions();
        chosenOption();
    }

    // EFFECT: Prints instructions for user
    private void instructions() {

        System.out.println("\nInstructions (do not include brackets):");
        System.out.println("\t[Print Queue] or [pq]  to print Queue");
        System.out.println("\t[Add Task] or [at]  to add a task to Queue");
        System.out.println("\t[Remove Task] or [rt]  to remove task in Queue");
        System.out.println("\t[Quit] or [q]  to remove task in Queue");

    }

    private void chosenOption() {
        boolean optionWindowRunning = true;
        System.out.println("");
        Scanner input = new Scanner(System.in);

        while (optionWindowRunning) {
            String chosen = input.nextLine();
            if (chosen.equals("Print Queue") || chosen.equals("pq")) {
                System.out.println("Printing Queue");
                printQueue();
            } else if (chosen.equals("Add Task") || chosen.equals("at")) {
                System.out.println("Adding Task");
                addTask();
                System.out.println("Done!");
            } else if (chosen.equals("Remove Task") || chosen.equals("rt")) {
                System.out.println("Removing Task");
                removeTask();
            } else if (chosen.equals("Quit") || chosen.equals("q")) {
                System.out.print("Going back to menu");
                optionWindowRunning = false;
                runTimer();
            } else {
                System.out.println("Enter a valid option please");
            }
        }
    }

    //EFFECTS: Prints the names of tasks in the queue
    private void printQueue() {

        for (Task t : queue.getTaskQueue()) {
            System.out.print(t.getTaskName() + " ");
        }

    }

    //REQUIRES: When user is asked if they would like to add another task, they must input "yes" or "no"
    //MODIFIES: this
    //EFFECTS: User inputs name of task, timer type, times task should be repeated, and if user wants to add
    //another task
    private void addTask() {
        Boolean addTaskRunning = true;

        Scanner input = new Scanner(System.in);

        while (addTaskRunning) {
            System.out.println("What is the name of the task that you want to add?");
            String taskNameInput = input.nextLine();
            System.out.println("What is the timer type? (Break or Work)");
            String taskTimerInput = input.nextLine();
            System.out.println("How many times should the task be repeated?");
            int taskRepetitionInput = Integer.parseInt(input.nextLine());

            Task task = new Task(taskNameInput, taskTimerInput, taskRepetitionInput);
            queue.addTask(task);

            System.out.println("Would you like to add another task? (yes or no)");
            String anotherTaskInput = input.nextLine();

            if (anotherTaskInput.equals("no") || anotherTaskInput.equals("No")) {
                addTaskRunning = false;
            } else if (anotherTaskInput.equals("yes") || anotherTaskInput.equals("Yes")) {
                addTaskRunning = true;
            }

        }
        runTimer();
    }

    //EFFECTS: Removes task from queue
    private void removeTask() {
        Boolean removeTaskRunning = true;
        Scanner input = new Scanner(System.in);

        while (removeTaskRunning) {
            System.out.println("Which task would you like to remove?");
            String taskNameInput = input.nextLine();
            System.out.println("How many times would you like to remove this task?");
            int numOfTimes = Integer.parseInt(input.nextLine());
            queue.removeTask(taskNameInput, numOfTimes);

            System.out.println("Would you like to remove another task? (yes or no)");
            String anotherTaskInput = input.nextLine();

            if (anotherTaskInput.equals("no") || anotherTaskInput.equals("No")) {
                removeTaskRunning = false;
            } else if (anotherTaskInput.equals("yes") || anotherTaskInput.equals("Yes")) {
                removeTaskRunning = true;
            }
        }
        runTimer();
    }


    //EFFECTS: Initializes queue
    private void initQueue() {
        queue = new TaskQueue();
    }

    private void initTask(String taskNameInput, String taskTimerInput, int taskRepetitionInput) {
        Task task = new Task(taskNameInput, taskTimerInput, taskRepetitionInput);
    }


}
