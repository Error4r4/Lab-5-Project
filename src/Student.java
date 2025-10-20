public class Student
{
    private int id;
    private String name;
    private int age;
    private String gender;
    private String department;
    private double gpa;

    public Student (int id, String name, int age, String gender, String department, double gpa)
    {
        this.id =id;
        this.name =name;
        this.age =age;
        this.gender =gender;
        this.department =department;
        this.gpa =gpa;
    }

    public void setId(int id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setAge(int age){this.age = age;}
    public void setGender(String gender){this.gender = gender;}
    public void setDepartment(String department){this.department = department;}
    public void setGpa(double gpa){this.gpa = gpa;}
    public int getId(){return id;}
    public String getName(){return name;}
    public int getAge(){return age;}
    public String getGender() {return gender;}
    public String getDepartment() {return department;}
    public double getGpa() {return gpa;}

    @Override
    public String toString()
    {
        return id + "," + name + "," + age + "," + gender + "," + department + "," + gpa;
    }
}
