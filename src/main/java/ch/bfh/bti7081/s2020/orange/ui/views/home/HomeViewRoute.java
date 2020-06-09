package ch.bfh.bti7081.s2020.orange.ui.views.home;

import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Route(value = AppConst.PAGE_HOME, layout = MainView.class)
@RouteAlias(value = AppConst.PAGE_ROOT, layout = MainView.class)
@PageTitle(AppConst.TITLE_HOME)
@RequiredArgsConstructor
public class HomeViewRoute extends VerticalLayout implements View {

  private final HomePresenter homePresenter;

  @PostConstruct
  public void init() {
    removeAll();

    add(this.getComponent(Component.class));
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return homePresenter.getView().getComponent(type);
  }
}
