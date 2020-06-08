package ch.bfh.bti7081.s2020.orange.ui.views.prescription;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.datepicker.DatePicker.DatePickerI18n;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.Query;
import com.vaadin.flow.spring.annotation.UIScope;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Dose;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Medicament;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Prescription;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@UIScope
@Component
@RequiredArgsConstructor
public class PrescriptionEditorViewImpl extends VerticalLayout implements PrescriptionEditorView, HasLogger {

	Binder<Prescription> binder;
	ComboBox<Medicament> medicamentCB;
	DatePicker validFromDP;
	DatePicker validToDP;
	DatePicker startingOnDP;
	Grid<Dose> dosages;
	Button addDoseBtn;
	Button delDoseBtn;
	Button savePrescriptionBtn;

	@Setter
	private Observer observer;

	@PostConstruct
	public void init() {
		add(new H1(AppConst.TITLE_PRESCRIPTION_EDITOR), buildForm());
	}

	private Div buildForm() {
		medicamentCB = new ComboBox<>("Medikament");
		medicamentCB.setItemLabelGenerator(Medicament::getName);

		validFromDP = new DatePicker("Gültig von");
		validFromDP.setValue(LocalDate.now());
		validFromDP.setI18n(createDPI18n());
		validToDP = new DatePicker("Gültig bis");
		validToDP.setValue(LocalDate.now());
		validToDP.setI18n(createDPI18n());
		startingOnDP = new DatePicker("Starte Einnahme ab");
		startingOnDP.setValue(LocalDate.now());
		startingOnDP.setI18n(createDPI18n());

		dosages = new Grid<>(Dose.class);
		dosages.removeAllColumns();
		dosages.addColumn(d -> d.getDosageMg()).setHeader("Dosierung (mg)");
		dosages.addColumn(d -> d.getConsumeTime()).setHeader("Uhrzeit Einnahme");
		dosages.setSelectionMode(SelectionMode.SINGLE);
		addDoseBtn = new Button("Einnahme hinzufügen");
		delDoseBtn = new Button("Einnahme entfernen");
		delDoseBtn.setEnabled(false);

		savePrescriptionBtn = new Button("Verschreibung speichern");

		binder = new Binder<>(Prescription.class);
		binder.forField(medicamentCB).asRequired("Medikament muss angegeben werden.").bind(Prescription::getMedicament,
				Prescription::setMedicament);
		binder.forField(validFromDP).asRequired("Gültigkeitsdatum muss angegeben werden.")
				.bind(Prescription::getValidFrom, Prescription::setValidUntil);
		binder.forField(validToDP).asRequired("Gültigkeitsdatum muss angegeben werden.")
				.bind(Prescription::getValidUntil, Prescription::setValidUntil);
		binder.forField(startingOnDP).asRequired("Start der Einnahme muss angegeben werden.")
				.bind(Prescription::getStartingOn, Prescription::setStartingOn);
		binder.setBean(new Prescription());
		setDosages(binder.getBean().getDosages());

		VerticalLayout dosageButtonsContainer = new VerticalLayout(addDoseBtn, delDoseBtn);
		addDoseBtn.setWidthFull();
		delDoseBtn.setWidthFull();

		FormLayout layout = new FormLayout(medicamentCB, validFromDP, validToDP, startingOnDP, dosages,
				dosageButtonsContainer, savePrescriptionBtn);

		binder.addStatusChangeListener(status -> {
			savePrescriptionBtn.setEnabled(!status.hasValidationErrors());
		});

		dosages.addSelectionListener(event -> {
			if (event.getAllSelectedItems().size() > 0) {
				delDoseBtn.setEnabled(true);
			} else {
				delDoseBtn.setEnabled(false);
			}
		});

		addDoseBtn.addClickListener(event -> {
			getLogger().debug("add Dosage");
			//TODO dosage createon dialog
			List<Dose> doses = getDosages();
			doses.add(new Dose(0.5, LocalDateTime.now()));
			dosages.setItems(doses);
		});

		delDoseBtn.addClickListener(event -> {
			Set<Dose> toDelete = dosages.getSelectedItems();
			if (toDelete.iterator().hasNext()) {
				List<Dose> doses = getDosages();
				doses.remove(toDelete.iterator().next());
				setDosages(doses);
			} else {
				getLogger().warn("Forbidden to call delete with no selected Dose!");
			}
		});

		savePrescriptionBtn.addClickListener(event -> {
			Prescription bean = binder.getBean();
			if (bean.getId() == null) {
				getLogger().info("Save new Prescription: " + bean.toString());
			} else {
				getLogger().info("Update Prescription with id [" + bean.getId() + "]");
			}
			bean.setDosages(getDosages());
			observer.savePrescription(binder.getBean());
			getLogger().debug("Successfully saved Prescription: " + bean.toString());
		});

		Div wrapper = new Div(layout);
		return wrapper;
	}

	private DatePickerI18n createDPI18n() {
		DatePicker.DatePickerI18n dPI18n = new DatePicker.DatePickerI18n();
		dPI18n.setWeek("Woche");
		dPI18n.setCalendar("Kalender");
		dPI18n.setClear("Löschen");
		dPI18n.setToday("Heute");
		dPI18n.setCancel("Abbrechen");
		dPI18n.setWeekdays(
				Arrays.asList("Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"));
		dPI18n.setWeekdaysShort(Arrays.asList("So", "Mo", "Di", "Mi", "Do", "Fr", "Sa"));
		dPI18n.setMonthNames(Arrays.asList("Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August",
				"September", "Oktober", "November", "Dezember"));
		return dPI18n;
	}

	private void setDosages(List<Dose> doses) {
		if(doses == null) {
			doses = new ArrayList<>();
		}
		dosages.setItems(doses);
	}

	private List<Dose> getDosages() {
		return dosages.getDataProvider().fetch(new Query<>()).collect(Collectors.toList());
	}

	@Override
	public <C> C getComponent(Class<C> type) {
		return type.cast(this);
	}

	@Override
	public void setPrescription(Prescription prescription) {
		getLogger().info("Display Prescription with id [" + prescription.getId() + "]");
		binder.setBean(prescription);
		setDosages(prescription.getDosages());
	}

	@Override
	public void setMedicaments(List<Medicament> medicaments) {
		medicamentCB.setItems(medicaments);
	}
	
}
