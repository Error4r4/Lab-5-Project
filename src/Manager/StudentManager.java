package Manager; //Admin panel

import Model.Student;

import java.io.*;
import java.util.*;

public class StudentManager
{
   private ArrayList<Student> students = new ArrayList<>();

   public void addStudent(Student s)
   {
       students.add(s);
   }

   public ArrayList<Student> getAllStudents()
   {
       return students;
   }

   public Student searchById(int  searchKey)
   {
       for(Student s : students)
       {
        if(s.getId() == searchKey)
            return s;
       }
       return null;
   }

   public void deleteById(int searchKey)
   {
     Student s = searchById(searchKey);
     if(s != null)
     {
         students.remove(s);
         System.out.println("Deleted Successfully");
     }
     else
         System.out.println("Couldn't Find The Model.Student");
   }

    public void updateStudent(Student s)
    {
        for (int i = 0; i < students.size(); i++)
        {
            if (students.get(i).getId() == s.getId())
            {
                students.set(i, s);
                System.out.println("Updated Successfully");
                return;
            }
        }
        System.out.println("Couldn't Update The Model.Student");
    }

    public void saveStudentsToFile(String fileName) throws IOException
    {
        FileWriter writer = new FileWriter(fileName);
        for(Student s : students)
        {
            writer.write(s.toString() + "\n");
        }
        writer.close();
    }

    public void loadFromFile(String fileName) throws IOException
    {
        students.clear();
        File file = new File(fileName);
        Scanner s = new Scanner(file);
        while(s.hasNextLine())
        {
            String line = s.nextLine();
            String [] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            int age = Integer.parseInt(parts[2]);
            String gender = parts[3];
            String department = parts[4];
            double gpa = Double.parseDouble(parts[5]);
            students.add(new Student(id, name, age, gender, department, gpa));
        }
        s.close();
    }
}
