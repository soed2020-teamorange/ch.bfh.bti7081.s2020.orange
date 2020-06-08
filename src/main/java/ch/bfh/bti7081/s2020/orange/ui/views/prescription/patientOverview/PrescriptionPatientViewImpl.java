package ch.bfh.bti7081.s2020.orange.ui.views.prescription.patientOverview;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.spring.annotation.UIScope;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Prescription;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@UIScope
@Component
@RequiredArgsConstructor
public class PrescriptionPatientViewImpl extends VerticalLayout implements PrescriptionPatientView, HasLogger {

	ListBox<Patient> patients;
	ListBox<Prescription> prescriptions;
	Button addPrescriptionBtn;
	Button editPrescriptionBtn;
	Button deletePrescriptionBtn;

	@Setter
	private Observer observer;

	@PostConstruct
	public void init() {
		setWidthFull();
		add(new H1(AppConst.TITLE_PRESCRIPTION_OVERVIEW), buildForm());
	}

	private Div buildForm() {
		Label patientLbl = new Label("Patient");
		patients = new ListBox<>();
		patients.setRenderer(new TextRenderer<>(c -> c.getFirstName() + " " + c.getLastName()));
		patients.addValueChangeListener(event -> {
			addPrescriptionBtn.setEnabled(true);
			observer.patientChanged(event.getValue());
		});
		patients.setWidthFull();

		Label prescriptionLbl = new Label("Verschreibung");
		prescriptions = new ListBox<>();
		prescriptions.setRenderer(new TextRenderer<>(c -> c.getMedicament().getName()));
		prescriptions.addValueChangeListener(event -> {
			boolean enabled = (event.getValue() != null);
			editPrescriptionBtn.setEnabled(enabled);
			deletePrescriptionBtn.setEnabled(enabled);
		});

		addPrescriptionBtn = new Button("Verschreibung erstellen");
		addPrescriptionBtn.addClickListener(event -> {
			Patient selected = patients.getValue();
			if(selected == null) {
				getLogger().error("Cannot add Prescription to empty Patient!");
				return;
			}
			observer.createPrescriptionToPatient(selected);
		});

		editPrescriptionBtn = new Button("Verschreibung bearbeiten");
		editPrescriptionBtn.addClickListener(event -> {
			Prescription selected = prescriptions.getValue();
			if(selected == null) {
				getLogger().error("Cannot edit empty Prescription!");
				return;
			}
			observer.editPrescription(selected);
		});

		deletePrescriptionBtn = new Button("Verschreibung löschen");
		deletePrescriptionBtn.addClickListener(event -> {
			Prescription selected = prescriptions.getValue();
			if(selected == null) {
				getLogger().error("Cannot delete empty Prescription!");
				return;
			}
			observer.deletePrescription(selected);
		});

		HorizontalLayout buttons = new HorizontalLayout(addPrescriptionBtn, editPrescriptionBtn, deletePrescriptionBtn);
		buttons.setWidthFull();

		FormLayout layout = new FormLayout(patientLbl, patients, prescriptionLbl, prescriptions, buttons);
		layout.setWidthFull();
		Div wrapper = new Div(layout);
		wrapper.setWidthFull();
		return wrapper;
	}

	@Override
	public <C> C getComponent(Class<C> type) {
		return type.cast(this);
	}

	@Override
	public void setPatients(List<Patient> patients) {
		this.patients.removeAll();
		this.patients.setItems(patients);
	}
	
	@Override
	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions.removeAll();
		this.prescriptions.setItems(prescriptions);
	}

}
