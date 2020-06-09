package ch.bfh.bti7081.s2020.orange.ui.views.errors;

import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouteNotFoundError;
import com.vaadin.flow.router.RouterLink;
import javax.servlet.http.HttpServletResponse;

@ParentLayout(MainView.class)
@PageTitle(AppConst.TITLE_NOT_FOUND)
public class CustomRouteNotFoundError extends RouteNotFoundError {

  public CustomRouteNotFoundError() {
    final RouterLink link = Component.from(
        ElementFactory.createRouterLink("", "Zurück zur Startseite."),
        RouterLink.class);
    this.getElement().appendChild(new H1("Oops, diese Seite existiert nicht! ").getElement(),
        link.getElement());
  }

  @Override
  public int setErrorParameter(final BeforeEnterEvent event,
      final ErrorParameter<NotFoundException> parameter) {
    return HttpServletResponse.SC_NOT_FOUND;
  }
}
