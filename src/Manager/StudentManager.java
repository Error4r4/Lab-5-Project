package Manager;

import Model.Student;
import java.io.*;
import java.util.*;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "Students.txt"; // 👈 ملف الحفظ

    public StudentManager() {
        loadFromFile(); // 👈 تحميل تلقائي عند تشغيل البرنامج
    }

    public void addStudent(Student s) {
        students.add(s);
        saveStudentsToFile(); // 👈 نحفظ التعديل مباشرة
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public Student searchById(int searchKey) {
        for (Student s : students) {
            if (s.getId() == searchKey)
                return s;
        }
        return null;
    }

    public void deleteById(int searchKey) {
        Student s = searchById(searchKey);
        if (s != null) {
            students.remove(s);
            System.out.println("Deleted Successfully");
            saveStudentsToFile(); // 👈 حفظ بعد الحذف
        } else {
            System.out.println("Couldn't Find The Student");
        }
    }

    public void updateStudent(Student s) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == s.getId()) {
                students.set(i, s);
                System.out.println("Updated Successfully");
                saveStudentsToFile(); // 👈 حفظ بعد التحديث
                return;
            }
        }
        System.out.println("Couldn't Update The Student");
    }

    // ✅ حفظ كل الطلاب في الملف
    private void saveStudentsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                writer.write(String.format("%d,%s,%d,%s,%s,%.2f",
                        s.getId(), s.getName(), s.getAge(),
                        s.getGender(), s.getDepartment(), s.getGpa()));
                writer.newLine();
            }
            System.out.println("Saved to file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // ✅ تحميل الطلاب من الملف (يتجاهل الأسطر الفاسدة)
    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No saved file found. Starting with empty list.");
            return;
        }

        try (Scanner s = new Scanner(file)) {
            students.clear();
            while (s.hasNextLine()) {
                String line = s.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 6) {
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String gender = parts[3];
                String department = parts[4];
                double gpa = Double.parseDouble(parts[5]);

                students.add(new Student(id, name, age, gender, department, gpa));
            }
            System.out.println("Loaded students from file successfully!");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing student data: " + e.getMessage());
        }
    }
}