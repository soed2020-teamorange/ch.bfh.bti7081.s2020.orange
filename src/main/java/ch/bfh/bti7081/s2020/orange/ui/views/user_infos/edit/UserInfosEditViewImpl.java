package ch.bfh.bti7081.s2020.orange.ui.views.user_infos.edit;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.UIScope;
import java.time.LocalDate;
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
public class UserInfosEditViewImpl extends VerticalLayout implements UserInfosEditView, HasLogger {

  private final Binder<User> binder = new BeanValidationBinder<>(User.class);
  private final EmailField emailEF = new EmailField("E-Mail");
  private final PasswordEncoder passwordEncoder;
  private final Grid<Patient> patients = new Grid<>(Patient.class);
  private final Grid<MedicalSpecialist> medicalSpecialists = new Grid<>(MedicalSpecialist.class);
  private final H2 assignedUsersDesc = new H2();
  private final Button editInfosButton = new Button("Bearbeiten");
  private final Button saveButton = new Button("Speichern");

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {
    this.patients
        .setColumns("firstName", "lastName", "email", "phone", "birthDate", "street",
            "streetNumber",
            "zipCode",
            "city",
            "country");
    this.medicalSpecialists
        .setColumns("firstName", "lastName", "email", "phone");

    this.add(new H1(AppConst.TITLE_USER_INFOS_EDIT), this.editInfosButton, this.buildForm(),
        this.assignedUsersDesc);
  }

