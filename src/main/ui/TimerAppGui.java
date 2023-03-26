package ui;

import javax.swing.*;

public class TimerAppGui extends JFrame {

    private JButton button3;
    private JButton startButton;
    private JButton pauseButton;
    private JTable table1;
    private JLabel label1;
    private JButton button1;
    private JButton button2;
    private JPanel mainJPanel;

    public TimerAppGui() {
        setContentPane(mainJPanel);
        setTitle("welcome");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
