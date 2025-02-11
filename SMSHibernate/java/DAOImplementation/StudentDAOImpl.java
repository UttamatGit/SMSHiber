package DAOImplementation;

import DAO.StudentDAO;
import Models.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Utils.utilSessionFactory;

import java.util.Date;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public void addStudent(Student student) {
        Transaction transaction = null;
        try(Session session = utilSessionFactory.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();;
        } catch (HibernateException e) {
            if (transaction!=null) transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStudentEmail(int rollNo, String email) {
        Transaction transaction = null;
        try(Session session = utilSessionFactory.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Student updateStudent = session.get(Student.class,rollNo);
            if (updateStudent != null){
                updateStudent.setEmail(email);
                session.update(updateStudent);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePhone(int rollNo, String phone) {
        Transaction transaction = null;
        try(Session session = utilSessionFactory.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Student phoneStudent = session.get(Student.class,phone);
            if (phoneStudent != null){
                phoneStudent.setPhone(phone);
                session.update(phoneStudent);
                transaction.commit();
            }
        }
        catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateDob(int rollNo, Date DOB) {
        Transaction transaction = null;
        try(Session session = utilSessionFactory.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Student dobStudent = session.get(Student.class,DOB);
            if (dobStudent != null){
                dobStudent.setPhone(String.valueOf(DOB));
                session.update(dobStudent);
                transaction.commit();
            }
        }
        catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void updateName(int rollNo, String Name) {
        Transaction transaction = null;
        try(Session session = utilSessionFactory.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Student nameStudent = session.get(Student.class,Name);
            if (nameStudent != null){
                nameStudent.setName(Name);
                session.update(nameStudent);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteStudent(int rollNo) {
        Transaction transaction = null;
        try (Session session = utilSessionFactory.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            Student student = session.get(Student.class,rollNo);
            if (student!= null){
                session.delete(student);
                transaction.commit();
            }
        }
        catch (Exception e){
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void viewStudent(int rollNo) {
        try (Session session = utilSessionFactory.getSessionFactory().openSession()) {
            Student student = session.get(Student.class, rollNo);
            if (student != null) {
                System.out.println(student.getName() + " | " + student.getCourse() + " | " + student.getEmail() + " | "+student.getDob());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> viewAllStudents() {
        try(Session session = utilSessionFactory.getSessionFactory().openSession()) {
            return session.createQuery("from Student", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
}
