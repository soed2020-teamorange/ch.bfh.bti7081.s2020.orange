package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Patient extends User {

  // we omit medicalSpecialist, because we got a bidirectional relation, which causes an error when printing
  @ToString.Exclude
  @ManyToOne
  private MedicalSpecialist medicalSpecialist;

  public Patient(String email, String passwordHash, String firstName, String lastName,
      LocalDate birthDate) {
    super(email, passwordHash, firstName, lastName, birthDate, Role.PATIENT);
  }

  public Patient(String email, String passwordHash, String firstName, String lastName,
      LocalDate birthDate, MedicalSpecialist medicalSpecialist) {
    super(email, passwordHash, firstName, lastName, birthDate, Role.PATIENT);
    this.medicalSpecialist = medicalSpecialist;
  }


}
