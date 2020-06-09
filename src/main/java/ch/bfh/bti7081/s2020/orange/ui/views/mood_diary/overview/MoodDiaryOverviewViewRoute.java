package ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview;

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

@Route(value = AppConst.PAGE_MOOD_DIARY_OVERVIEW, layout = MainView.class)
@PageTitle(AppConst.TITLE_MOOD_DIARY)
@RequiredArgsConstructor
@PreserveOnRefresh
@Secured(Role.PATIENT)
public class MoodDiaryOverviewViewRoute extends VerticalLayout implements BeforeEnterObserver,
    View {

  private final MoodDiaryOverviewPresenter presenter;

  @PostConstruct
  public void init() {
    this.removeAll();
    this.add(getComponent(Component.class));
  }

  @Override
  public <C> C getComponent(final Class<C> type) {
    return this.presenter.getView().getComponent(type);
  }

  @Override
  public void beforeEnter(final BeforeEnterEvent beforeEnterEvent) {
    this.presenter.onBeforeEnter();
  }
}
