package ch.bfh.bti7081.s2020.orange.ui.views.chat;

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

@Route(value = AppConst.PAGE_CHAT, layout = MainView.class)
@PageTitle(AppConst.TITLE_CHAT)
@RequiredArgsConstructor
public class ChatViewRoute extends VerticalLayout implements View, BeforeEnterObserver {

  private final ChatPresenter presenter;

  @PostConstruct
  public void init() {
    this.removeAll();
    this.add(getComponent(Component.class));
    this.addClassName("chat-view-container");
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
