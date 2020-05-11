package ch.bfh.bti7081.s2020.orange.ui.views.showUserInfos;


import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
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
    if (currentUser.getUser().getRole().equals(Role.PATIENT)) {
      showUserView.setMedicalSpecialist("Dummy Therapist");
    } else if (currentUser.getUser().getRole().equals(Role.MEDICAL_SPECIALIST)) {
      // ToDo: Displays the same patient multiple times, Sorting changes all rows with same patient
      showUserView.setPatients(patientService.getAllPatients());
      /* Used for debugging
      for (Patient pa : patientService.getAllPatients()) {
        System.out.println(pa.getId());
      }*/
    }
  }

  @Override
  public View getView() {
    return showUserView;
  }

}
