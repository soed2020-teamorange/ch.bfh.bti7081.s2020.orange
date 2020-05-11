package ch.bfh.bti7081.s2020.orange.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import com.vaadin.flow.component.polymertemplate.Id;
import lombok.Data;

@Entity
@Data
public class Patient extends Person {

    @ManyToOne
    private MedicalSpecialist medicalSpecialist;

    public Patient() {
    }

    public Patient(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format(
                "Patient[id=%d, firstName='%s', lastName='%s']",
                getId(), getFirstName(), getLastName());
    }
}
