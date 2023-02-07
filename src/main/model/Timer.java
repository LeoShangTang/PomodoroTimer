package model;

public class Timer {

    // constant times are in units of minutes
    private static int timerWorkingTime = 25;
    private static int timerShortBreakTime = 5;
    private static int timerLongBreakTime = 5;

    // timeLeft will be in units of minutes
    private String timerType; // type of timer like working timer or break timer...
    private int timeLeft;
    private boolean isTimerRunning;
    private boolean isTimerJustCompleted; // think about it

    public Timer(int timeLeft, boolean isTimerRunning) {
        this.timeLeft = timeLeft;
        this.isTimerRunning = isTimerRunning;
    }

    public void timerRunning() {

    }

    public void startTimer() {

    }

    public void pauseTimer() {

    }

    public void resumeTimer() {

    }

    public void alarm() {

    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public boolean getIsTimerRunning() {
        return isTimerRunning;
    }

    public boolean getIsTimerJustCompleted() {
        return isTimerJustCompleted;
    }
}
