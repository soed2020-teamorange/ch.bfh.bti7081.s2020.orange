package ch.bfh.bti7081.s2020.orange.ui.views.landingPage;

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
import ch.bfh.bti7081.s2020.orange.ui.views.home.HomePresenter;
import lombok.RequiredArgsConstructor;

@Route(value = AppConst.PAGE_LANDING, layout = MainView.class)
@PageTitle(AppConst.TITLE_LANDING)
@RequiredArgsConstructor
public class LandingPageViewRoute extends VerticalLayout implements BeforeEnterObserver, View {
	
	  private final LandingPagePresenter landingPagePresenter;

	  @PostConstruct
	  public void init() {
	    removeAll();
	    add(this.getComponent(Component.class));
	  }

	  @Override
	  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		  landingPagePresenter.onBeforeEnter();
	  }

	  @Override
	  public <C> C getComponent(Class<C> type) {
	  return landingPagePresenter.getView().getComponent(type);
	  }
}