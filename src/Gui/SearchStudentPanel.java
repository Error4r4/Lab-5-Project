package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Manager.StudentManager;
import Model.Student;

public class SearchStudentPanel extends BaseStudentPanel {

    private JTextField idField;
    private JButton searchButton;
    private JTextArea resultArea;

    public SearchStudentPanel(StudentManager manager) {
        super(manager);
        setupUI(); // set up everything when panel is created
    }

    @Override
    public void setupUI() {
        setLayout(new BorderLayout(15, 15)); // main layout for the panel

        // ---- Top Section: Title ----
        JLabel titleLabel = new JLabel("Search Student by ID", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));
        add(titleLabel, BorderLayout.NORTH);

        // ---- Center Section: Form ----
        JPanel formPanel = new JPanel(new FlowLayout());
        formPanel.add(new JLabel("Enter Student ID: "));

        idField = new JTextField(10);
        formPanel.add(idField);

        searchButton = new JButton("Search");
        formPanel.add(searchButton);

        add(formPanel, BorderLayout.CENTER);

        // ---- Bottom Section: Result Display ----
        resultArea = new JTextArea(8, 40);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // ---- Button Click Event ----
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
    }

    private void performSearch() {
        String input = idField.getText().trim();

        if (input.isEmpty()) {
            showMessage("Please enter a student ID.");
            return;
        }

        try {
            int id = Integer.parseInt(input);
            Student s = manager.searchById(id);

            if (s == null) {
                showMessage("No student found with ID: " + id);
                resultArea.setText("");
            } else {
                String info = String.format(
                        "ID: %d\nName: %s\nAge: %d\nGender: %s\nDepartment: %s\nGPA: %.2f",
                        s.getId(), s.getName(), s.getAge(), s.getGender(), s.getDepartment(), s.getGpa());
                resultArea.setText(info);
            }
        } catch (NumberFormatException ex) {
            showMessage("Invalid input! Please enter numbers only for the ID.");
        }
    }
}
