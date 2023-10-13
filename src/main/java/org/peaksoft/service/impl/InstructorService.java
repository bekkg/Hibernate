package org.peaksoft.service.impl;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.peaksoft.model.entities.Instructor;
import org.peaksoft.model.entities.Student;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class InstructorService implements Service<Instructor> {
    @Override
    public void create(Instructor instructor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(instructor);
            session.getTransaction().commit();
        } catch (HibernateError e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Instructor instructor, Long id) {
        Instructor oldinstructor = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            oldinstructor = session.get(Instructor.class, id);

            oldinstructor.setName(instructor.getName());
            oldinstructor.setAge(instructor.getAge());
            oldinstructor.setCourse(instructor.getCourse());

            session.persist(oldinstructor);
            session.getTransaction().commit();
        } catch (HibernateError e) {
            System.out.println(e.getMessage());
        }
        System.out.println(oldinstructor);
    }

    @Override
    public List<Instructor> getAll() {
        List<Instructor> instructors = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            instructors = session.createQuery("FROM  Instructor ", Instructor.class).list();
            session.getTransaction().commit();
        } catch (HibernateError e) {
            System.out.println(e.getMessage());
        }
        return instructors;
    }

    @Override
    public Instructor getById(Long id) {
        Instructor instructor = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            instructor = session.get(Instructor.class, id);
            session.getTransaction().commit();
        } catch (HibernateError e) {
            System.out.println(e.getMessage());
        }
        return instructor;
    }

    @Override
    public String deleteById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.remove(session.get(Instructor.class, id));
            session.getTransaction().commit();
        } catch (HibernateError e) {
            System.out.println(e.getMessage());
        }
        return "Success  delete.";
    }
}
