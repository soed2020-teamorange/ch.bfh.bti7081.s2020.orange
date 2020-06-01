package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Chat extends AbstractEntity {

  @OneToOne
  private Patient patient;

  @ManyToOne
  private MedicalSpecialist medicalSpecialist;
}
