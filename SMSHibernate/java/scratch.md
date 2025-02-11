# Hibernate Project Setup with Maven/Gradle

## 1. Create Maven/Gradle Project
Create a new Maven or Gradle project in your preferred IDE.

## 2. Add Dependencies

### **Maven (pom.xml)**
```xml
<dependencies>
    <!-- Hibernate Core -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.6.5.Final</version>
    </dependency>
    
    <!-- MySQL Connector -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>
    
    <!-- JPA API -->
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
        <version>3.1.0</version>
    </dependency>
</dependencies>
```

### **Gradle (build.gradle)**
```gradle
dependencies {
    implementation 'org.hibernate:hibernate-core:6.6.5.Final'
    implementation 'mysql:mysql-connector-java:8.0.33'
    implementation 'jakarta.persistence:jakarta.persistence-api:3.1.0'
}
```

## 3. Create `hibernate.cfg.xml`
Create the configuration file inside `src/main/resources/`.

```xml
<!DOCTYPE hibernate-configuration PUBLIC
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"
        "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>
    <session-factory>
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/your_database</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">password</property>
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>
        <property name="hibernate.show_sql">true</property>
        <property name="hibernate.hbm2ddl.auto">update</property>
        
        <mapping class="Models.Student" />
    </session-factory>
</hibernate-configuration>
```

## 4. Create `SessionFactoryUtil`
Create a utility class for managing Hibernate sessions.

```java
package Utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        getSessionFactory().close();
    }
}
```

## 5. Create Entity Class

```java
package Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String course;

    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 10)
    private String phone;

    public Student() {}
    
    public Student(String name, String course, Date dob, String email, String phone) {
        this.name = name;
        this.course = course;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
    }

    // Getters and Setters
}
```

## 6. Create DAO Interface
```java
package DAO;

import Models.Student;
import java.util.List;

public interface StudentDAO {
    void addStudent(Student student);
    void updateStudent(int rollNo, String email);
    void deleteStudent(int rollNo);
    Student viewStudent(int rollNo);
    List<Student> viewAllStudents();
}
```

## 7. Implement DAO
```java
package DAOImplementation;

import DAO.StudentDAO;
import Models.Student;
import Utils.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public void addStudent(Student student) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(student);
            tx.commit();
        }
    }

    @Override
    public void updateStudent(int rollNo, String email) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Student student = session.get(Student.class, rollNo);
            if (student != null) {
                student.setEmail(email);
                session.update(student);
            }
            tx.commit();
        }
    }

    @Override
    public void deleteStudent(int rollNo) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            Student student = session.get(Student.class, rollNo);
            if (student != null) {
                session.delete(student);
            }
            tx.commit();
        }
    }

    @Override
    public Student viewStudent(int rollNo) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, rollNo);
        }
    }

    @Override
    public List<Student> viewAllStudents() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        }
    }
}
```

## 8. Create Controller
```java
package Controller;

import DAOImplementation.StudentDAOImpl;
import Models.Student;

import java.util.Date;

public class StudentController {
    private final StudentDAOImpl dao = new StudentDAOImpl();

    public void addNewStudent(String name, String course, Date dob, String email, String phone) {
        dao.addStudent(new Student(name, course, dob, email, phone));
    }

    public void updateEmail(int rollNo, String email) {
        dao.updateStudent(rollNo, email);
    }

    public void deleteStudent(int rollNo) {
        dao.deleteStudent(rollNo);
    }
}
```

## 9. Create Main Class
```java
public class RUN {
    public static void main(String[] args) {
        System.out.println("Hibernate Project Running...");
    }
}
```

## 10. Run the Project
Compile and run the project from the main class. The Hibernate setup is now complete!

