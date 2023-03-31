package ui;

import javax.swing.*;
import java.awt.*;

// Pop up window to tell user that the timer has finished
public class TimerDoneGui extends JFrame {

    private JPanel timerDonePanel;
    private JLabel timerDoneLabel;

    public TimerDoneGui() {
        timerDoneLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
        initPanel();
    }

    //EFFECTS: Initializes panel with title, size, visibility, and disposes on close
    private void initPanel() {
        setContentPane(timerDonePanel);
        setTitle("welcome");
        Dimension size = timerDonePanel.getPreferredSize();
        setSize(size);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
