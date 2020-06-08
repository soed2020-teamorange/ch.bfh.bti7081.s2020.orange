package ch.bfh.bti7081.s2020.orange.ui.views.prescription;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;

@Route(value = AppConst.PAGE_PRESCRIPTION_EDITOR, layout = MainView.class)
@PageTitle(AppConst.TITLE_PRESCRIPTION_EDITOR)
@RequiredArgsConstructor
@Secured(Role.MEDICAL_SPECIALIST)
public class PrescriptionEditorViewRoute extends VerticalLayout implements BeforeEnterObserver, View, HasUrlParameter<String>, HasLogger {

	private final PrescriptionEditorPresenter prescriptionEditorPresenter;
	
	@PostConstruct
	public void init() {
		add(this.getComponent(Component.class));
	}
	
	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		prescriptionEditorPresenter.onBeforeEnter();
	}

	@Override
	public <C> C getComponent(Class<C> type) {
		return prescriptionEditorPresenter.getView().getComponent(type);
	}

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		Map<String, List<String>> parameters = event.getLocation().getQueryParameters().getParameters();
		List<String> prescription = parameters.get(AppConst.PARAMETER_PRESCRIPTION_PRESCRIPTION);
		if(prescription != null && prescription.size() == 1) {
			Long id = Long.parseLong(prescription.get(0));
			getLogger().info("Editing prescription with ID [" + id + "]");
			prescriptionEditorPresenter.onBeforeEnter();
			prescriptionEditorPresenter.setPrescription(id);
			return;
		}
		List<String> patient = parameters.get(AppConst.PARAMETER_PRESCRIPTION_PATIENT);
		if(patient != null && patient.size() == 1) {
			Long id = Long.parseLong(patient.get(0));
			getLogger().info("Adding prescription to patient with ID [" + id + "]");
			prescriptionEditorPresenter.setPatient(id);
			return;
		}
		
		illegalParameters();
		
	}
	
	private void illegalParameters() {
		getLogger().warn("Illegal parameters passed to " + AppConst.PAGE_PRESCRIPTION_EDITOR + " - trying to redirecting");
		getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_PRESCRIPTION_OVERVIEW));
	}
	
}
