package ch.bfh.bti7081.s2020.orange.backend.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class MedicalSpecialist extends Person {

  private String jobTitle;

  @OneToMany
  private List<Patient> patients;

  public MedicalSpecialist() {
    super("Vorname", "Nachname");
  }

}
