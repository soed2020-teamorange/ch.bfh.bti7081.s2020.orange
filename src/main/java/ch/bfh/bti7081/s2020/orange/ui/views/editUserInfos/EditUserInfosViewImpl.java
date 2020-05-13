package ch.bfh.bti7081.s2020.orange.ui.views.editUserInfos;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import java.util.Locale;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@UIScope
@Component
@RequiredArgsConstructor
public class EditUserInfosViewImpl extends VerticalLayout implements EditUserInfosView, HasLogger {

  private final CurrentUser currentUser;
  private Binder<User> binder = new BeanValidationBinder<>(User.class);

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {

    add(new H1(AppConst.TITLE_EDITUSERINFOS),
        buildForm());

    Button submit = new Button("Speichern");
    submit.addClickListener(this::onSaveNewDataButtonClick);

    add(submit);
  }

  private Div buildForm() {
    // Create UI components
    TextField firstName = new TextField("Vorname");
    TextField lastName = new TextField("Nachname");
    DatePicker birthDate = new DatePicker("Geburtsdatum");
    birthDate.setLocale(Locale.GERMAN);
    TextField street = new TextField("Strasse");
    TextField streetNumber = new TextField("Hausnummer");
    TextField city = new TextField("Stadt");
    TextField zipCode = new TextField("Postleitzahl");
    TextField country = new TextField("Land");
    TextField phone = new TextField("Telefonnummer");
    EmailField email = new EmailField("E-Mail");

    Div errorsLayout = new Div();

    // Wrap components in layouts
    FormLayout formLayout = new FormLayout(firstName, lastName, birthDate, street, streetNumber,
        city, zipCode, country, phone, email);
    Div wrapperLayout = new Div(formLayout, errorsLayout);
    wrapperLayout.setWidth("100%");

    binder.forField(firstName).bind(User::getFirstName, User::setFirstName);
    binder.forField(lastName).bind(User::getLastName, User::setLastName);
    binder.forField(birthDate).bind(User::getBirthDate, User::setBirthDate);
    binder.forField(street).bind(User::getStreet, User::setStreet);
    binder.forField(streetNumber).bind(User::getStreetNumber, User::setStreetNumber);
    binder.forField(city).bind(User::getCity, User::setCity);
    binder.forField(zipCode).bind(User::getZipCode, User::setZipCode);
    binder.forField(country).bind(User::getCountry, User::setCountry);
    binder.forField(phone).bind(User::getPhone, User::setPhone);
    binder.forField(email).bind(User::getEmail, User::setEmail);

    // TODO: Eingaben validieren

    binder.setBean(currentUser.getUser());

    formLayout.setResponsiveSteps(
        new FormLayout.ResponsiveStep("25em", 1),
        new FormLayout.ResponsiveStep("32em", 2),
        new FormLayout.ResponsiveStep("40em", 3));

    return wrapperLayout;
  }

  private void onSaveNewDataButtonClick(ClickEvent<Button> buttonClickEvent) {
    getLogger().info("Successfully saved new data for user [" + binder.getBean().getEmail() + "]");
    observer.onSaveUser(binder.getBean());
  }

  @Override
  public void setUser(User user) {
    getLogger().info("Set user with e-mail [" + user.getEmail() + "]");
    binder.setBean(user);
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
