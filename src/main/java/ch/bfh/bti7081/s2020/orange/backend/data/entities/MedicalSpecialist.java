package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MedicalSpecialist extends User {

  private String jobTitle;

  // we omit patients, because we got a bidirectional relation, which causes an error when printing
  @ToString.Exclude
  @OneToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "medical_specialist_id")
  private List<Patient> patients;

  public MedicalSpecialist(String email, String passwordHash, String firstName, String lastName) {
    super(email, passwordHash, firstName, lastName, Role.MEDICAL_SPECIALIST);
  }

  public MedicalSpecialist(String email, String passwordHash, String firstName, String lastName,
      String jobTitle, List<Patient> patients) {
    super(email, passwordHash, firstName, lastName, Role.MEDICAL_SPECIALIST);
    this.jobTitle = jobTitle;
    this.patients = patients;
  }

}
