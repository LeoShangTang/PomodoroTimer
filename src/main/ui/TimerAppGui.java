package ui;

import model.Event;
import model.EventLog;
import model.Task;
import model.TaskQueue;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Where main TimerApp window runs. Allows users to start, pause, and reset timer. Also allows user to change timer
// type and add or remove tasks.
// Adapted ideas from: https://www.youtube.com/watch?v=aIdIXsi1qTU
public class TimerAppGui extends JFrame implements WindowListener {

    private JButton resetButton;
    private JButton startButton;
    private JButton pauseButton;
    private JTable taskTable;
    private JLabel timerText;
    private JButton addTaskButton;
    private JButton removeTaskButton;
    private JPanel mainJPanel;
    private JButton workButton;
    private JButton breakButton;
    private JButton saveButton;
    private JButton loadDataButton;
    private JButton settingsButton;

    private Timer timer;
    private int seconds;
    private int minutes;
    private AddTaskGui addTaskGui;
    private TaskQueue taskQueue;
    private String timerType;

    private JsonWriter jsonWriter;
    private static final String JSON_STORE = "./data/taskqueue.json";
    private JsonReader jsonReader;
    private ImageIcon checkMark;

    //EFFECT: Constructor for TimerAppGui which is run in main
    public TimerAppGui() {

        taskQueue = new TaskQueue();
        addTaskGui = null;

        addWindowListener(this);

        initPanel();

        initTimerLabel();
        initTimer();
        initWorkButton();
        initBreakButton();
        initStartButton();
        initPauseButton();
        initResetButton();

        initAddButton();
        initRemoveTaskButton();

        initSettingsButton();

        createTable();
        showTasks();
        initSaveDataButton();
        initLoadDataButton();

        initializeJsonWriter();
        initializeJsonReader();

        checkMark = new ImageIcon("correct.png");
    }


    // EFFECTS: Initializes add button graphics. When add button is pressed, a new window opens where user
    // can insert task to add
    private void initAddButton() {
        TimerAppGui timerAppGui = this;
        buttonSettings(addTaskButton, false);
        addTaskButton.addActionListener(e -> new AddTaskGui(taskQueue, timerAppGui));
    }

    //EFFECTS: Initialize remove task button. When button is pressed, opens remove task gui window for user to remove
    // task for a specific number of times
    private void initRemoveTaskButton() {
        TimerAppGui timerAppGui = this;
        buttonSettings(removeTaskButton, false);
        removeTaskButton.addActionListener(e -> new RemoveTaskGui(taskQueue, timerAppGui));
    }

