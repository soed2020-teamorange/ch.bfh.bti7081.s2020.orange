package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;


import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import ch.bfh.bti7081.s2020.orange.backend.model.Person;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RegisterPatientPresenterImpl implements RegisterPatientPresenter, RegisterPatientView.Observer {

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
        patientService.createPatient(p);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

}
