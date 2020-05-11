package ch.bfh.bti7081.s2020.orange.ui.views.registerPatient;

import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Route(value = AppConst.PAGE_REGISTERPATIENT, layout = MainView.class)
@RouteAlias(value = AppConst.PAGE_REGISTERPATIENT, layout = MainView.class)
@PageTitle(AppConst.TITLE_REGISTERPATIENT)
@RequiredArgsConstructor
public class RegisterPatientViewRoute extends VerticalLayout implements BeforeEnterObserver, View {

  private final RegisterPatientPresenter registerPatientPresenter;

  @PostConstruct
  public void init() {
    add(this.getComponent(Component.class));
  }

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    registerPatientPresenter.onBeforeEnter();
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return registerPatientPresenter.getView().getComponent(type);
  }
}
