package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import java.time.LocalDate;
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
import javax.validation.constraints.Past;
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

  public User(final String email, final String passwordHash, final String firstName,
      final String lastName,
      final LocalDate birthDate, final String role) {
    this.email = email;
    this.passwordHash = passwordHash;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.role = role;
  }

  @NotBlank
  @Size(max = 255)
  private String role;

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

  @Past
  private LocalDate birthDate;

  @Size(max = 255)
  private String street;

  @Size(max = 255)
  private String streetNumber;

  @Size(max = 255)
  private String city;

  @Size(max = 255)
  private String zipCode;

  @Size(max = 255)
  private String country;

  @Size(max = 255)
  private String phone;

  @PrePersist
  @PreUpdate
  private void prepareData() {
    email = this.email == null ? null : this.email.toLowerCase();
  }

}
