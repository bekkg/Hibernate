package org.peaksoft.model.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

// TODO: 07.10.2023 в классе StudentIdCard должен быть поля(Long id, String identityNumber,
//  LocalDate createDate)
@Entity
@Table(name = "studentIdCards")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StudentIdCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   private String identityNumber;
   private LocalDate createDate;
    @ToString.Exclude
    @OneToOne(cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
   private Student student;

    public StudentIdCard(String identityNumber, LocalDate createDate) {
        this.identityNumber = identityNumber;
        this.createDate = createDate;
    }
}
