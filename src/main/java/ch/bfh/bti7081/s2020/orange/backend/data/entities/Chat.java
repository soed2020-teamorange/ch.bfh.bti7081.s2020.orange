package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Chat extends AbstractEntity {

  @ToString.Exclude
  @OneToMany
  private List<Message> messages;

  @OneToOne
  private Patient patient;

  @ManyToOne
  private MedicalSpecialist medicalSpecialist;
}
