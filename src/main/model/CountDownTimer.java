package model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Where a timer is constructed and is counted down. Timer has minutes and seconds portion.
// ADAPTED FROM: https://www.youtube.com/watch?v=zWw72j-EbqI&t=103s
public class CountDownTimer {

    private int minutes;
    private int seconds;
    Timer timer;

    //REQUIRES: Minutes can not be negative
    //EFFECTS: Constructs countDownTimer with minutes and seconds
    public CountDownTimer(int minutes, int seconds) {

        this.seconds = seconds;
        this.minutes = minutes;

    }

    //MODIFIES: this
    //EFFECTS: Counts down timer by one second. When second reaches 0,
    //increment minutes by 1 down, and sets seconds to 59
    public void countDown() {

        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                secondsReachesZero();
                timerDone();
            }
        });

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

    //EFFECTS: Stops the timer
    public void stopTimer() {
        timer.stop();
    }

    //EFFECTS: Returns seconds
    public int getSeconds() {
        return seconds;
    }

    //EFFECTS: Returns minutes
    public int getMinutes() {
        return minutes;
    }

}
