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
    this.userInfosEditView.setObserver(this);
    this.userInfosEditView.setUser(this.currentUser.getUser());
    if (this.currentUser.getUser() instanceof Patient) {
      final Patient patient = (Patient) this.currentUser.getUser();
      if (patient.getMedicalSpecialist() != null) {
        //List<MedicalSpecialist> medicalSpecialistList = new ArrayList<>();
        //medicalSpecialistList.add(patient.getMedicalSpecialist());
        this.userInfosEditView.setMedicalSpecialist(patient.getMedicalSpecialist());
      }
    } else if (this.currentUser.getUser() instanceof MedicalSpecialist) {
      final MedicalSpecialist medicalSpecialist = (MedicalSpecialist) this.currentUser.getUser();
      if (medicalSpecialist.getPatients() != null) {
        this.userInfosEditView.setPatients(medicalSpecialist.getPatients());
      }
    }
  }

  @Override
  public void onSaveUser(final User user) {
    this.userService.saveUser(user);
    this.userInfosEditView.showNotification("Angaben erfolgreich bearbeitet.");
  }

  @Override
  public void emailIsUnique(final String email) throws UserAlreadyExistsException {
    try {
      this.userService.emailIsUnique(email);
      this.userInfosEditView.setEMailIsUniqueError(false);
    } catch (final UserAlreadyExistsException e) {
      this.userInfosEditView.setEMailIsUniqueError(true);
    }
  }

  @Override
  public View getView() {
    return this.userInfosEditView;
  }
}
