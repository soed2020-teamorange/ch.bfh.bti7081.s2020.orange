package ch.bfh.bti7081.s2020.orange.ui.views.login;

import ch.bfh.bti7081.s2020.orange.application.security.SecurityUtils;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.views.home.HomeViewRoute;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(AppConst.PAGE_LOGIN)
@PageTitle(AppConst.TITLE_LOGIN)
@Viewport(AppConst.VIEWPORT)
public class LoginView extends LoginOverlay
    implements AfterNavigationObserver, BeforeEnterObserver {

  public LoginView() {
    final LoginI18n i18n = LoginI18n.createDefault();
    i18n.setHeader(new LoginI18n.Header());
    i18n.getHeader().setTitle("Projekt MHC-PMS");
    i18n.getHeader().setDescription("Team Orange");
    i18n.setAdditionalInformation(null);

    i18n.setForm(new LoginI18n.Form());
    i18n.getForm().setSubmit("Anmelden");
    i18n.getForm().setTitle("Anmeldung");
    i18n.getForm().setUsername("E-Mail");
    i18n.getForm().setPassword("Passwort");

    this.setI18n(i18n);
    this.setForgotPasswordButtonVisible(false);
    this.setAction("login");
  }

  @Override
  public void beforeEnter(final BeforeEnterEvent event) {
    if (SecurityUtils.isUserLoggedIn()) {
      event.forwardTo(HomeViewRoute.class);
    } else {
      this.setOpened(true);
    }
  }

  @Override
  public void afterNavigation(final AfterNavigationEvent event) {
    this.setError(
        event.getLocation().getQueryParameters().getParameters().containsKey(
            "error"));
  }

}
