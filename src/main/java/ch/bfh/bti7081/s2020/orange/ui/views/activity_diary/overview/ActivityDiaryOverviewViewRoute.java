package ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.overview;

import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;

@Route(value = AppConst.PAGE_ACTIVITY_DIARY_OVERVIEW, layout = MainView.class)
@PageTitle(AppConst.TITLE_ACTIVITY_DIARY)
@RequiredArgsConstructor
@PreserveOnRefresh
@Secured(Role.PATIENT)
public class ActivityDiaryOverviewViewRoute extends VerticalLayout implements BeforeEnterObserver,
    View {

  private final ActivityDiaryOverviewPresenter presenter;

  @PostConstruct
  public void init() {
    removeAll();
    add(this.getComponent(Component.class));
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return presenter.getView().getComponent(type);
  }

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    presenter.onBeforeEnter();
  }
}
