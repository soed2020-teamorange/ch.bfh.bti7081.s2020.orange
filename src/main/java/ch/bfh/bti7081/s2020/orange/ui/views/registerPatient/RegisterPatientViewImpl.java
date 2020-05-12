package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.spring.annotation.UIScope;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@UIScope
@Component
@RequiredArgsConstructor
public class RegisterPatientViewImpl extends VerticalLayout implements RegisterPatientView,
    HasLogger {

  private Grid<Patient> registeredPatientGrid = new Grid<>(Patient.class, false);
  private List<Patient> patients = new ArrayList<Patient>();
  private Binder<User> binder = new BeanValidationBinder<>(User.class);
  private final PasswordEncoder passwordEncoder;

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {

    registeredPatientGrid.addColumn(Patient::getFirstName).setHeader("Vorname").setSortable(true);
    registeredPatientGrid.addColumn(Patient::getLastName).setHeader("Nachname").setSortable(true);
    registeredPatientGrid.addColumn(Patient::getEmail).setHeader("E-Mail");

    add(new H1("Neuen Patienten registrieren"),
        buildForm(),
            new H2("Alle vorhandenen Patienten"),
            registeredPatientGrid);
  }

  private Div buildForm() {

    // Create UI components
    TextField firstName = new TextField("Vorname");
    TextField lastName = new TextField("Nachname");
    EmailField email = new EmailField("E-Mail");

    Button saveButton = new Button("Speichern");
    Div errorsLayout = new Div();

    // Configure UI components
    saveButton.setThemeName("primary");

    // Wrap components in layouts
    FormLayout formLayout = new FormLayout(firstName, lastName, email, saveButton);
    Div wrapperLayout = new Div(formLayout, errorsLayout);

    Binder<Patient> binder = new Binder<>(Patient.class);
    binder.forField(firstName)
        .asRequired("Vorname muss angegeben werden.")
        .bind(Patient::getFirstName, Patient::setFirstName);

    binder.forField(lastName)
        .asRequired("Nachname muss angegeben werden.")
        .bind(Patient::getLastName, Patient::setLastName);

    binder.forField(email)
            .asRequired("E-Mail muss angegeben werden.")
            .withValidator(new EmailValidator(
            "Bitte eine korrekte E-Mail angeben."))
            .bind(Patient::getEmail, Patient::setEmail);

    binder.readBean(new Patient());

    binder.addStatusChangeListener(status -> {
          saveButton.setEnabled(!status.hasValidationErrors());
        }
    );

    saveButton.addClickListener(click -> {
      try {
        errorsLayout.setText("");

        Patient newPatient = new Patient();
        binder.writeBean(newPatient);
        // TODO: ein Passwort generieren?
        newPatient.setPasswordHash(passwordEncoder.encode("1234"));

        observer.createNewPatient(newPatient);

        binder.readBean(new Patient());

      } catch (ValidationException e) {
        // TODO: wie mit Exception umgehen
      }
    });

    formLayout.setResponsiveSteps(
            new FormLayout.ResponsiveStep("25em", 1),
            new FormLayout.ResponsiveStep("32em", 2),
            new FormLayout.ResponsiveStep("40em", 3));

    return wrapperLayout;
  }

  @Override
  public void setUser(User user) {
    getLogger().info("Set user with e-mail ["+user.getEmail()+"]");
    binder.setBean(user);
  }

  @Override
  public void setPatients(List<Patient> patients) {
    getLogger().info("Set patients into grid");
    registeredPatientGrid.setItems(patients);
    registeredPatientGrid.getDataProvider().refreshAll();
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
