package ui;

import javax.swing.*;
import java.awt.*;

public class TimerAppWindow {
    JLabel label;
    JPanel panel;
    JFrame frame;

    TimerAppWindow() {

        frame = new JFrame();
        panel = new JPanel();

//        JButton button = new JButton("Click Me");
//        panel.add(button);
//        button.addActionListener(this);

        label = new JLabel("Number of clicks: 0");
        panel.add(label);


        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Pomodoro Timer");
        frame.pack();
        frame.setVisible(true);
    }

//    public void startTimerButton () {
//        JButton button = new JButton("Click Me");
//        panel.add(button);
//        button.addActionListener(this);
//    }

}
