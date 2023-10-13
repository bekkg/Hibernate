package org.peaksoft.service.impl;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.peaksoft.model.entities.Student;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.util.List;

public class StudentService implements Service<Student> {
    @Override
    public void create(Student t) {
        try (Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
            session.persist(t);
            session.getTransaction().commit();
        } catch (HibernateError e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Student student, Long id) {
        Student oldstudent = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
            oldstudent = session.get(Student.class,id);

            oldstudent.setName(student.getName());
            oldstudent.setAge(student.getAge());
            oldstudent.setStudyFormat(student.getStudyFormat());
            oldstudent.setGender(student.getGender());
            oldstudent.setCreatDate(student.getCreatDate());


            session.persist(oldstudent);
            session.getTransaction().commit();
        } catch (HibernateError e ){
            System.out.println(e.getMessage());
        }
        System.out.println(oldstudent);
    }

    @Override
    public List<Student> getAll() {
        List <Student> students = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            students = session.createQuery("FROM Student ",Student.class).list();
            session.getTransaction().commit();
        } catch ( HibernateError e){
            System.out.println(e.getMessage());
        }
        return students;
    }

    @Override
    public Student getById(Long id) {
        Student student = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
           student = session.get(Student.class,id);
            session.getTransaction().commit();
        } catch (HibernateError e ){
            System.out.println(e.getMessage());
        }
        return student;
    }

    @Override
    public String deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession() ) {
            session.beginTransaction();
            session.remove(session.get(Student.class,id));
            session.getTransaction().commit();
        } catch (HibernateError e ){
            System.out.println(e.getMessage());
        }
        return "The class  Student was delete.";
    }

}
