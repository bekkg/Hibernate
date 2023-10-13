package org.peaksoft.service.impl;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.peaksoft.model.entities.Course;
import org.peaksoft.model.entities.Student;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.util.List;

public class CourseService implements Service<Course> {
    @Override
    public void create(Course course) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(course);
            session.getTransaction().commit();
        } catch (HibernateError e ){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Course course, Long id) {
        Course oldcourse = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            oldcourse = session.get(Course.class,id);

            oldcourse.setCourseName(course.getCourseName());
            oldcourse.setDurationMonth(course.getDurationMonth());
            oldcourse.setCreateDate(course.getCreateDate());

            session.persist(oldcourse);
            session.getTransaction().commit();
        } catch ( HibernateError e){
            System.out.println(e.getMessage());
        }
        System.out.println(oldcourse);
    }

    @Override
    public List<Course> getAll() {
        List <Course> courses = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            courses =  session.createQuery("FROM  Course ", Course.class).list();
            session.getTransaction().commit();
        } catch ( HibernateError e){
          throw new RuntimeException();
        }
        return courses;
    }

    @Override
    public Course getById(Long id) {
        Course course = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            course = session.get(Course.class,id);
            session.getTransaction().commit();
        } catch ( HibernateError e){
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public String deleteById(Long id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.remove(session.get(Course.class,id));
            session.getTransaction().commit();
        } catch ( HibernateError e){
            System.out.println(e);
        }
        return "Success delete.";
    }
}
