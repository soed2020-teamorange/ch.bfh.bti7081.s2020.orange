package ch.bfh.bti7081.s2020.orange.ui.views.prescription;

import java.util.List;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Medicament;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Prescription;
import ch.bfh.bti7081.s2020.orange.ui.utils.ViewWithObserver;

public interface PrescriptionEditorView extends ViewWithObserver<PrescriptionEditorView.Observer> {

	void setPrescription(Prescription prescription);
	
	void setMedicaments(List<Medicament> medicaments);
	
	interface Observer {
		
		void savePrescription(Prescription p);
		
		void removeExistingPrescription(Prescription p);
		
	}
}
