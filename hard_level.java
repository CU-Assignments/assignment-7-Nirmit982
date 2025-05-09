package model;
public class Student {
    private int studentID;
    private String name;
    private String department;
    private double marks;

    public Student(int studentID, String name, String department, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }    public int getStudentID() { return studentID; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getMarks() { return marks; }

    public void setStudentID(int id) { this.studentID = id; }
    public void setName(String name) { this.name = name; }
    public void setDepartment(String dept) { this.department = dept; }
    public void setMarks(double marks) { this.marks = marks; }
}
