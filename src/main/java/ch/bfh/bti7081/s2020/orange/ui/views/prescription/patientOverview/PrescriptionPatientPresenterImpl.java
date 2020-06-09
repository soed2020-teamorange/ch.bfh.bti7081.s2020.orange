package ch.bfh.bti7081.s2020.orange.ui.views.prescription.patientOverview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.QueryParameters;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Prescription;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.backend.service.PrescriptionService;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrescriptionPatientPresenterImpl
		implements PrescriptionPatientsPresenter, PrescriptionPatientView.Observer {

	private final PrescriptionPatientView prescriptionPatientView;
	private final CurrentUser user;
	private final PatientService patientService;
	private final PrescriptionService prescriptionService;
	
	private Patient selectedPatient;

	@Override
	public View getView() {
		return prescriptionPatientView;
	}

	@Override
	public void onBefore() {
		prescriptionPatientView.setObserver(this);
		List<Patient> patients = new LinkedList<>();
		if (user.getUser() instanceof MedicalSpecialist) {
			((MedicalSpecialist) user.getUser()).getPatients().forEach( p -> {
				patients.add(patientService.getPatientById(p.getId()));
			});
		}
		prescriptionPatientView.setPatients(patients);
	}

	@Override
	public void patientChanged(Patient newPatient) {
		selectedPatient = newPatient;
		if(selectedPatient != null) {
			List<Prescription> prescriptions = prescriptionService.getByPatient(selectedPatient);
			prescriptionPatientView.setPrescriptions(prescriptions);
		}
	}

	@Override
	public void createPrescriptionToPatient(Patient patient) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(AppConst.PARAMETER_PRESCRIPTION_PATIENT, patient.getId().toString());
		UI.getCurrent().navigate(AppConst.PAGE_PRESCRIPTION_EDITOR, QueryParameters.simple(parameters));
	}

	@Override
	public void editPrescription(Prescription prescription) {
		Map<String, String> parameters = new HashMap<>();
		parameters.put(AppConst.PARAMETER_PRESCRIPTION_PRESCRIPTION, prescription.getId().toString());
		UI.getCurrent().navigate(AppConst.PAGE_PRESCRIPTION_EDITOR, QueryParameters.simple(parameters));
	}

	@Override
	public void deletePrescription(Prescription prescription) {
		prescriptionService.deletePrescription(prescription);
		prescriptionPatientView.setPrescriptions(prescriptionService.getByPatient(selectedPatient));
	}

}
