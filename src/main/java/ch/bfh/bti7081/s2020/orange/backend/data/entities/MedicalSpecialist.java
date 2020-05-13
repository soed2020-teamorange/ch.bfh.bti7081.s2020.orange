package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MedicalSpecialist extends User {

  private String jobTitle;

  @JsonIgnoreProperties("medicalSpecialist")
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "medicalSpecialist")
// we omit patients, because we got a bidirectional relation, which causes an error when printing
  @ToString.Exclude
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
