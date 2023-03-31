package ui;

import model.Task;
import model.TaskQueue;

import javax.swing.*;

// Where AddTask window runs which is called from TimerAppGui. This window allows user to add tasks to the JTable in
// the main window, TimerAppGui
public class AddTaskGui extends JFrame {

    private JTextField taskNameText;
    private JComboBox timerTypeComboBox;
    private JTextField repititionsText;
    private JButton okButton;
    private JPanel addTaskPanel;
    private TaskQueue taskQueue;
    private TimerAppGui timerAppGui;

    //EFFECTS: constructor for AddTaskGui. TaskQueue from TimerAppGui is passed in as well as TimerAppGui itself
    public AddTaskGui(TaskQueue taskQueue, TimerAppGui timerAppGui) {
        initPanel();
        initTaskComboBox();
        initOkButton();
        this.taskQueue = taskQueue;
        this.timerAppGui = timerAppGui;
    }

    //MODIFIES: this, TaskQueue
    //REQUIRES: repititions > 0
    //EFFECTS: When ok button is pressed, user inputs are constructed as a new task object before being added
    // to taskQueue. TimerAppGui displays updated table and AddTaskGui window is disposed.
    private void initOkButton() {
        okButton.addActionListener(e -> {
            String timerType = (String) timerTypeComboBox.getSelectedItem();
            int repititions = Integer.parseInt(repititionsText.getText());
            String taskName = taskNameText.getText();
            taskQueue.addTask(new Task(taskName,timerType,repititions));
            timerAppGui.showTasks();
            dispose();
        });
    }

    //EFFECTS: Initializes taskComboBox to include options "Work" and "Break" for user to pick
    private void initTaskComboBox() {
        String[] timerOptions = {"Work", "Break"};
        timerTypeComboBox.setModel(new DefaultComboBoxModel(timerOptions));
    }

    //EFFECTS: Initializes panel with title, size, visibility, and disposes on close
    private void initPanel() {
        setContentPane(addTaskPanel);
        setTitle("Add Task");
        setSize(400, 250);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


}
