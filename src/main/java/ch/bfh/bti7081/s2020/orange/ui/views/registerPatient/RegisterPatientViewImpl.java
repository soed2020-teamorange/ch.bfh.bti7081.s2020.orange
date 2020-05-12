package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.spring.annotation.UIScope;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.stereotype.Component;

@UIScope
@Component
public class RegisterPatientViewImpl extends VerticalLayout implements RegisterPatientView,
    HasLogger {

  private Grid<Patient> registeredPatientGrid = new Grid<>(Patient.class, false);
  private List<Patient> patients = new ArrayList<Patient>();

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {

    registeredPatientGrid.addColumn(Patient::getFirstName).setHeader("Vorname");
    registeredPatientGrid.addColumn(Patient::getLastName).setHeader("Nachname");

    add(new H1("Neuen Patienten registrieren"),
        buildForm(),
        registeredPatientGrid);
  }

  private Div buildForm() {

    Button loadButton = new Button("Daten laden");
    add(loadButton);

    // Create UI components
    TextField firstName = new TextField("Vorname");
    TextField lastName = new TextField("Nachname");
    EmailField emailField = new EmailField("E-Mail", "E-Mail");

    Button saveButton = new Button("Speichern");
    Div errorsLayout = new Div();

    // Configure UI components
    saveButton.setThemeName("primary");

    // Wrap components in layouts

    HorizontalLayout formLayout = new HorizontalLayout(firstName, lastName, saveButton);
    Div wrapperLayout = new Div(formLayout, errorsLayout);
    formLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
    wrapperLayout.setWidth("100%");

    Binder<Patient> binder = new Binder<>(Patient.class);
    binder.forField(firstName)
        .asRequired("Vorname muss angegeben werden.")
        .bind(Patient::getFirstName, Patient::setFirstName);

    binder.forField(lastName)
        .asRequired("Nachname muss angegeben werden.")
        .bind(Patient::getLastName, Patient::setLastName);

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

        observer.saveNewPatient(newPatient);
        updateGrid();
        binder.readBean(new Patient());

      } catch (ValidationException e) {
        //errorsLayout.add(new Html(e.getValidationErrors().stream()
        //      .map(res -> "<p>" + res.getErrorMessage() + "</p>")
        //    .collect(Collectors.joining("\n"))));
      }
    });

    loadButton.addClickListener(click -> {
      updateGrid();
    });
    return wrapperLayout;
  }

  private void updateGrid() {
    List<Patient> patients = observer.getAllPatients();
    getLogger().info(patients.toString());
    registeredPatientGrid.setItems(patients);
    registeredPatientGrid.getDataProvider().refreshAll();
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
