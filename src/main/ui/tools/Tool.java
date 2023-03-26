package ui.tools;

import ui.TimerApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

// ADAPTED FROM: SimpleDrawingPlayer
public abstract class Tool {

    protected TimerApp timerApp;
    protected JButton button;

    public Tool(TimerApp timerApp, JComponent parent) {
        this.timerApp = timerApp;
        createButton(parent);
        addToParent(parent);
        addListener();
    }

    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        button.setBackground(Color.white);
        return button;
    }

    //EFFECTS: Creates button for our tools
    protected abstract void createButton(JComponent parent);

    //EFFECTS: Adds listener
    protected abstract void addListener();

    //MODIFIES: parent
    //EFFECTS: adds button to parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: default behaviour does nothing
    public void mousePressedInDrawingArea(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void mouseReleasedInDrawingArea(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void mouseClickedInDrawingArea(MouseEvent e) {
    }

    // EFFECTS: default behaviour does nothing
    public void mouseDraggedInDrawingArea(MouseEvent e) {
    }

}
