package ch.bfh.bti7081.s2020.orange.ui.views.prescription.patientOverview;

import javax.annotation.PostConstruct;

import org.springframework.security.access.annotation.Secured;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;

@Route(value = AppConst.PAGE_PRESCRIPTION_OVERVIEW, layout = MainView.class)
@PageTitle(AppConst.TITLE_PRESCRIPTION_OVERVIEW)
@RequiredArgsConstructor
@Secured(Role.MEDICAL_SPECIALIST)
public class PrescriptionPatientViewRoute extends VerticalLayout implements BeforeEnterObserver, View {

	private final PrescriptionPatientsPresenter prescriptionPatientsPresenter;
	
	@PostConstruct
	public void init() {
		add(this.getComponent(Component.class));
	}

	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		prescriptionPatientsPresenter.onBefore();
		
	}

	@Override
	public <C> C getComponent(Class<C> type) {
		return prescriptionPatientsPresenter.getView().getComponent(type);
	}
}
