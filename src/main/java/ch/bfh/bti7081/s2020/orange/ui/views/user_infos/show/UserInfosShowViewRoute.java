package ch.bfh.bti7081.s2020.orange.ui.views.user_infos.show;

import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Route(value = AppConst.PAGE_USER_INFOS_SHOW, layout = MainView.class)
@PageTitle(AppConst.TITLE_USER_INFOS_SHOW)
@RequiredArgsConstructor
public class UserInfosShowViewRoute extends VerticalLayout implements BeforeEnterObserver, View {

  private final UserInfosShowPresenter userInfosShowPresenter;

  @PostConstruct
  public void init() {
    add(this.getComponent(Component.class));
  }

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    userInfosShowPresenter.onBeforeEnter();
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return userInfosShowPresenter.getView().getComponent(type);
  }
}
