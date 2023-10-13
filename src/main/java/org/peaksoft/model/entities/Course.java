package org.peaksoft.model.entities;

// TODO: 07.10.2023 в классе Course должен быть поля(Long id, String courseName,
//  String durationMonth, LocalDate createDate)

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String courseName;
   private String durationMonth;
   private LocalDate createDate;

   @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE}, mappedBy = "courses")
    private List<Student> students;

    public Course(String courseName, String durationMonth, LocalDate createDate) {
        this.courseName = courseName;
        this.durationMonth = durationMonth;
        this.createDate = createDate;
    }
}
