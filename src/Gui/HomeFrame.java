//HomeFrame.java 
package Gui;

import Manager.StudentManager;
import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    private StudentManager manager;
    private JPanel mainPanel;

    public HomeFrame(StudentManager manager) {
        this.manager = manager;
        initUI();
    }

    private void initUI() {
        setTitle("Student Management System");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(6, 1, 10, 10));
        navPanel.setPreferredSize(new Dimension(180, 600));

        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View Students");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton searchBtn = new JButton("Search Student");
        JButton exitBtn = new JButton("Logout");


        navPanel.add(addBtn);
        navPanel.add(viewBtn);
        navPanel.add(updateBtn);
        navPanel.add(deleteBtn);
        navPanel.add(searchBtn);
        navPanel.add(exitBtn);

        // Main Panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JLabel("Welcome! Choose an action from left menu."), BorderLayout.LINE_START);
        add(navPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // Buttons Actions (Switch Panels)
        addBtn.addActionListener(e -> switchPanel(new AddStudentPanel(manager)));
        viewBtn.addActionListener(e -> switchPanel(new ViewStudentsPanel(manager)));
        updateBtn.addActionListener(e -> switchPanel(new UpdateStudentPanel(manager)));
        deleteBtn.addActionListener(e -> switchPanel(new DeleteStudentPanel(manager)));
        searchBtn.addActionListener(e -> switchPanel(new SearchStudentPanel(manager)));

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Logged out!");
            System.exit(0);
        });

        setVisible(true);
    }

    private void switchPanel(JPanel panel) {
        mainPanel.removeAll();
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}