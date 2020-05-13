package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
public class Patient extends User {

  @JsonIgnoreProperties("patients")
// we omit medicalSpecialist, because we got a bidirectional relation, which causes an error when printing
  @ToString.Exclude
  @ManyToOne(fetch = FetchType.LAZY)
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
