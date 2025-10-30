package Gui;
import javax.swing.*;
import java.awt.*;
import Manager.StudentManager;
import Model.Student;
    public class AddStudentPanel extends BaseStudentPanel {
        private JTextField idField, nameField, ageField, departmentField, gpaField;
        private JButton addButton, clearButton;
        private JComboBox<String> genderBox;

        public AddStudentPanel(StudentManager manager) {
            super(manager);
            setLayout(new GridBagLayout());
            setupUI();
        }

        @Override
        public void setupUI() {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.anchor = GridBagConstraints.WEST;

            JLabel title = new JLabel("Add New Student");
            title.setFont(new Font("Arial", Font.BOLD, 20));
            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
            add(title, gbc);

            gbc.gridwidth = 1;

            gbc.gridy = 1; gbc.gridx = 0;
            add(new JLabel("ID:"), gbc);
            idField = new JTextField(10);
            gbc.gridx = 1;
            add(idField, gbc);

            gbc.gridy = 2; gbc.gridx = 0;
            add(new JLabel("Name:"), gbc);
            nameField = new JTextField(15);
            gbc.gridx = 1;
            add(nameField, gbc);

            gbc.gridy = 3; gbc.gridx = 0;
            add(new JLabel("Age:"), gbc);
            ageField = new JTextField(5);
            gbc.gridx = 1;
            add(ageField, gbc);

            gbc.gridy = 4; gbc.gridx = 0;
            add(new JLabel("Gender:"), gbc);
            genderBox = new JComboBox<>(new String[]{"Male", "Female"});
            gbc.gridx = 1;
            add(genderBox, gbc);

            gbc.gridy = 5; gbc.gridx = 0;
            add(new JLabel("Department:"), gbc);
            departmentField = new JTextField(10);
            gbc.gridx = 1;
            add(departmentField, gbc);

            gbc.gridy = 6; gbc.gridx = 0;
            add(new JLabel("GPA:"), gbc);
            gpaField = new JTextField(5);
            gbc.gridx = 1;
            add(gpaField, gbc);

            gbc.gridy = 7; gbc.gridx = 0;
            addButton = new JButton("Add Student");
            add(addButton, gbc);
            gbc.gridx = 1;
            clearButton = new JButton("Clear");
            add(clearButton, gbc);
            addButton.addActionListener(e -> addStudentAction());
            clearButton.addActionListener(e -> clearFields());
        }

        private void addStudentAction() {
            try {
                String idText = idField.getText().trim();
                String name = nameField.getText().trim();
                String ageText = ageField.getText().trim();
                String department = departmentField.getText().trim();
                String gpaText = gpaField.getText().trim();
                String gender = (String) genderBox.getSelectedItem();

                if (idText.isEmpty() || name.isEmpty() || ageText.isEmpty() ||
                        department.isEmpty() || gpaText.isEmpty()) {
                    showMessage("All fields must be filled!");
                    return;
                }

                int id = Integer.parseInt(idText);
                int age = Integer.parseInt(ageText);
                double gpa = Double.parseDouble(gpaText);

                if (id <= 0) {
                    showMessage("ID must be a positive number!");
                    return;
                }
                if (age <= 0) {
                    showMessage("Age must be a positive number!");
                    return;
                }

                if (gpa < 0.0 || gpa > 4.0) {
                    showMessage("GPA must be between 0.0 and 4.0!");
                    return;
                }

                if (manager.searchById(id) != null) {
                    showMessage("A student with this ID already exists!");
                    return;
                }

                Student s = new Student(id, name, age, gender, department, gpa);
                manager.addStudent(s);

                showMessage("Student added successfully!");
                clearFields();

            } catch (NumberFormatException ex) {
                showMessage("Please enter valid numeric values for ID, Age, and GPA.");
            } catch (Exception ex) {
                showMessage("An error occurred: " + ex.getMessage());
            }
        }

        private void clearFields() {
            idField.setText("");
            nameField.setText("");
            ageField.setText("");
            departmentField.setText("");
            gpaField.setText("");
        }
}