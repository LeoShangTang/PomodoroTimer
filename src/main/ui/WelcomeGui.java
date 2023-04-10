package ui;

import javax.swing.*;
import java.awt.*;

// Pop up welcome window that greets the user
public class WelcomeGui extends JFrame {
    private JPanel welcomePanel;

    //EFFECTS: Constructs welcomePanel
    public WelcomeGui() {
        initPanel();
    }

    //EFFECTS: Initializes panel with title, size, visibility, and disposes on close
    private void initPanel() {
        setContentPane(welcomePanel);
        setTitle("WELCOME!!!");
        Dimension size = welcomePanel.getPreferredSize();
        setSize(size);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
