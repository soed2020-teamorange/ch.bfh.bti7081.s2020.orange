package ch.bfh.bti7081.s2020.orange.backend.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Patient extends Person {

  @ManyToOne
  private MedicalSpecialist medicalSpecialist;

  public Patient() {
  }

  public Patient(long id) {
    this.setId(id);
  }

}
