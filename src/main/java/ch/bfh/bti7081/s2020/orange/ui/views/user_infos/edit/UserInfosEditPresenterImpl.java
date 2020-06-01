package ch.bfh.bti7081.s2020.orange.ui.views.user_infos.edit;


import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.backend.service.UserService;
import ch.bfh.bti7081.s2020.orange.ui.exceptions.UserAlreadyExistsException;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserInfosEditPresenterImpl implements UserInfosEditPresenter,
    UserInfosEditView.Observer {

  private final UserInfosEditView userInfosEditView;
  private final UserService userService;
  private final CurrentUser currentUser;

  @Override
  public void onBeforeEnter() {
    userInfosEditView.setObserver(this);
    userInfosEditView.setUser(currentUser.getUser());
    if (currentUser.getUser() instanceof Patient) {
      Patient patient = (Patient) currentUser.getUser();
      if (patient.getMedicalSpecialist() != null) {
        //List<MedicalSpecialist> medicalSpecialistList = new ArrayList<>();
        //medicalSpecialistList.add(patient.getMedicalSpecialist());
        userInfosEditView.setMedicalSpecialist(patient.getMedicalSpecialist());
      }
    } else if (currentUser.getUser() instanceof MedicalSpecialist) {
      MedicalSpecialist medicalSpecialist = (MedicalSpecialist) currentUser.getUser();
      if (medicalSpecialist.getPatients() != null) {
        userInfosEditView.setPatients(medicalSpecialist.getPatients());
      }
    }
  }

  @Override
  public void onSaveUser(User user) {
    userService.saveUser(user);
    userInfosEditView.showNotification("Angaben erfolgreich bearbeitet.");
  }

  @Override
  public void emailIsUnique(String email) throws UserAlreadyExistsException {
    try {
      userService.emailIsUnique(email);
      userInfosEditView.setEMailIsUniqueError(false);
    } catch (UserAlreadyExistsException e) {
      userInfosEditView.setEMailIsUniqueError(true);
    }
  }

  @Override
  public View getView() {
    return userInfosEditView;
  }
}
