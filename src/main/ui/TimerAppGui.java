package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerAppGui extends JFrame {

    private JButton resetButton;
    private JButton startButton;
    private JButton pauseButton;
    private JTable table1;
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

    private int breakSeconds;
    private int breakMinutes;

    private int workSeconds;
    private int workMinutes;


    public TimerAppGui() {
        timerApp = new TimerApp();
        initLabel1();
        initTimer();

        setContentPane(mainJPanel);
        setTitle("welcome");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        initStartButton();
        initPauseButton();
        initResetButton();

        initAddButton();
    }

    private void initAddButton() {
        addTaskButton.setContentAreaFilled(false);
        addTaskButton.setFocusPainted(false);
        addTaskButton.setBorderPainted(false);
        addTaskButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void initResetButton() {
        resetButton.setContentAreaFilled(false);
        resetButton.setFocusPainted(false);
        resetButton.setBorderPainted(false);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds = 0;
                minutes = 25;
                label1.setText(minutes + ":" + "0" + seconds);
                timer.stop();
            }
        });
    }


    private void initPauseButton() {
//        pauseButton.setForeground(new Color(105,105,105));
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

    private void initStartButton() {
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(true);
//        startButton.setForeground(new Color(105,105,105));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timer.start();
            }
        });
    }

    private void initTimer() {
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
                    label1.setText(minutes  + ":" +  seconds);
                }

            }
        });
    }

    private void initLabel1() {
        label1.setFont(new Font(Font.DIALOG,Font.BOLD,30));
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

    //EFFECTS: Starts the timer
    public void startTimer() {
        timer.start();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
