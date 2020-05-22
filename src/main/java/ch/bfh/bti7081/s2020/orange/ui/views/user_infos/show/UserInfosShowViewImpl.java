package ch.bfh.bti7081.s2020.orange.ui.views.user_infos.show;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.stereotype.Component;

@UIScope
@Component
public class UserInfosShowViewImpl extends VerticalLayout implements
    UserInfosShowView,
    HasLogger {

  H1 title = new H1("Angaben anzeigen");
  Label firstNameDesc = new Label("Vorname:");
  Label firstName = new Label();
  Label lastNameDesc = new Label("Nachname:");
  Label lastName = new Label();
  H2 assignedUsersDesc = new H2();

  Label medicalSpecialistFirstNameDesc = new Label("Vorname:");
  Label medicalSpecialistFirstName = new Label();
  Label medicalSpecialistLastNameDesc = new Label("Nachname:");
  Label medicalSpecialistLastName = new Label();
  Grid<Patient> patients = new Grid<>(Patient.class);

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {
    patients
        .setColumns("firstName", "lastName", "email", "phone", "birthDate", "street",
            "streetNumber",
            "zipCode",
            "city",
            "country");
    add(title, firstNameDesc, firstName, lastNameDesc, lastName, assignedUsersDesc);
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName.setText(firstName);
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName.setText(lastName);
  }

  @Override
  public void setMedicalSpecialist(MedicalSpecialist medicalSpecialist) {
    this.assignedUsersDesc.setText("zugewiesene medizinische Fachperson:");
    this.medicalSpecialistFirstName.setText(medicalSpecialist.getFirstName());
    this.medicalSpecialistLastName.setText(medicalSpecialist.getLastName());
    add(medicalSpecialistFirstNameDesc, this.medicalSpecialistFirstName,
        medicalSpecialistLastNameDesc,
        this.medicalSpecialistLastName);
  }

  @Override
  public void setPatients(List<Patient> patients) {
    this.assignedUsersDesc.setText("zugewiesene Patienten:");
    this.patients.setItems(patients);
    add(this.patients);
  }
}
