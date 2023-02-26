
package ui;

import exceptions.InvalidOption;
import model.Task;
import model.TaskQueue;

import java.util.Scanner;

public class TimerApp {

    private TaskQueue queue;

    // EFFECT: Used to initialize queue and run timer from Main
    public TimerApp() {
        initQueue();
        runTimer();
    }

    // EFFECT: Runs the timer by running instructions method and chosenOption method
    private void runTimer() {
        instructions();
        chosenOption();
    }

    // EFFECT: Prints instructions for user
    private void instructions() {

        System.out.println("\nInstructions (do not include brackets):");
        System.out.println("\t[Print Queue] or [pq] to print Queue");
        System.out.println("\t[Add Task] or [at] to add a task to Queue");
        System.out.println("\t[Remove Task] or [rt] to remove task in Queue");
        System.out.println("\t[Repititions] or [r] to retrieve number of times a task is being repeated");
        System.out.println("\t[Quit] or [q]  to remove task in Queue");

    }

    //EFFECTS: Executes method according to user's input. When user types "Print Queue" or "pq", chosenOption
    // executes the printQueue method. If user inputs invalid option, prints "Enter a valid option please"
    private void chosenOption() {
        boolean optionWindowRunning = true;
        Scanner input = new Scanner(System.in);

        while (optionWindowRunning) {
            String chosen = input.nextLine();
            try {
                if (chosen.equals("Print Queue") || chosen.equals("pq")) {
                    printQueue();
                } else if (chosen.equals("Add Task") || chosen.equals("at")) {
                    addTask();
                    System.out.println("Done!");
                } else if (chosen.equals("Remove Task") || chosen.equals("rt")) {
                    removeTask();
                } else if (chosen.equals("Repititions") || chosen.equals("r")) {
                    getRepititions();
                } else if (chosen.equals("Quit") || chosen.equals("q")) {
                    optionWindowRunning = false;
                } else {
                    throw new InvalidOption();
                }
            } catch (InvalidOption e) {
                System.out.println("Enter a valid option please");
            }
        }
    }

    //EFFECTS: Prints the names of tasks in the queue. If queue is empty, print "Queue is empty"
    private void printQueue() {
        try {
            if (queue.emptyQueue()) {
                throw new InvalidOption();
            }
            System.out.print("Tasks Include: ");
            for (Task t : queue.getTaskQueue()) {
                System.out.print("[" + t.getTaskName() + "] ");
            }
            System.out.println("");
        } catch (InvalidOption e) {
            System.out.println("Queue is empty");
        }
    }

    //REQUIRES: When user is asked if they would like to add another task, they must input "yes" or "no".
    // User must enter "Break" or "Work" when they are asked the timer type. The user must enter an integer greater
    // than 0 when asked the number of times a task should be repeated
    //MODIFIES: this
    //EFFECTS: User inputs name of task, timer type, times task should be repeated, and if user wants to add
    //another task. Once User inputs task name, timer type, and number of repititions, the task is added to queue
    private void addTask() {
        Boolean addTaskRunning = true;

        Scanner input = new Scanner(System.in);

        while (addTaskRunning) {

            System.out.println("What is the name of the task that you want to add?");
            String taskNameInput = input.nextLine();
            System.out.println("What is the timer type? (Break or Work)");
            String taskTimerInput = input.nextLine();
            assert (taskTimerInput.equals("Break") || taskTimerInput.equals("Work"));
            System.out.println("How many times should the task be repeated?");
            int taskRepetitionInput = Integer.valueOf(input.nextLine());
            assert (taskRepetitionInput > 0);
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

    //REQUIRES: task must be a member of queue
    //MODIFIES: this
    //EFFECTS: User removes task from queue by typing name of task that they want to remove. User can remove task by
    // specific number of repititions.
    private void removeTask() {
        Boolean removeTaskRunning = true;
        Scanner input = new Scanner(System.in);

        while (removeTaskRunning) {
            System.out.println("Which task would you like to remove?");
            String taskNameInput = input.nextLine();
            assert (queue.memberOfQueue(taskNameInput));
            System.out.println("How many times would you like to remove this task?");
            int numOfTimes = Integer.valueOf(input.nextLine());
            queue.removeTask(taskNameInput, numOfTimes);

            System.out.println("Would you like to remove another task? (yes or no)");
            String anotherTaskInput = input.nextLine();

            if (anotherTaskInput.equals("no") || anotherTaskInput.equals("No")) {
                removeTaskRunning = false;
            } else if (anotherTaskInput.equals("yes") || anotherTaskInput.equals("Yes")) {
                removeTaskRunning = true;
            } else {
                System.out.println("Invalid Input");
                removeTaskRunning = false;
            }
        }
        runTimer();
    }

    //REQUIRES: Task must be a member of the queue
    //EFFECTS: User inputs task and retrieves number of repititions in that task
    private void getRepititions() {
        Scanner input = new Scanner(System.in);
        System.out.println("Which task would you like to retrieve repititions?");
        String taskNameInput = input.nextLine();
        assert (queue.memberOfQueue(taskNameInput));
        int result = queue.retrieveRepetitions(taskNameInput);
        System.out.println(result);
        runTimer();
    }

    //EFFECTS: Initializes queue
    private void initQueue() {
        queue = new TaskQueue();
    }

}
