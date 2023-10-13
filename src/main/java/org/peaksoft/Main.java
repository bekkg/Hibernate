package org.peaksoft;

import org.peaksoft.model.entities.Course;
import org.peaksoft.model.entities.Instructor;
import org.peaksoft.model.entities.Student;
import org.peaksoft.model.entities.StudentIdCard;
import org.peaksoft.model.enums.Gender;
import org.peaksoft.model.enums.StudyFormat;
import org.peaksoft.service.impl.CourseService;
import org.peaksoft.service.impl.InstructorService;
import org.peaksoft.service.impl.StudentIdCardService;
import org.peaksoft.service.impl.StudentService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Course course = new Course("Java - 7", "12-04-2024", LocalDate.now());
        Instructor instructor = new Instructor("Алишер", "Абдылдаев", 23, Gender.MALE, LocalDate.now());
        Student student = new Student("Бкболсун", 25, StudyFormat.ONLINE, Gender.MALE, LocalDate.now());
        Student student2 = new Student("Narynbek", 22, StudyFormat.OFFLINE, Gender.MALE, LocalDate.now());
        StudentIdCard studentIdCard = new StudentIdCard("14", LocalDate.now());




        CourseService courseService = new CourseService();
//        courseService.create(course);
//        courseService.update(course2, 1L);
//        System.out.println(courseService.deleteById(1L));
//        System.out.println(courseService.getById(2L));
//        courseService.getAll().forEach(System.out::println);


        instructor.setCourse(courseService.getById(1L));


        InstructorService instructorService = new InstructorService();
//        instructorService.create(instructor);
//        instructorService.getAll().forEach(System.out::println);
//        System.out.println(instructorService.getById(2l));
//        System.out.println(instructorService.deleteById(1l));
//        instructorService.update(instructor,1l);



        Course course1 = courseService.getById(1L);


        List<Student> list = new ArrayList<>();
        list.add(student2);

        course1.setStudents(list);
        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        student2.setCourses(courses);




        StudentService studentService = new StudentService();
//        studentService.create(student);
//        studentService.update(student2, 14l);
//        studentService.getAll().forEach(System.out::println);
//        System.out.println(studentService.getById(1L));
//        System.out.println(studentService.deleteById(13L));


        studentIdCard.setStudent(studentService.getById(1L));


        StudentIdCardService studentIdCardService = new StudentIdCardService();
//        studentIdCardService.create(studentIdCard);
//        studentIdCardService.update(studentIdCard2, 1l);
//        studentIdCardService.getAll().forEach(System.out::println);
//        System.out.println(studentIdCardService.getById(2l));
//        System.out.println(studentIdCardService.deleteById(2l));


    }

}