    // EFFECTS: Initializes add settings button graphics. When add setting button is pressed,
    // a new window opens where user can print EventLog or Clear EventLog
    private void initSettingsButton() {
        buttonSettings(settingsButton, false);
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingsGui();
            }
        });
    }

    // MODIFIES: this, JButton
    //EFFECTS: initializes save data button and saves data
    private void initSaveDataButton() {
        buttonSettings(saveButton, true);
        saveButton.addActionListener(e -> saveTaskQueue());
    }

    // MODIFIES: this, JButton
    //EFFECTS: initializes load data button which loads previous saved data into table
    private void initLoadDataButton() {
        buttonSettings(loadDataButton, true);
        loadDataButton.addActionListener(e -> {
            loadTaskQueue();
            showTasks();
        });
    }

    // EFFECTS: saves the workroom to file
    // ADAPTED FROM: JsonSerializationDemo
    private void saveTaskQueue() {
        try {
            jsonWriter.open();
            jsonWriter.write(taskQueue);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null,
                    "Saved to: " + JSON_STORE, "Saved Data", JOptionPane.OK_OPTION, checkMark);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to save data to file: " + JSON_STORE, "Not Saved", JOptionPane.ERROR_MESSAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    // ADAPTED FROM: JsonSerializationDemo
    private void loadTaskQueue() {
        try {
            taskQueue = jsonReader.read();
            JOptionPane.showMessageDialog(null,
                    "Loaded previously saved data from: " + JSON_STORE,
                    "Saved Data", JOptionPane.OK_OPTION, checkMark);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Unable to read data from: " + JSON_STORE, "Not Saved", JOptionPane.ERROR_MESSAGE);
        }
    }


    // MODIFIES: this, JTable
    // EFFECTS: Sets up JTable with 3 columns including Task, timer type, and repititions.
    // Adapted from https://www.youtube.com/watch?v=3m1j3PiUeVI and https://www.youtube.com/watch?v=teSBvFy9NH8
    public void createTable() {
        Object[][] data = {};
        taskTable.setModel(new DefaultTableModel(data, new String[]{"Task", "Timer Type", "Repititions"}));
    }

    // MODIFIES: this, JTable
    // EFFECTS: Displays tasks onto JTable by adding rows for each task in taskQueue. Before adding rows,
    // table is reset to a row count of 0
    // Adapted from https://www.youtube.com/watch?v=3m1j3PiUeVI
    public void showTasks() {
        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        model.setRowCount(0);
        for (Task task : taskQueue.getTaskQueue()) {
            model.addRow(new Object[]{
                    task.getTaskName(),
                    task.getTimerType(),
                    task.getNumberOfTimes()
            });
        }
    }

    //MODIFIES: this, JLabel
    //EFFECTS:  Initializes timer label to 25 minutes
    private void initTimerLabel() {
        timerText.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        timerText.setText("25:00");
    }


    //MODIFIES: this
    //EFFECTS: When seconds reaches -1, seconds is set to 59 and we take away 1 minute
    public void secondsReachesZero() {
        if (this.seconds == -1) {
            this.seconds = 59;
            minutes--;
        }
    }

    //EFFECTS: Stops timer when timer is done which is when both seconds and minutes equal 0. TimerDoneGui window
    // also pops up
    public void timerDone() {
        if (this.seconds == 0 && this.minutes == 0) {
            new TimerDoneGui();
            timer.stop();
        }
    }

    // MODIFIES: this, JButton
    // EFFECTS: Initializes timer with staring time of 25 minutes with a countdown of 1 second
    private void initTimer() {
        this.timerType = "Work";
        this.minutes = 25;
        this.seconds = 0;
        timer = new Timer(1000, e -> {
            seconds--;
            secondsReachesZero();
            timerDone();
            if (seconds < 10) {
                timerText.setText(minutes + ":" + "0" + seconds);
            } else {
                timerText.setText(minutes + ":" + seconds);
            }

        });
    }

    // MODIFIES: JButton
    // EFFECTS: Initializes pause button graphics. When pressed, timer pauses.
    private void initPauseButton() {
        buttonSettings(pauseButton, true);
        pauseButton.addActionListener(e -> timer.stop());
    }

    // MODIFIES: JButton
    // EFFECTS: Initializes start button graphics. When pressed, timer starts.
    private void initStartButton() {
        buttonSettings(startButton, true);
        startButton.addActionListener(e -> timer.start());
    }

    // MODIFIES: this, JButton
    // EFFECTS: Initializes work button graphics. When work button is pressed, the timer type is set to work time
    // of 25 minutes and timer is reset and paused.
    private void initWorkButton() {
        buttonSettings(workButton, true);
        workButton.addActionListener(e -> {
            minutes = 25;
            seconds = 0;
            timerType = "Work";
            timerText.setText(minutes + ":" + "0" + seconds);
            timer.stop();
        });
    }

    // MODIFIES: this, JButton
    // EFFECTS: Initializes break button graphics. When break button is pressed, the timer type is set to break time
    // of 5 minutes and timer is reset and paused.
    private void initBreakButton() {
        buttonSettings(breakButton, true);
        breakButton.addActionListener(e -> {
            minutes = 5;
            seconds = 0;
            timerType = "Break";
            timerText.setText(minutes + ":" + "0" + seconds);
            timer.stop();
        });
    }

    // MODIFIES: this, JButton
    // EFFECTS: Initializes reset button graphics. When pressed, timer pauses. Label and time is
    // reset to the timer type time. When timer type is "Work", resets to 25 minutes. When timer type is  "Break",
    // resets to 5 minutes.
    private void initResetButton() {
        buttonSettings(resetButton, false);
        resetButton.addActionListener(e -> {
            if (timerType.equals("Work")) {
                seconds = 0;
                minutes = 25;
            } else {
                seconds = 0;
                minutes = 5;
            }
            timerText.setText(minutes + ":" + "0" + seconds);
            timer.stop();
        });
    }

    // MODIFIES: JButton
    // EFFECTS: Sets up button setting with no color but has outline
    private void buttonSettings(JButton button, boolean b) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(b);
    }

    //MODIFIES: JPanel
    //EFFECTS: Initializes panel with title, size, visibility, and disposes on close
    private void initPanel() {
        setContentPane(mainJPanel);
        setTitle("Pomodoro Timer");
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //EFFECT: When window is closed, play welcome window known as WelcomeGui
    @Override
    public void windowOpened(WindowEvent e) {
        new WelcomeGui();
    }

    //EFFECT: When window is closed, print out the Event log into the console
    @Override
    public void windowClosing(WindowEvent e) {
        String logEventText = "";
        for (Event next : EventLog.getInstance()) {
            logEventText +=  next.getDate() + "  -> " + next.getDescription() + System.lineSeparator();
        }
        System.out.println(logEventText);
    }

    //EFFECTS: Initializes JsonWriter
    private void initializeJsonWriter() {
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    //EFFECTS: Initializes JsonReader
    private void initializeJsonReader() {
        jsonReader = new JsonReader(JSON_STORE);
    }


    //EFFECT: Window closes like default. Action possibly to be added later.
    @Override
    public void windowClosed(WindowEvent e) {

    }

    //EFFECT: Window iconfined like default. Action possibly to be added later.
    @Override
    public void windowIconified(WindowEvent e) {

    }

    //EFFECT: Window deiconfined like default. Action possibly to be added later.
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    //EFFECT: Window activated like default. Action possibly to be added later.
    @Override
    public void windowActivated(WindowEvent e) {

    }

    //EFFECT: Window deactivated like default. Action possibly to be added later.
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
