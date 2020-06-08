package ch.bfh.bti7081.s2020.orange.ui.views.prescription.patientOverview;

import java.util.List;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Prescription;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;

public interface PrescriptionPatientView extends ViewWithObserver<PrescriptionPatientView.Observer> {

	public void setPatients(List<Patient> patients);
	
	public void setPrescriptions(List<Prescription> prescriptions);
	
	interface Observer {
		
		void patientChanged(Patient newPatient);
		
		void createPrescriptionToPatient(Patient patient);
		
		void editPrescription(Prescription prescription);
		
		void deletePrescription(Prescription prescription);
		
	}
}
