package ch.bfh.bti7081.s2020.orange.ui.views.viewPatient;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import javax.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class ViewPatientViewImpl extends VerticalLayout implements
    ViewPatientView,
    HasLogger {

  Grid<Patient> grid = new Grid<>(Patient.class);
  Label firstName = new Label();
  Label lastName = new Label();

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {
    firstName.setText("Vorname");
    lastName.setText("Nachname");

    add(firstName);
    add(lastName);

    //ToDo: List patients during init method
    //grid.setItems(observer.getAllPatients());

    grid.removeColumnByKey("id");

    // The Grid<>(Patient.class) sorts the properties and in order to
    // reorder the properties we use the 'setColumns' method.
    grid.setColumns("firstName", "lastName", "medicalSpecialist");

    add(grid);
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }

}
