package ch.bfh.bti7081.s2020.orange.ui.views.register_patient;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.UIScope;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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

  private final List<MedicalSpecialist> medicalSpecialists = new ArrayList<MedicalSpecialist>();
  private final PasswordEncoder passwordEncoder;

  private final EmailField emailEF = new EmailField("E-Mail");
  private final Button saveButton = new Button("Neuen Patienten hinzufügen");

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {
    add(new H1(AppConst.TITLE_REGISTER_PATIENT),
        buildForm());
  }

  private Div buildForm() {
    // Create form components
    TextField firstNameTF = new TextField("Vorname");
    TextField lastNameTF = new TextField("Nachname");

    DatePicker birthDateDP = new DatePicker("Geburtsdatum");
    birthDateDP.setValue(LocalDate.now());
    DatePicker.DatePickerI18n birthDateDPI18n = new DatePicker.DatePickerI18n();
    birthDateDPI18n.setWeek("Woche");
    birthDateDPI18n.setCalendar("Kalender");
    birthDateDPI18n.setClear("Löschen");
    birthDateDPI18n.setToday("Heute");
    birthDateDPI18n.setCancel("Abbrechen");
    birthDateDPI18n.setWeekdays(Arrays
        .asList("Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"));
    birthDateDPI18n.setWeekdaysShort(Arrays.asList("So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"));
    birthDateDPI18n.setMonthNames(
        Arrays.asList("Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August",
            "September", "Oktober", "November", "Dezember"));
    birthDateDP.setI18n(birthDateDPI18n);

    TextField streetTF = new TextField("Strasse");
    TextField streetNumberTF = new TextField("Hausnummer");
    TextField cityTF = new TextField("Stadt");
    TextField zipCodeTF = new TextField("Postleitzahl");
    TextField countryTF = new TextField("Land");
    TextField phoneTF = new TextField("Telefonnummer");

    PasswordField passwordPF = new PasswordField("Passwort");
    PasswordField passwordConfirmPF = new PasswordField("Passwort wiederholen");

    ComboBox<MedicalSpecialist> medicalSpecialistComboBox = new ComboBox<>("Therapeut");
    medicalSpecialistComboBox.setItemLabelGenerator(MedicalSpecialist::toStringForFormatCombobox);
    medicalSpecialistComboBox.setItems(medicalSpecialists);

    emailEF.setValueChangeMode(ValueChangeMode.EAGER);

    // Bind elements to business object
    Binder<Patient> binder = new Binder<>(Patient.class);
    binder.forField(firstNameTF)
        .asRequired("Vorname muss angegeben werden.")
        .bind(Patient::getFirstName, Patient::setFirstName);

    binder.forField(lastNameTF)
        .asRequired("Nachname muss angegeben werden.")
        .bind(Patient::getLastName, Patient::setLastName);

    binder.forField(birthDateDP)
        .asRequired("Geburtsdatum muss gesetzt sein.")
        .withValidator(date -> date.isBefore(LocalDate.now()),
            "Geburtsdatum muss in der Vergangenheit sein.")
        .bind(Patient::getBirthDate, Patient::setBirthDate);

    binder.forField(streetTF)
        .bind(Patient::getStreet, Patient::setStreet);

    binder.forField(streetNumberTF)
        .bind(Patient::getStreetNumber, Patient::setStreetNumber);

    binder.forField(cityTF)
        .bind(Patient::getCity, Patient::setCity);

    binder.forField(zipCodeTF)
        .bind(Patient::getZipCode, Patient::setZipCode);

    binder.forField(countryTF)
        .bind(Patient::getCountry, Patient::setCountry);

    binder.forField(phoneTF)
        .bind(Patient::getPhone, Patient::setPhone);

    binder.forField(emailEF)
        .asRequired("E-Mail muss angegeben werden.")
        .withValidator(new EmailValidator(
            "Bitte eine korrekte E-Mail angeben."))
        .bind(Patient::getEmail, Patient::setEmail);

    binder.forField(medicalSpecialistComboBox)
        .asRequired("Bitte einen Therapeuten auswählen.")
        .bind(Patient::getMedicalSpecialist, Patient::setMedicalSpecialist);

    binder.forField(passwordPF)
        .asRequired("Passwort muss gesetzt sein.")
        .withValidator(p -> p.length() >= 4,
            "Passwort muss mindestens 4 Zeichen lang sein.")
        .bind(p -> p.getPasswordHash(),
            (p, password) -> {
              p.setPasswordHash(passwordEncoder.encode(password));
            });

    Binder.Binding<Patient, String> secondPassword = binder.forField(passwordConfirmPF)
        .asRequired("Passwort muss gesetzt sein.")
        .withValidator(p -> p.length() >= 4,
            "Passwort muss mindestens 4 Zeichen lang sein.")
        .withValidator(p -> passwordPF.getValue().equals(passwordConfirmPF.getValue()),
            "Passwörter müssen übereinstimmen.")
        .bind(p -> p.getPasswordHash(),
            (p, password) -> {
              p.setPasswordHash(passwordEncoder.encode(password));
            });

    binder.readBean(new Patient());

    // Wrap components in layouts

    FormLayout formLayout = new FormLayout(firstNameTF, lastNameTF, birthDateDP, streetTF,
        streetNumberTF,
        cityTF, zipCodeTF, countryTF, phoneTF, emailEF, medicalSpecialistComboBox, passwordPF,
        passwordConfirmPF, saveButton);

    Div wrapperLayout = new Div(formLayout);

    passwordPF.addValueChangeListener(
        event -> secondPassword.validate());


    emailEF.addValueChangeListener(
        event -> {
          if (!emailEF.isInvalid()) {
            observer.emailIsUnique(event.getValue());
          }
        });

    // disable saveButton if form has validation errors
    binder.addStatusChangeListener(status -> {
          saveButton.setEnabled(!status.hasValidationErrors());
        }
    );

    saveButton.addClickListener(click -> {
      try {
        Patient newPatient = new Patient();
        binder.writeBean(newPatient);

        // save new patient
        observer.createNewPatient(newPatient);

        // load new empty patient to the form
        binder.readBean(new Patient());
      } catch (ValidationException e) {
        getLogger().warn("There are some validation errors.");
      }
    });

    formLayout.setResponsiveSteps(
        new FormLayout.ResponsiveStep("25em", 1),
        new FormLayout.ResponsiveStep("32em", 2),
        new FormLayout.ResponsiveStep("40em", 3));

    return wrapperLayout;
  }

  @Override
  public void setEMailIsUniqueError(boolean b) {
    emailEF.setInvalid(b);
    saveButton.setEnabled(!b);
    emailEF.setErrorMessage("E-Mail wird bereits verwendet.");
  }

  @Override
  public void setMedicalSpecialists(List<MedicalSpecialist> medicalSpecialists) {
    getLogger().info("Create list with medicalSpecialists");
    this.medicalSpecialists.addAll(medicalSpecialists);
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
