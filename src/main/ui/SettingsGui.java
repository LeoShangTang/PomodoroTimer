package ui;

import model.EventLog;

import javax.swing.*;

// Pop up settings window that allows user to print or clear eventLog
public class SettingsGui extends JFrame {

    private JButton printLogToScreenButton;
    private JButton clearLogButton;
    private JPanel settingsPanel;

    //EFFECTS: Constructs SettingsGui
    public SettingsGui() {
        initLogToScreenButton();
        initClearLogButton();
        initPanel();
    }

    //EFFECTS: Initializes LogToScreen button graphics. When LogToScreenButton is pressed, a new window pops up,
    // printing the EventLog
    private void initLogToScreenButton() {
        buttonSettings(printLogToScreenButton,true);
        printLogToScreenButton.addActionListener(e -> new PrintLogGui());
    }

    //EFFECTS: Initializes ClearLog button graphics. When ClearLogButton is pressed, it clears the event log
    private void initClearLogButton() {
        buttonSettings(clearLogButton,true);
        clearLogButton.addActionListener(e -> EventLog.getInstance().clear());
    }

    //EFFECTS: Initializes panel with title, size, visibility, and disposes on close
    private void initPanel() {
        setContentPane(settingsPanel);
        setTitle("Settings");
        setSize(200,200);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    //MODIFIES: JButton
    //EFFECTS: Helper method to initialize button graphics
    private void buttonSettings(JButton button, boolean b) {
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorderPainted(b);
    }
}
