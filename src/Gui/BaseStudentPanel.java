package Gui;

import javax.swing.*;
import java.awt.*;
import Manager.StudentManager;

public abstract class BaseStudentPanel extends JPanel {
    protected StudentManager manager;
    public BaseStudentPanel(StudentManager manager) {
        this.manager = manager;
        setBackground(new Color(245, 245, 245));
    }

    protected void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public abstract void setupUI();
}