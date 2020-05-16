package ch.bfh.bti7081.s2020.orange.ui.views.showUserInfos;


import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ShowUserPresenterImpl implements ShowUserPresenter, ShowUserView.Observer {

  private final ShowUserView showUserView;
  private final PatientService patientService;
  private final MedicalSpecialistService medicalSpecialistService;
  private final CurrentUser currentUser;

  @Override
  public void onBeforeEnter() {
    showUserView.setObserver(this);
    showUserView.setFirstName(currentUser.getUser().getFirstName());
    showUserView.setLastName(currentUser.getUser().getLastName());
    if (currentUser.getUser() instanceof Patient) {
      Patient patient = (Patient) currentUser.getUser();
      if (patient.getMedicalSpecialist() != null) {
        showUserView.setMedicalSpecialist(patient.getMedicalSpecialist());
      }
    } else if (currentUser.getUser() instanceof MedicalSpecialist) {
      List<Patient> patients = patientService
          .getPatientsByMedicalSpecialist((MedicalSpecialist) currentUser.getUser());
      showUserView.setPatients(patients);
    }
  }

  @Override
  public View getView() {
    return showUserView;
  }

}
