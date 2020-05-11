package ch.bfh.bti7081.s2020.orange.ui.views.viewPatient;


import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ViewPatientPresenterImpl implements ViewPatientPresenter, ViewPatientView.Observer {

  private final ViewPatientView viewPatientView;
  private final PatientService patientService;

  @Override
  public void onBeforeEnter() {
    viewPatientView.setObserver(this);
  }

  @Override
  public View getView() {
    return viewPatientView;
  }

  @Override
  public List<Patient> getAllPatients() {
    return patientService.getAllPatients();
  }

}
