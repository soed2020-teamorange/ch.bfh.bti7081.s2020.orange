package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;


import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import com.vaadin.flow.spring.annotation.UIScope;

import java.util.List;

public interface RegisterPatientView extends ViewWithObserver<RegisterPatientView.Observer> {

  interface Observer {

    void saveNewPatient(Patient p);

    List<Patient> getAllPatients();
  }
}
