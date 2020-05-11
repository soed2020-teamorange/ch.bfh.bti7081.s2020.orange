package ch.bfh.bti7081.s2020.orange.ui.views.viewMedicalSpecialist;

import ch.bfh.bti7081.s2020.orange.backend.model.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Setter;

public class ViewMedicalSpecialistViewImpl extends VerticalLayout implements
    ViewMedicalSpecialistView,
    HasLogger {

  Label firstName = new Label();
  Label lastName = new Label();

  @Setter
  private ViewMedicalSpecialistView.Observer observer;

  @PostConstruct
  public void init() {
    firstName.setText("Vorname");
    lastName.setText("Nachname");

    add(firstName);
    add(lastName);

    List<MedicalSpecialist> l = observer.getAllMedicalSpecialists();
    l.forEach(ms -> getLogger().info(l.toString()));
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
