package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalSpecialist extends User {

  private String jobTitle;

  @OneToMany
  private List<Patient> patients;

  public MedicalSpecialist(String email, String passwordHash, String firstName, String lastName) {
    super(email, passwordHash, firstName, lastName, Role.PATIENT);
  }

  public MedicalSpecialist(String email, String passwordHash, String firstName, String lastName,
      String jobTitle, List<Patient> patients) {
    super(email, passwordHash, firstName, lastName, Role.PATIENT);
    this.jobTitle = jobTitle;
    this.patients = patients;
  }

}
