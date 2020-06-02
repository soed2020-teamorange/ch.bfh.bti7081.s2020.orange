package ch.bfh.bti7081.s2020.orange.ui.views.prescription;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Prescription;
import ch.bfh.bti7081.s2020.orange.backend.service.MedicamentService;
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
		prescriptionService.savePrescription(p);
	}

	@Override
	public void removeExistingPrescription(Prescription p) {
		prescriptionService.deletePrescription(p);
		
	}

}
