package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import java.util.List;

public interface RegisterPatientView extends ViewWithObserver<RegisterPatientView.Observer> {

  void setMedicalSpecialists(List<MedicalSpecialist> medicalSpecialists);

  interface Observer {

    void createNewPatient(Patient p);

    boolean emailIsUnique(String email);

  }
}
