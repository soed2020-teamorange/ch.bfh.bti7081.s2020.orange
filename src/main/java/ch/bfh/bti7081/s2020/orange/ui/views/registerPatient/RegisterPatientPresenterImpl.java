package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicalSpecialistService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.backend.service.UserService;
import ch.bfh.bti7081.s2020.orange.ui.exceptions.UserAlreadyExistsException;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import java.util.List;

import com.vaadin.flow.component.textfield.EmailField;
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
    registerPatientView.setObserver(this);
    registerPatientView.setMedicalSpecialists(medicalSpecialistService.getAllMedicalSpecialist());
  }

  @Override
  public void createNewPatient(Patient p) {
    getLogger().info("Registered new patient with email {}", p.getEmail());
    patientService.savePatient(p);
  }

  @Override
  public void emailIsUnique(String email) throws UserAlreadyExistsException {
    try {
      userService.emailIsUnique(email);
      registerPatientView.setEMailIsUniqueError(false);
    } catch (UserAlreadyExistsException e) {
      registerPatientView.setEMailIsUniqueError(true);
    }
  }

  @Override
  public View getView() {
    return registerPatientView;
  }
}
