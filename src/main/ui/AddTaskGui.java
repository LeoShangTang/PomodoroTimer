package ui;

import model.Task;
import model.TaskQueue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTaskGui extends JFrame {

    private JTextField taskNameText;
    private JComboBox comboBox1;
    private JTextField repititionsText;
    private JButton okButton;
    private JPanel addTaskPanel;
    private TimerAppWindow timerApp;

    public AddTaskGui() {
        setContentPane(addTaskPanel);
        setTitle("Add Task");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        initComboBox1();
        initOkButton();
        timerApp = new TimerAppWindow();
    }

    private void initOkButton() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timerType = (String) comboBox1.getSelectedItem();
                int repititions = Integer.parseInt(repititionsText.getText());
                String taskName = taskNameText.getText();
                timerApp.addTask(new Task(timerType, taskName, repititions));
            }
        });
    }

    private void initComboBox1() {
        String[] timerOptions = {"Work", "Break"};
        comboBox1.setModel(new DefaultComboBoxModel(timerOptions));
    }


    public TaskQueue getTaskQueue() {
        return timerApp.getTaskQueue();
    }

}
