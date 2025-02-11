package Controller;

import DAO.StudentDAO;
import DAOImplementation.StudentDAOImpl;
import Models.Student;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class StudentController {
    private final StudentDAO studentDAO = new StudentDAOImpl();

    public void addNewStudent(String name, String Course, Date dob, String email, String phone) {
        Student student = new Student(name,Course,dob,email,phone);
        studentDAO.addStudent(student);
        System.out.println("Student added successfully.");
    }

    public void deleteStudent(int rollNo) {
        studentDAO.deleteStudent(rollNo);
        System.out.println("Student deleted successfully.");
    }

    public void viewStudent(int rollNo) {
        studentDAO.viewStudent(rollNo);
    }

    public void viewAllStudents() {
        List<Student> students = studentDAO.viewAllStudents();
        students.forEach(student -> System.out.println(student.getRollNo() + " | " + student.getName()));
    }

    public void updateEmail(int rollNo, String email){
        studentDAO.updateStudentEmail(rollNo,email);
        System.out.println("Email Updated Successfully");
    }
}
