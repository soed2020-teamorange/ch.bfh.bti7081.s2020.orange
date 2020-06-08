package ch.bfh.bti7081.s2020.orange.ui.views.landingPage;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.service.HomeService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import ch.bfh.bti7081.s2020.orange.ui.views.home.HomeView;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LandingPagePresenterImpl implements LandingPagePresenter, LandingPageView.Observer {
	
	private final LandingPageView landingPageView;
	private final CurrentUser currentUser;
	
	@Override
	public void onBeforeEnter() {
		landingPageView.setObserver(this);

	    getView().getComponent(VerticalLayout.class).add();
	  }
	
	@Override
	public void onCalculate(long base, long power) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public View getView() {
		return landingPageView;
	}

}
