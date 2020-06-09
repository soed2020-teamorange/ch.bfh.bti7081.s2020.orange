package ch.bfh.bti7081.s2020.orange.ui.views.errors;

import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.exceptions.AccessDeniedException;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import javax.servlet.http.HttpServletResponse;

@ParentLayout(MainView.class)
@PageTitle(AppConst.TITLE_ACCESS_DENIED)
@Route
public class AccessDeniedView extends VerticalLayout implements
    HasErrorParameter<AccessDeniedException>, RouterLayout {

  public AccessDeniedView() {
    final RouterLink link = Component.from(
        ElementFactory.createRouterLink("", "Zurück zur Startseite."),
        RouterLink.class);

    this.getElement().appendChild(new H1("Oops, Sie haben keine Berechtigungen! ").getElement(),
        link.getElement());

  }

  @Override
  public int setErrorParameter(final BeforeEnterEvent beforeEnterEvent,
      final ErrorParameter<AccessDeniedException> errorParameter) {

    return HttpServletResponse.SC_FORBIDDEN;
  }
}
