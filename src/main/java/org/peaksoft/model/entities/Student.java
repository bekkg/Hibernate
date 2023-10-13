package org.peaksoft.model.entities;

// TODO: 07.10.2023 в классе Student должен быть поля (Long id, String name, int age,
//  StudyFormat studyFormat(enum),Gender gender, LocalDate createDate)
//  Relationships:
//  1. Student and StudentIdCard. У каждого Студента должна быть только одна StudentIdCard
//  2. Student and Course. Студент может обучаться на нескольких курсах, и на одном курсе может быть несколько студентов

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.peaksoft.model.enums.Gender;
import org.peaksoft.model.enums.StudyFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private   long id;

   private String name;
   private int age;

   @Enumerated(EnumType.STRING)
   private StudyFormat studyFormat;

   @Enumerated(EnumType.STRING)
   private Gender gender;

   private LocalDate creatDate;



   @ToString.Exclude
   @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
   @JoinTable(name = "students_and_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Course> courses;

   @OneToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH},mappedBy = "student")
   private  StudentIdCard studentIdCard;


    public Student(String name, int age, StudyFormat studyFormat, Gender gender, LocalDate creatDate) {
        this.name = name;
        this.age = age;
        this.studyFormat = studyFormat;
        this.gender = gender;
        this.creatDate = creatDate;
    }
}
