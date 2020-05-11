package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;

import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RegisterPatientViewImpl extends VerticalLayout implements RegisterPatientView, HasLogger {

  TextField firstnameField = new TextField("Vorname", "Hans");
  TextField lastnameField = new TextField("Nachname", "Muster");
  EmailField emailField = new EmailField("Email");

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {

    Binder<Patient> binder = new Binder<>(Patient.class);

    binder.forField(emailField)
            .asRequired()
            .withValidator(new EmailValidator(
            "Bitte eine korrekte E-Mailadresse angeben."));

    FormLayout registerPatientForm = new FormLayout();

    TextField birthdate = new TextField("Geburtsdatum", "01.01.1970");

    Button saveButton = new Button("Speichern");
    saveButton.addClickListener(this::onClick);

    registerPatientForm.add(firstnameField, lastnameField, emailField, birthdate, saveButton);

    registerPatientForm.setResponsiveSteps(
        new FormLayout.ResponsiveStep("25em", 1),
        new FormLayout.ResponsiveStep("32em", 2));

    add(registerPatientForm);

  }

  private void onClick(ClickEvent<Button> buttonClickEvent) {
    observer.saveNewPatient(new Patient(firstnameField.getValue(), lastnameField.getValue()));

    List<Patient> l = observer.getAllPatients();
    l.forEach(p -> getLogger().info(l.toString()));
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
