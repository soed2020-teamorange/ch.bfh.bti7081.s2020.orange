package ch.bfh.bti7081.s2020.orange.ui.views.user_infos.edit;

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

@Route(value = AppConst.PAGE_USER_INFOS_EDIT, layout = MainView.class)
@PageTitle(AppConst.TITLE_USER_INFOS_EDIT)
@RequiredArgsConstructor
@PreserveOnRefresh
public class UserInfosEditViewRoute extends VerticalLayout implements BeforeEnterObserver, View {

  private final UserInfosEditPresenter userInfosEditPresenter;

  @PostConstruct
  public void init() {
    add(this.getComponent(Component.class));
  }

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    userInfosEditPresenter.onBeforeEnter();
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return userInfosEditPresenter.getView().getComponent(type);
  }
}