package model;


import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountDownInfo {

    private Boolean isTimerRunning;
    private int seconds;
    private int minutes;

    public CountDownInfo(int minutes) {
        isTimerRunning = false;
        this.minutes = minutes;
    }

    public void simpleTimer() {
        Timer timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                if (seconds == -1) {
                    seconds = 59;
                    minutes--;
                }
                if (minutes == 0 && seconds == 0) {
                    isTimerRunning = false;
                }
            }

        });
    }

    public void resumeTimer() {
        isTimerRunning = true;
    }

    public void pauseTimer() {
        isTimerRunning = false;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public Boolean getIsTimerRunning() {
        return isTimerRunning;
    }


}
