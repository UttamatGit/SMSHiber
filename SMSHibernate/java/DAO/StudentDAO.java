package DAO;

import Models.Student;

import java.util.Date;
import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    void deleteStudent(int rollNo);
    void viewStudent(int rollNo);
    List<Student> viewAllStudents();
    void updateStudentEmail(int rollNo,String email);
    void updatePhone(int rollNo, String phone);
    void updateDob(int rollNo, Date DOB);
    void updateName(int rollNo,String Name);
}
