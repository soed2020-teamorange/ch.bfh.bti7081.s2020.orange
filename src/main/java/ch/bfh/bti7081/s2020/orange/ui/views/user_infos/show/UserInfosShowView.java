package ch.bfh.bti7081.s2020.orange.ui.views.user_infos.show;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import java.util.List;

public interface UserInfosShowView extends View {

  void setFirstName(String firstName);

  void setLastName(String lastName);

  void setMedicalSpecialist(MedicalSpecialist medicalSpecialist);

  void setPatients(List<Patient> patients);

}
