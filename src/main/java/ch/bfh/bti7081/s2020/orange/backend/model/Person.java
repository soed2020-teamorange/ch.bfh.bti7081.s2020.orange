package ch.bfh.bti7081.s2020.orange.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance
@Data
public abstract class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  private long id;

  @Getter
  @Setter
  private String lastName;

  @Getter
  @Setter
  private String firstName;

  private String street;
  private String streetNumber;
  private String city;
  private int zipCode;
  private String country;
  private String phoneNumber;

  public Person() {  }

  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
