package ch.bfh.bti7081.s2020.orange.ui.views.viewPatient;

import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Setter;

public class ViewPatientViewImpl extends VerticalLayout implements
    ViewPatientView,
    HasLogger {

  Label firstName = new Label();
  Label lastName = new Label();

  @Setter
  private ViewPatientView.Observer observer;

  @PostConstruct
  public void init() {
    firstName.setText("Vorname");
    lastName.setText("Nachname");

    add(firstName);
    add(lastName);

    List<Patient> l = observer.getAllPatients();
    l.forEach(p -> getLogger().info(l.toString()));
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }

}
