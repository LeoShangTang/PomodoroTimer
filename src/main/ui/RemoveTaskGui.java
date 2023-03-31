package ui;

import exceptions.NegativeNumberOrZero;
import exceptions.OptionNotInList;
import model.TaskQueue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Where RemoveTask window runs which is called from TimerAppGui. This window allows user to remove tasks from JTable in
// the main window, TimerAppGui
public class RemoveTaskGui extends JFrame {

    private JTextField taskNameText;
    private JPanel removeTaskPanel;
    private JTextField repititionsText;
    private JButton okButton;
    private TimerAppGui timerAppGui;
    private TaskQueue taskQueue;

    //EFFECTS: Constructor for RemoveTaskGui. TaskQueue from TimerAppGui is passed in as well as TimerAppGui itself
    public RemoveTaskGui(TaskQueue taskQueue, TimerAppGui timerAppGui) {
        initPanel();
        this.taskQueue = taskQueue;
        this.timerAppGui = timerAppGui;
        initOkButton();
    }

    //EFFECTS: Initializes panel with title, size, visibility, and disposes on close
    private void initPanel() {
        setContentPane(removeTaskPanel);
        setTitle("Remove Task");
        setSize(300, 150);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    //EFFECTS: When ok button is pressed, user inputs remove task with specified repititions from taskQueue.
    // TimerAppGui displays updated table and RemoveTaskGui window is disposed.
    private void initOkButton() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int repititions =  Integer.parseInt(repititionsText.getText());
                String taskName = taskNameText.getText();
                try {
                    taskQueue.removeTask(taskName,repititions);
                } catch (OptionNotInList ex) {
                    throw new RuntimeException(ex);
                } catch (NegativeNumberOrZero ex) {
                    throw new RuntimeException(ex);
                }
                timerAppGui.showTasks();
                dispose();
            }
        });
    }
}
