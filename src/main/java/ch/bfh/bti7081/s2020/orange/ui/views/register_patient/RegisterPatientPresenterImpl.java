package ch.bfh.bti7081.s2020.orange.ui.views.register_patient;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.backend.service.UserService;
import ch.bfh.bti7081.s2020.orange.ui.exceptions.UserAlreadyExistsException;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RegisterPatientPresenterImpl implements RegisterPatientPresenter,
    RegisterPatientView.Observer, HasLogger {

  private final RegisterPatientView registerPatientView;
  private final UserService userService;
  private final PatientService patientService;
  private final MedicalSpecialistService medicalSpecialistService;

  @Override
  public void onBeforeEnter() {
    this.registerPatientView.setObserver(this);
    this.registerPatientView
        .setMedicalSpecialists(this.medicalSpecialistService.getAllMedicalSpecialist());
  }

  @Override
  public void createNewPatient(final Patient p) {
    this.getLogger().info("Registered new patient with email {}", p.getEmail());
    this.patientService.updatePatient(p);
  }

  @Override
  public void emailIsUnique(final String email) throws UserAlreadyExistsException {
    try {
      this.userService.emailIsUnique(email);
      this.registerPatientView.setEMailIsUniqueError(false);
    } catch (final UserAlreadyExistsException e) {
      this.registerPatientView.setEMailIsUniqueError(true);
    }
  }

  @Override
  public View getView() {
    return this.registerPatientView;
  }
}
