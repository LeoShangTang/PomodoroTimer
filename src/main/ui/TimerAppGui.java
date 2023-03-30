package ui;

import model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerAppGui extends JFrame {

    private JButton resetButton;
    private JButton startButton;
    private JButton pauseButton;
    private JTable taskTable;
    private JLabel label1;
    private JButton addTaskButton;
    private JButton removeTaskButton;
    private JPanel mainJPanel;
    private JButton workButton;
    private JButton breakButton;
    private TimerApp timerApp;
    private Timer timer;
    private int seconds;
    private int minutes;
    private AddTaskGui addTaskGui;

    private String timerType;

    public TimerAppGui() {
       // taskQueue = new TaskQueue();

//        Task task1 = new Task("Amongus","Break",2);
//        Task task2 = new Task("Sad","Break",2);
        timerApp = new TimerApp();
        addTaskGui = new AddTaskGui();

        initTimerLabel();
        initTimer();

        setContentPane(mainJPanel);
        setTitle("welcome");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        initStartButton();
        initPauseButton();
        initResetButton();

        initAddButton();
        initWorkButton();
        initBreakButton();

        createTable();
        showTasks();
    }

    // EFFECTS: Initializes add button graphics. When add button is pressed, a new window opens where user
    // can insert task to add
    private void initAddButton() {
        addTaskButton.setContentAreaFilled(false);
        addTaskButton.setFocusPainted(false);
        addTaskButton.setBorderPainted(false);
        addTaskButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addTaskGui = new AddTaskGui();
                showTasks();
            }
        });
    }

    // EFFECTS: Sets up JTable with 3 columns includeing Task, timer type, and repititions
    public void createTable() {
        Object[][] data = {};
        taskTable.setModel(new DefaultTableModel(data, new String[]{"Task", "Timer Type", "Repititions"}));
    }

    // EFFECTS: Displays tasks onto JTable by adding rows for each task in taskQueue
    public void showTasks() {
        DefaultTableModel model = (DefaultTableModel) taskTable.getModel();
        for (Task task : addTaskGui.getTaskQueue().getTaskQueue()) {
            model.addRow(new Object[]{
                    task.getTaskName(),
                    task.getTimerType(),
                    task.getNumberOfTimes()
            });
        }
    }

    //MODIFIES: this
    //EFFECTS:  Initializes timer label to 25 minutes
    private void initTimerLabel() {
        label1.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        label1.setText("25:00");
    }


    //MODIFIES: this
    //EFFECTS: When seconds reaches 0
    public void secondsReachesZero() {
        if (this.seconds == -1) {
            this.seconds = 59;
            minutes--;
        }
    }

    //EFFECTS: Stops timer when timer is done which is when both seconds and minutes equal 0
    public void timerDone() {
        if (this.seconds == 0 && this.minutes == 0) {
            timer.stop();
        }
    }


    // EFFECTS: Initializes timer with staring time of 25 minutes with a countdown
    private void initTimer() {
        this.timerType = "Work";
        this.minutes = 25;
        this.seconds = 0;
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                secondsReachesZero();
                timerDone();
                if (seconds < 10) {
                    label1.setText(minutes + ":" + "0" + seconds);
                } else {
                    label1.setText(minutes + ":" + seconds);
                }

            }
        });
    }


    // EFFECTS: Initializes pause button graphics. When pressed, timer pauses.
    private void initPauseButton() {
        pauseButton.setContentAreaFilled(false);
        pauseButton.setFocusPainted(false);
        pauseButton.setBorderPainted(true);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.stop();
            }
        });
    }

    // EFFECTS: Initializes start button graphics. When pressed, timer starts.
    private void initStartButton() {
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(true);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
    }

    // EFFECTS: Initializes work button graphics. When work button is pressed, the timer type is set to work time
    // of 25 minutes and timer is reset and paused.
    private void initWorkButton() {
        workButton.setContentAreaFilled(false);
        workButton.setFocusPainted(false);
        workButton.setBorderPainted(true);
        workButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                minutes = 25;
                seconds = 0;
                timerType = "Work";
                label1.setText(minutes + ":" + "0" + seconds);
                timer.stop();
            }
        });
    }

    // EFFECTS: Initializes break button graphics. When break button is pressed, the timer type is set to break time
    // of 5 minutes and timer is reset and paused.
    private void initBreakButton() {
        breakButton.setContentAreaFilled(false);
        breakButton.setFocusPainted(false);
        breakButton.setBorderPainted(true);
        breakButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                minutes = 5;
                seconds = 0;
                timerType = "Break";
                label1.setText(minutes + ":" + "0" + seconds);
                timer.stop();
            }
        });
    }
    // EFFECTS: Initializes reset button graphics. When pressed, timer pauses. Label and time is
    // reset to the timer type time.
    private void initResetButton() {
        resetButton.setContentAreaFilled(false);
        resetButton.setFocusPainted(false);
        resetButton.setBorderPainted(false);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (timerType.equals("Work")) {
                    seconds = 0;
                    minutes = 25;
                } else {
                    seconds = 0;
                    minutes = 5;
                }
                label1.setText(minutes + ":" + "0" + seconds);
                timer.stop();
            }
        });
    }

}
