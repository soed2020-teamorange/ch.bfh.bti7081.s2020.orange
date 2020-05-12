package ch.bfh.bti7081.s2020.orange.backend.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractEntity {

  public User(String email, String passwordHash, String firstName, String lastName, String role) {
    this.email = email;
    this.passwordHash = passwordHash;
    this.firstName = firstName;
    this.lastName = lastName;
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
    this.email = email == null ? null : email.toLowerCase();
  }

}