  private Div buildForm() {
    // Create form components
    final TextField firstNameTF = new TextField("Vorname");
    final TextField lastNameTF = new TextField("Nachname");
    firstNameTF.setEnabled(false);
    lastNameTF.setEnabled(false);

    final DatePicker birthDateDP = new DatePicker("Geburtsdatum");
    birthDateDP.setValue(LocalDate.now());
    final DatePicker.DatePickerI18n birthDateDPI18n = new DatePicker.DatePickerI18n();
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

    final TextField streetTF = new TextField("Strasse");
    final TextField streetNumberTF = new TextField("Hausnummer");
    final TextField cityTF = new TextField("Stadt");
    final TextField zipCodeTF = new TextField("Postleitzahl");
    final TextField countryTF = new TextField("Land");
    final TextField phoneTF = new TextField("Telefonnummer");
    final PasswordField passwordPF = new PasswordField("Neues Passwort");
    final PasswordField passwordConfirmPF = new PasswordField("Neues Passwort wiederholen");

    birthDateDP.setEnabled(false);
    this.emailEF.setEnabled(false);
    streetTF.setEnabled(false);
    streetNumberTF.setEnabled(false);
    cityTF.setEnabled(false);
    zipCodeTF.setEnabled(false);
    countryTF.setEnabled(false);
    phoneTF.setEnabled(false);
    passwordPF.setEnabled(false);
    passwordConfirmPF.setEnabled(false);

    this.emailEF.setValueChangeMode(ValueChangeMode.EAGER);
    this.saveButton.setEnabled(false);

    // Bind elements to business object
    this.binder.forField(firstNameTF)
        .asRequired("Vorname muss angegeben werden.")
        .bind(User::getFirstName, User::setFirstName);

    this.binder.forField(lastNameTF)
        .asRequired("Nachname muss angegeben werden.")
        .bind(User::getLastName, User::setLastName);

    this.binder.forField(birthDateDP)
        .asRequired("Geburtsdatum muss gesetzt sein.")
        .withValidator(date -> date.isBefore(LocalDate.now()),
            "Geburtsdatum muss in der Vergangenheit sein.")
        .bind(User::getBirthDate, User::setBirthDate);

    this.binder.forField(streetTF)
        .bind(User::getStreet, User::setStreet);

    this.binder.forField(streetNumberTF)
        .bind(User::getStreetNumber, User::setStreetNumber);

    this.binder.forField(cityTF)
        .bind(User::getCity, User::setCity);

    this.binder.forField(zipCodeTF)
        .bind(User::getZipCode, User::setZipCode);

    this.binder.forField(countryTF)
        .bind(User::getCountry, User::setCountry);

    this.binder.forField(phoneTF)
        .bind(User::getPhone, User::setPhone);

    this.binder.forField(this.emailEF)
        .asRequired("E-Mail muss angegeben werden.")
        .withValidator(new EmailValidator(
            "Bitte eine korrekte E-Mail angeben."))
        .bind(User::getEmail, User::setEmail);

    this.binder.forField(passwordPF)
        .withValidator(p -> p.length() >= 4,
            "Passwort muss mindestens 4 Zeichen lang sein.")
        .withValidator(p -> passwordPF.getValue().equals(passwordConfirmPF.getValue()),
            "Passwörter müssen übereinstimmen.")
        .bind(p -> "",
            (p, password) -> {
              p.setPasswordHash(this.passwordEncoder.encode(password));
            });

    final Binder.Binding<User, String> secondPassword = this.binder.forField(passwordConfirmPF)
        .withValidator(p -> p.length() >= 4,
            "Passwort muss mindestens 4 Zeichen lang sein.")
        .withValidator(p -> passwordPF.getValue().equals(passwordConfirmPF.getValue()),
            "Passwörter müssen übereinstimmen.")
        .bind(p -> "",
            (p, password) -> {
              p.setPasswordHash(this.passwordEncoder.encode(password));
            });

    // Wrap components in layouts
    final FormLayout formLayout = new FormLayout(firstNameTF, lastNameTF, birthDateDP, streetTF,
        streetNumberTF,
        cityTF, zipCodeTF, countryTF, phoneTF, this.emailEF, passwordPF, passwordConfirmPF,
        this.saveButton);

    final Div wrapperLayout = new Div(formLayout);

    passwordPF.addValueChangeListener(
        event -> secondPassword.validate());

    this.emailEF.addValueChangeListener(
        event -> {
          if (!this.emailEF.isInvalid()) {
            this.observer.emailIsUnique(event.getValue());
          }
        });

    // disable saveButton if form has validation errors
    this.binder.addStatusChangeListener(status -> {
          if (!this.editInfosButton.isEnabled()) {
            this.saveButton.setEnabled(!status.hasValidationErrors());
          }
        }
    );

    this.editInfosButton.addClickListener(click -> {
      firstNameTF.setEnabled(true);
      lastNameTF.setEnabled(true);
      birthDateDP.setEnabled(true);
      this.emailEF.setEnabled(true);
      streetTF.setEnabled(true);
      streetNumberTF.setEnabled(true);
      cityTF.setEnabled(true);
      zipCodeTF.setEnabled(true);
      countryTF.setEnabled(true);
      phoneTF.setEnabled(true);
      passwordPF.setEnabled(true);
      passwordConfirmPF.setEnabled(true);
      this.saveButton.setEnabled(true);
      this.editInfosButton.setEnabled(false);
    });

    this.saveButton.addClickListener(click -> {
      this.getLogger()
          .info("Successfully saved new data for user [" + this.binder.getBean().getEmail() + "]");
      this.observer.onSaveUser(this.binder.getBean());
      firstNameTF.setEnabled(false);
      lastNameTF.setEnabled(false);
      birthDateDP.setEnabled(false);
      this.emailEF.setEnabled(false);
      streetTF.setEnabled(false);
      streetNumberTF.setEnabled(false);
      cityTF.setEnabled(false);
      zipCodeTF.setEnabled(false);
      countryTF.setEnabled(false);
      phoneTF.setEnabled(false);
      passwordPF.setEnabled(false);
      passwordConfirmPF.setEnabled(false);
      this.saveButton.setEnabled(false);
      this.editInfosButton.setEnabled(true);
    });

    formLayout.setResponsiveSteps(
        new FormLayout.ResponsiveStep("25em", 1),
        new FormLayout.ResponsiveStep("32em", 2),
        new FormLayout.ResponsiveStep("40em", 3));

    return wrapperLayout;
  }

  @Override
  public void setEMailIsUniqueError(final boolean b) {
    this.emailEF.setInvalid(b);
    this.saveButton.setEnabled(!b);
    this.emailEF.setErrorMessage("E-Mail wird bereits verwendet.");
  }

  @Override
  public void setMedicalSpecialist(final MedicalSpecialist medicalSpecialist) {
    assignedUsersDesc.setText("zugewiesene medizinische Fachperson:");
    medicalSpecialists.setItems(medicalSpecialist);
    this.add(medicalSpecialists);
  }

  @Override
  public void setPatients(final List<Patient> patients) {
    assignedUsersDesc.setText("zugewiesene Patienten:");
    this.patients.setItems(patients);
    this.add(this.patients);
  }

  @Override
  public void setUser(final User user) {
    this.getLogger().info("Set user with e-mail [" + user.getEmail() + "]");
    this.binder.setBean(user);
  }

  @Override
  public <C> C getComponent(final Class<C> type) {
    return type.cast(this);
  }
}
