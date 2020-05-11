package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Patient extends User {

  @ManyToOne
  private MedicalSpecialist medicalSpecialist;


  public Patient(String email, String passwordHash, String firstName, String lastName) {
    super(email, passwordHash, firstName, lastName, Role.PATIENT);
  }

  public Patient(String email, String passwordHash, String firstName, String lastName,
      MedicalSpecialist medicalSpecialist) {
    super(email, passwordHash, firstName, lastName, Role.PATIENT);
    this.medicalSpecialist = medicalSpecialist;
  }
}
