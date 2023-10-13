package org.peaksoft.service.impl;

import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.peaksoft.model.entities.Student;
import org.peaksoft.model.entities.StudentIdCard;
import org.peaksoft.service.Service;
import org.peaksoft.util.HibernateUtil;

import java.util.List;

public class StudentIdCardService implements Service<StudentIdCard> {
    @Override
    public void create(StudentIdCard studentIdCard) {
    try (Session session = HibernateUtil.getSessionFactory().openSession()){
        session.beginTransaction();
        session.persist(studentIdCard);
        session.getTransaction().commit();
    } catch (HibernateError e){
        System.out.println(e.getMessage());
    }
    }

    @Override
    public void update(StudentIdCard studentIdCard, Long id) {
        StudentIdCard oldstudentIdCard = null;

        try ( Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            oldstudentIdCard = session.get(StudentIdCard.class,id);

            oldstudentIdCard.setIdentityNumber(studentIdCard.getIdentityNumber());
            oldstudentIdCard.setCreateDate(studentIdCard.getCreateDate());
            oldstudentIdCard.setStudent(studentIdCard.getStudent());

            session.persist(oldstudentIdCard);
            session.getTransaction().commit();
        } catch ( HibernateError e){
            System.out.println(e.getMessage());
        }
        System.out.println(oldstudentIdCard);
    }

    @Override
    public List<StudentIdCard> getAll() {
        List < StudentIdCard> studentIdCards = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            studentIdCards =  session.createQuery("FROM  StudentIdCard", StudentIdCard.class).list();
            session.getTransaction().commit();
        } catch ( HibernateError e){
            System.out.println(e.getMessage());
        }
        return studentIdCards;
    }

    @Override
    public StudentIdCard getById(Long id) {
        StudentIdCard studentIdCard = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            studentIdCard = session.get(StudentIdCard.class,id);
            session.getTransaction().commit();
        } catch ( HibernateError e ) {
            System.out.println(e.getMessage());
        }
         return studentIdCard;
    }
    @Override
    public String deleteById(Long id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.remove(session.get(StudentIdCard.class,id));
            session.getTransaction().commit();
        } catch ( HibernateError e){
            System.out.println(e.getMessage());
        }
        return "Success delete.";
    }
}
