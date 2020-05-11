package ch.bfh.bti7081.s2020.orange.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import lombok.Data;

@Entity
@Inheritance
@Data
public abstract class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private String lastName;
  private String firstName;

  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }


  /*private Date birthDate;
  private String street;
  private String streetNumber;
  private String city;
  private int zipCode;
  private String country;
  private String phoneNumber;*/
}
