
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
        chosenOptionTimerSettings();
    }

    // EFFECT: Prints instructions for user to follow
    private void instructions() {

        System.out.println("\nInstructions (do not include brackets):");
        System.out.println("\t[Print Queue] or [pq] to print Queue");
        System.out.println("\t[Add Task] or [at] to add a task to Queue");
        System.out.println("\t[Remove Task] or [rt] to remove task in Queue");
        System.out.println("\t[Repititions] or [r] to retrieve number of times a task is being repeated");
        System.out.println("\t[Timer Settings] or [ts] to change timer settings");
        System.out.println("\t[Start Timer] or [st] to start and resume timer");
        System.out.println("\t[Pause Timer] or [pt] to pause timer");
        System.out.println("\t[Reset Timer] or [rt] to reset timer");
        System.out.println("\t[Quit] or [q]  to quit");

    }

    // EFFECT: Prints out settings of the timer for user's to change
    private void timerSettings() {
        System.out.println("\nTimer Settings (do not include brackets):");
        System.out.println("\t[Break Timer] or [bt] to change break timer time");
        System.out.println("\t[Work Timer] or [wt] to change work timer time");
    }

    //EFFECTS: Executes method according to user's input. Example: When user types "Print Queue" or "pq", chosenOption
    // executes the printQueue method. If user inputs invalid option, prints "Enter a valid option please"
    private void chosenOption() {
        boolean optionWindowRunning = true;
        Scanner input = new Scanner(System.in);

        while (optionWindowRunning) {
            String chosen = input.nextLine();
            if (chosen.equals("Print Queue") || chosen.equals("pq")) {
                printQueue();
            } else if (chosen.equals("Add Task") || chosen.equals("at")) {
                addTask();
            } else if (chosen.equals("Remove Task") || chosen.equals("rt")) {
                removeTask();
            } else if (chosen.equals("Repititions") || chosen.equals("r")) {
                getRepititions();
            } else if (chosen.equals("Timer Settings") || chosen.equals("ts")) {
                timerSettings();
            } else if (chosen.equals("Start Timer") || chosen.equals("st")) {
                startOrResumeTimer();
            } else if (chosen.equals("Pause Timer") || chosen.equals("pt")) {
                pauseTimer();
            } else if (chosen.equals("Quit") || chosen.equals("q")) {
                optionWindowRunning = false;
            } else {
                System.out.println("Enter a valid option please");
            }
        }
    }

    private void chosenOptionTimerSettings() {
        boolean optionTimerWindowRunning = true;
        Scanner input = new Scanner(System.in);

        while (optionTimerWindowRunning) {
            String chosen = input.nextLine();
            if (chosen.equals("Break Timer") || chosen.equals("bt")) {
                changeBreakTimer();
            } else if (chosen.equals("Work TImer") || chosen.equals("wt")) {
                changeWorkTimer();
            } else if (chosen.equals("Quit") || chosen.equals("q")) {
                optionTimerWindowRunning = false;
            } else {
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

    //REQUIRES: User must enter "Break" or "Work" when they are asked the timer type.
    // The user must enter an integer greater than 0 when asked the number of times a task should be repeated
    //  When user is asked if they would like to add another task, they must input "yes" or "no".
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
            int numOfTimes = Integer.valueOf(input.nextLine());
            assert (numOfTimes > 0);

            Task task = new Task(taskNameInput, taskTimerInput, numOfTimes);
            queue.addTask(task);

            System.out.println("Would you like to add another task? (Yes or No)");
            String anotherTaskInput = input.nextLine();
            assert (anotherTaskInput.equals("Yes") || anotherTaskInput.equals("No"));

            if (anotherTaskInput.equals("No")) {
                addTaskRunning = false;
            } else if (anotherTaskInput.equals("Yes")) {
                addTaskRunning = true;
            }
        }
        runTimer();
    }

    //REQUIRES: Task must be a member of queue. The user must enter an integer greater than 0 when asked the number of
    // times a task should be repeated.
    //MODIFIES: this, Task
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
            assert (numOfTimes > 0);

            queue.removeTask(taskNameInput, numOfTimes);

            System.out.println("Would you like to remove another task? (Yes or No)");
            String anotherTaskInput = input.nextLine();
            assert (anotherTaskInput.equals("Yes") || anotherTaskInput.equals("No"));

            if (anotherTaskInput.equals("No")) {
                removeTaskRunning = false;
            } else if (anotherTaskInput.equals("Yes")) {
                removeTaskRunning = true;
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

        System.out.println(queue.retrieveRepetitions(taskNameInput));
        runTimer();
    }

    // REQUIRES: User must input a number > 0
    // EFFECT: Changes the amount of time for break timer
    private void changeBreakTimer() {

    }

    // REQUIRES: User must input a number > 0
    // EFFECT: Changes the amount of time for work timer
    private void changeWorkTimer() {

    }

    // REQUIRES: Can only start one timer at a time. To start another timer, the current timer must be reset or skipped
    // EFFECT: Starts or resumes timer that is currently running
    private void startOrResumeTimer() {

    }

    // EFFECT: Pauses timer
    private void pauseTimer() {

    }

    //EFFECTS: Initializes queue
    private void initQueue() {
        queue = new TaskQueue();
    }

}
