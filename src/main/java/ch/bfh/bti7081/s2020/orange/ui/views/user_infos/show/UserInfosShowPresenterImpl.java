package ch.bfh.bti7081.s2020.orange.ui.views.user_infos.show;


import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserInfosShowPresenterImpl implements UserInfosShowPresenter,
    UserInfosShowView.Observer {

  private final UserInfosShowView userInfosShowView;
  private final CurrentUser currentUser;

  @Override
  public void onBeforeEnter() {
    userInfosShowView.setObserver(this);
    userInfosShowView.setFirstName(currentUser.getUser().getFirstName());
    userInfosShowView.setLastName(currentUser.getUser().getLastName());
    if (currentUser.getUser() instanceof Patient) {
      Patient patient = (Patient) currentUser.getUser();
      if (patient.getMedicalSpecialist() != null) {
        userInfosShowView.setMedicalSpecialist(patient.getMedicalSpecialist());
      }
    } else if (currentUser.getUser() instanceof MedicalSpecialist) {
      MedicalSpecialist medicalSpecialist = (MedicalSpecialist) currentUser.getUser();
      if (medicalSpecialist.getPatients() != null) {
        userInfosShowView.setPatients(medicalSpecialist.getPatients());
      }
    }
  }

  @Override
  public View getView() {
    return userInfosShowView;
  }

}
