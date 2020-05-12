package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

  @NotEmpty
  @Email
  @Size(max = 255)
  @Column(unique = true)
  private String email;

  @NotNull
  @Size(min = 4, max = 255)
  private String passwordHash;

  @NotBlank
  @Size(max = 255)
  private String firstName;

  @NotBlank
  @Size(max = 255)
  private String lastName;

  @NotBlank
  @Size(max = 255)
  private String role;

  @PrePersist
  @PreUpdate
  private void prepareData() {
    this.email = email == null ? null : email.toLowerCase();
  }


}
