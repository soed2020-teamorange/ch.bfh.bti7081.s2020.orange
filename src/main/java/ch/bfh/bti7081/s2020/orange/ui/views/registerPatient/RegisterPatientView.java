package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.exceptions.UserAlreadyExistsException;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import com.vaadin.flow.component.html.Div;

import java.util.List;

public interface RegisterPatientView extends ViewWithObserver<RegisterPatientView.Observer> {

  void setMedicalSpecialists(List<MedicalSpecialist> medicalSpecialists);

  void setEMailIsUniqueError(boolean b);

  interface Observer {

    void createNewPatient(Patient p);

    void emailIsUnique(String email) throws UserAlreadyExistsException;



  }
}
