package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
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
  
  @ToString.Exclude
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "patient")
  private List<Prescription> prescriptions;

  public Patient(final String email, final String passwordHash, final String firstName,
      final String lastName,
      final LocalDate birthDate) {
    super(email, passwordHash, firstName, lastName, birthDate, Role.PATIENT);
    prescriptions = new ArrayList<>();
  }

  public Patient(final String email, final String passwordHash, final String firstName,
      final String lastName,
      final LocalDate birthDate, final MedicalSpecialist medicalSpecialist) {
    super(email, passwordHash, firstName, lastName, birthDate, Role.PATIENT);
    this.medicalSpecialist = medicalSpecialist;
    prescriptions = new ArrayList<>();
    
  }


}
