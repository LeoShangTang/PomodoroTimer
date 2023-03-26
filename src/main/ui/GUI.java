package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    int count = 0;
    JLabel label;
    JPanel panel;
    JFrame frame;

    GUI() {
//
//        frame = new JFrame();
//        panel = new JPanel();
//
//        JButton button = new JButton("Click Me");
//        panel.add(button);
//
//        button.addActionListener(this);
//

//
//
//        panel.setBorder(BorderFactory.createEmptyBorder(500, 500, 250, 250));
//        panel.setLayout(new GridLayout(0, 1));
//
//        frame.add(panel, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("Pomodoro Timer");
//        frame.pack();
//        frame.setVisible(true);

        frame = new JFrame("Pomodoro Timer");
        panel = new JPanel();

        //frame.getContentPane();
        // Button
        JButton button = new JButton("Demo Button");
        button.addActionListener(this);
        Dimension size = button.getPreferredSize();
        button.setBounds(200, 200, size.width, size.height);

        label = new JLabel("Number of clicks: 0");
        panel.add(label);
        Dimension size2 = label.getPreferredSize();
        label.setBounds(150,150,size2.width,size2.height);

        // PANEL
        panel.setLayout(null);
        panel.add(button);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // FRAME
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks" + count);

    }
}
