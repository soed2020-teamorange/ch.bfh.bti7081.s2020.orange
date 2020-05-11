package ch.bfh.bti7081.s2020.orange.ui.views.viewMedicalSpecialist;

import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.RequiredArgsConstructor;

@Route(value = AppConst.PAGE_VIEWMEDICALSPECIALIST, layout = MainView.class)
@PageTitle(AppConst.TITLE_VIEWMEDICALSPECIALIST)
@RequiredArgsConstructor
public class ViewMedicalSpecialistViewRoute extends VerticalLayout implements BeforeEnterObserver,
    View {

  private final ViewMedicalSpecialistPresenter viewMedicalSpecialistPresenter;

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    viewMedicalSpecialistPresenter.onBeforeEnter();
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return viewMedicalSpecialistPresenter.getView().getComponent(type);
  }
}
