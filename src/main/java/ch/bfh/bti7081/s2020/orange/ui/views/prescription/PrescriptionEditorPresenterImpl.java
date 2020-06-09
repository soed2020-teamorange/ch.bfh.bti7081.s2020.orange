package ch.bfh.bti7081.s2020.orange.ui.views.prescription;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Prescription;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicamentService;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import ch.bfh.bti7081.s2020.orange.backend.service.PrescriptionService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PrescriptionEditorPresenterImpl implements PrescriptionEditorPresenter, PrescriptionEditorView.Observer {

	private final PrescriptionEditorView prescriptionEditorView;
	private final PrescriptionService prescriptionService;
	private final MedicamentService medicamentService;
	private final PatientService patientService;
	
	Patient patient = null;

	@Override
	public View getView() {
		return prescriptionEditorView;
	}
	
	@Override
	public void onBeforeEnter() {
		prescriptionEditorView.setObserver(this);
		prescriptionEditorView.setMedicaments(medicamentService.getAllMedicaments());
	}

	@Override
	public void savePrescription(Prescription p) {
		p.setPatient(patient);
		prescriptionService.savePrescription(p);
	}

	@Override
	public void setPatient(Long id) {
		this.patient = patientService.getPatientById(id);
	}

	@Override
	public void setPrescription(Long id) {
		Prescription prescription = prescriptionService.getById(id);
		patient = patientService.getPatientById(prescription.getPatient().getId());
		prescription.setPatient(null); //avoid problems about lazy loading
		prescriptionEditorView.setPrescription(prescription);
	}

}
