package ui.tools;

import ui.TimerApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartTimerTool extends Tool {

    JButton startTimerButton;

    public StartTimerTool(TimerApp timerApp, JComponent parent) {
        super(timerApp, parent);
    }


    @Override
    protected void createButton(JComponent parent) {
        startTimerButton = new JButton("Start");
        addToParent(parent);
    }

    @Override
    protected void addListener() {
        startTimerButton.addActionListener(new StartToolClickHandler());
    }

    private class StartToolClickHandler implements ActionListener {

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            timerApp.startTimer();
        }
    }
}
