package org.peaksoft.model.entities;

// TODO: 07.10.2023  в классе Instructor должен быть поля(Long id,String name, String lastName, int age,
//  Gender gender, LocalDate createDate)
//  Relationship: Instructor and Course.На одном курсе может быть несколько инструкторов,
//  но один инструктор не может работать на нескольких курсах

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.peaksoft.model.enums.Gender;

import java.time.LocalDate;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@ToString
@NoArgsConstructor


public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private String name;
   private String lastName;
   private int age;

   @Enumerated(EnumType.STRING)
   private Gender gender;

   private LocalDate createDate;

   @ToString.Exclude
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
   private Course course;


    public Instructor(String name, String lastName, int age, Gender gender, LocalDate createDate) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.createDate = createDate;
    }
}
