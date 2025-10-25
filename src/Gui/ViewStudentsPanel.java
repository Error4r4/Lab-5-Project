package Gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import Model.Student;
import Manager.StudentManager;

public class ViewStudentsPanel extends BaseStudentPanel {
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewStudentsPanel(StudentManager manager) {
        super(manager);
        setLayout(new BorderLayout());
        setupUI();
    }

    @Override
    public void setupUI() {
        JLabel title = new JLabel("All Students");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        add(title, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new Object[]{"ID", "Name", "Age", "Gender", "Department", "GPA"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        add(refreshButton, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> loadStudents());
        loadStudents();
    }

    private void loadStudents() {
        tableModel.setRowCount(0); // clear
        List<Student> students = manager.getAllStudents();
        for (Student s : students) {
            tableModel.addRow(new Object[]{
                    s.getId(), s.getName(), s.getAge(), s.getGender(),
                    s.getDepartment(), s.getGpa()
            });
        }
        showMessage("Loaded " + students.size() + " students.");
    }
}