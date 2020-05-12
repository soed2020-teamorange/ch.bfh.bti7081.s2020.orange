package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
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
public class RegisterPatientPresenterImpl implements RegisterPatientPresenter,
    RegisterPatientView.Observer {

  private final RegisterPatientView registerPatientView;
  private final PatientService patientService;

  @Override
  public void onBeforeEnter() {
    registerPatientView.setObserver(this);
  }

  @Override
  public View getView() {
    return registerPatientView;
  }

  @Override
  public void saveNewPatient(Patient p) {
    patientService.savePatient(p);
  }

  @Override
  public List<Patient> getAllPatients() {
    return patientService.getAllPatients();
  }

}
