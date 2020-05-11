package ch.bfh.bti7081.s2020.orange.ui.views.editUserInfos;

import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;

@Route(value = AppConst.PAGE_EDITUSERINFOS, layout = MainView.class)
@RouteAlias(value = AppConst.PAGE_EDITUSERINFOS, layout = MainView.class)
@PageTitle(AppConst.TITLE_EDITUSERINFOS)
@RequiredArgsConstructor
@PreserveOnRefresh
public class EditUserInfosViewRoute extends VerticalLayout implements BeforeEnterObserver, View {

  private final EditUserInfosPresenter editUserInfosPresenter;

  @PostConstruct
  public void init() {
    add(this.getComponent(Component.class));
  }

  @Override
  public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
    editUserInfosPresenter.onBeforeEnter();
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return editUserInfosPresenter.getView().getComponent(type);
  }
}
