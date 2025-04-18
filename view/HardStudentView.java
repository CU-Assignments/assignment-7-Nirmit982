package view;
import controller.StudentDAO;
import model.Student;
import java.util.*;

public class StudentView {
    private final StudentDAO dao = new StudentDAO();
    private final Scanner sc = new Scanner(System.in);
    public void menu() {
        int choice;
        do {
            System.out.println("\n1. Add Student\n2. View All\n3. Update\n4. Delete\n5. Exit");
            System.out.print("Choose: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> viewAll();
                case 3 -> updateStudent();
                case 4 -> deleteStudent();
                case 5 -> System.out.println("Goodbye.");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }
    private void addStudent() {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Name: "); String name = sc.nextLine();
        System.out.print("Dept: "); String dept = sc.nextLine();
        System.out.print("Marks: "); double marks = sc.nextDouble();
        dao.insertStudent(new Student(id, name, dept, marks));
    }
    private void viewAll() {
        List<Student> list = dao.getAllStudents();
        for (Student s : list) {
            System.out.printf("%d | %s | %s | %.2f\n",
                    s.getStudentID(), s.getName(), s.getDepartment(), s.getMarks());
        }
    }
    private void updateStudent() {
        System.out.print("Enter ID to update: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("New Name: "); String name = sc.nextLine();
        System.out.print("New Dept: "); String dept = sc.nextLine();
        System.out.print("New Marks: "); double marks = sc.nextDouble();
        dao.updateStudent(new Student(id, name, dept, marks));
    }
    private void deleteStudent() {
        System.out.print("Enter ID to delete: "); int id = sc.nextInt();
        dao.deleteStudent(id);
    }
}
