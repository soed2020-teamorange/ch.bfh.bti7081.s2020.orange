package ch.bfh.bti7081.s2020.orange.ui.views.showUserInfos;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;
import java.util.List;

public interface ShowUserView extends ViewWithObserver<ShowUserView.Observer> {

  void setFirstName(String firstName);

  void setLastName(String lastName);

  void setMedicalSpecialist(MedicalSpecialist medicalSpecialist);

  void setPatients(List<Patient> patients);

  interface Observer {

  }
}
