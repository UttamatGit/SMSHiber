package Models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_sequence", allocationSize = 1)
    int rollNo;
    @Column(length = 50,nullable = false)
    String name;
    @Column(length = 50,nullable = false)
    String course;
    @Column(name = "DOB",nullable = false)
    @Temporal(TemporalType.DATE)
    Date dob;
    @Column(length = 100,unique = true,nullable = false)
    String email;
    @Column(length = 15, nullable = false)
    String phone;

    public Student() {
    }

    public Student(String name, String course, Date dob, String email, String phone) {
        this.name = name;
        this.course = course;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    public Student(String name, String course, LocalDate dob, String email, String phone) {
    }
    public Student(String email){
        this.email = email;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
