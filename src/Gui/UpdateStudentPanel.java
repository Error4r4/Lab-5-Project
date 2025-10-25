package Gui;

import javax.swing.*;
import java.awt.*;
import Model.Student;
import Manager.StudentManager;

public class UpdateStudentPanel extends BaseStudentPanel {
    private JTextField idField, nameField, ageField, departmentField, gpaField;
    private JComboBox<String> genderBox;
    private JButton searchButton, updateButton;
    private Student currentStudent;

    public UpdateStudentPanel(StudentManager manager) {
        super(manager);
        setLayout(new GridBagLayout());
        setupUI();
    }

    @Override
    public void setupUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Update Student Information");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;

        gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("Student ID:"), gbc);
        idField = new JTextField(10);
        gbc.gridx = 1;
        add(idField, gbc);

        gbc.gridy = 2; gbc.gridx = 0; gbc.gridwidth = 2;
        searchButton = new JButton("Search");
        add(searchButton, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 3; gbc.gridx = 0;
        add(new JLabel("Name:"), gbc);
        nameField = new JTextField(15);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridy = 4; gbc.gridx = 0;
        add(new JLabel("Age:"), gbc);
        ageField = new JTextField(5);
        gbc.gridx = 1;
        add(ageField, gbc);

        gbc.gridy = 5; gbc.gridx = 0;
        add(new JLabel("Gender:"), gbc);
        genderBox = new JComboBox<>(new String[]{"Male", "Female"});
        gbc.gridx = 1;
        add(genderBox, gbc);

        gbc.gridy = 6; gbc.gridx = 0;
        add(new JLabel("Department:"), gbc);
        departmentField = new JTextField(10);
        gbc.gridx = 1;
        add(departmentField, gbc);

        gbc.gridy = 7; gbc.gridx = 0;
        add(new JLabel("GPA:"), gbc);
        gpaField = new JTextField(5);
        gbc.gridx = 1;
        add(gpaField, gbc);

        gbc.gridy = 8; gbc.gridx = 0; gbc.gridwidth = 2;
        updateButton = new JButton("Update Student");
        add(updateButton, gbc);

        searchButton.addActionListener(e -> searchStudent());
        updateButton.addActionListener(e -> updateStudent());
    }

    private void searchStudent() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            currentStudent = manager.searchById(id);

            if (currentStudent != null) {
                nameField.setText(currentStudent.getName());
                ageField.setText(String.valueOf(currentStudent.getAge()));
                genderBox.setSelectedItem(currentStudent.getGender());
                departmentField.setText(currentStudent.getDepartment());
                gpaField.setText(String.valueOf(currentStudent.getGpa()));
                showMessage("Student found!");
            } else {
                showMessage("No student found with this ID.");
            }
        } catch (NumberFormatException ex) {
            showMessage("Please enter a valid numeric ID.");
        }
    }

    private void updateStudent() {
        if (currentStudent == null) {
            showMessage("Search for a student first!");
            return;
        }

        try {
            currentStudent.setName(nameField.getText().trim());
            currentStudent.setAge(Integer.parseInt(ageField.getText().trim()));
            currentStudent.setGender((String) genderBox.getSelectedItem());
            currentStudent.setDepartment(departmentField.getText().trim());
            currentStudent.setGpa(Double.parseDouble(gpaField.getText().trim()));

            manager.updateStudent(currentStudent);
            showMessage("Student updated successfully!");
        } catch (NumberFormatException ex) {
            showMessage("Please enter valid numeric values for age and GPA.");
        }
    }
}