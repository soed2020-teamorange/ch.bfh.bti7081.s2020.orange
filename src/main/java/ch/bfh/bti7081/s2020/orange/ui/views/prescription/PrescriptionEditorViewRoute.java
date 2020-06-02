package ch.bfh.bti7081.s2020.orange.ui.views.prescription;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import lombok.RequiredArgsConstructor;

@Route(value = AppConst.PAGE_PRESCRIPTION_EDITOR, layout = MainView.class)
@PageTitle(AppConst.TITLE_PRESCRIPTION_EDITOR)
@RequiredArgsConstructor
public class PrescriptionEditorViewRoute extends VerticalLayout implements BeforeEnterObserver, View {

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
	
}
