package Gui;

import javax.swing.*;
import java.awt.*;
import Manager.StudentManager;
import Model.Student;

public class DeleteStudentPanel extends BaseStudentPanel {
    private JTextField idField;
    private JLabel nameLabel, deptLabel, gpaLabel;
    private JButton searchButton, deleteButton, clearButton;
    private Student currentStudent;

    public DeleteStudentPanel(StudentManager manager) {
        super(manager);
        setLayout(new GridBagLayout());
        setupUI();
    }

    @Override
    public void setupUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Delete Student");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        add(title, gbc);
        gbc.gridwidth=1;

        gbc.gridy=1; gbc.gridx=0;
        add(new JLabel("Student ID:"), gbc);
        idField = new JTextField(10);
        gbc.gridx=1;
        add(idField, gbc);

        gbc.gridy=2; gbc.gridx=0; gbc.gridwidth=2;
        searchButton = new JButton("Search");
        add(searchButton, gbc);
        gbc.gridwidth=1;

        gbc.gridy=3; gbc.gridx=0;
        add(new JLabel("Name:"), gbc);
        nameLabel = new JLabel("-");
        gbc.gridx=1;
        add(nameLabel, gbc);

        gbc.gridy=4; gbc.gridx=0;
        add(new JLabel("Department:"), gbc);
        deptLabel = new JLabel("-");
        gbc.gridx=1;
        add(deptLabel, gbc);

        gbc.gridy=5; gbc.gridx=0;
        add(new JLabel("GPA:"), gbc);
        gpaLabel = new JLabel("-");
        gbc.gridx=1;
        add(gpaLabel, gbc);

        gbc.gridy=6; gbc.gridx=0; gbc.gridwidth=2;
        JPanel buttonPanel = new JPanel();
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, gbc);

        searchButton.addActionListener(e -> searchStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearFields());
    }

    private void searchStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            currentStudent = manager.searchById(id);
            if (currentStudent == null) {
                showMessage("Student not found.");
                clearFields();
                return;
            }
            nameLabel.setText(currentStudent.getName());
            deptLabel.setText(currentStudent.getDepartment());
            gpaLabel.setText(String.valueOf(currentStudent.getGpa()));
        } catch (NumberFormatException ex) {
            showMessage("Enter a valid numeric ID.");
        }
    }

    private void deleteStudent() {
        if (currentStudent == null) {
            showMessage("Search for a student first.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete student: " + currentStudent.getName() + "?",
                "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            manager.deleteById(currentStudent.getId());
            showMessage("Student deleted successfully.");
            clearFields();
        }
    }

    private void clearFields() {
        idField.setText("");
        nameLabel.setText("-");
        deptLabel.setText("-");
        gpaLabel.setText("-");
        currentStudent = null;
    }
}