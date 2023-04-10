package ui;

import model.Event;
import model.EventLog;

import javax.swing.*;

// Pop up window that displays the EventLog
public class PrintLogGui extends JFrame {


    private JPanel logScreenPanel;
    private JTextArea displayText;

    //EFFECTS: Constructs PrintLogGui
    public PrintLogGui() {
        displayText.setEditable(false);
        initPanel();
        printLog(EventLog.getInstance());
    }

    //EFFECTS: Displays EventLog as displayText
    public void printLog(EventLog el) {
        String eventLogText = "";
        for (Event next : el) {
            eventLogText += displayText.getText() + next.getDate() + "  -> " + next.getDescription() + System.lineSeparator();
        }
        displayText.setText(eventLogText);
    }

    //EFFECTS: Initializes panel with title, size, visibility, and disposes on close
    private void initPanel() {
        setContentPane(logScreenPanel);
        setSize(500, 200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
