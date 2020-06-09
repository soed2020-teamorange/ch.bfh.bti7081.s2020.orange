package ch.bfh.bti7081.s2020.orange.ui;


import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_ACTIVITY_DIARY;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_CHAT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_HOME;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_LOGOUT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_MOOD_DIARY;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_PRESCRIPTION_OVERVIEW;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_REGISTER_PATIENT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_USER_INFOS_EDIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServlet;

import ch.bfh.bti7081.s2020.orange.application.security.SecurityUtils;
import ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.overview.ActivityDiaryOverviewViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.chat.ChatViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.home.HomeViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview.MoodDiaryOverviewViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.prescription.patientOverview.PrescriptionPatientViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.register_patient.RegisterPatientViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.user_infos.edit.UserInfosEditViewRoute;

@ParentLayout(AppView.class)
public class MainView extends AppLayout {

  private final Tabs menu;

  public MainView() {
    setDrawerOpened(false);
    final Span appName = new Span("Team Orange - Projekt MHC-PMS");
    appName.addClassName("hide-on-mobile");
    appName.addClassName("app-title");

    this.menu = MainView.createMenuTabs();

    addToNavbar(appName);
    addToNavbar(true, this.menu);

    this.getElement().addEventListener("search-focus", e -> {
      this.getElement().getClassList().add("hide-navbar");
    });

    this.getElement().addEventListener("search-blur", e -> {
      this.getElement().getClassList().remove("hide-navbar");
    });
  }

  @Override
  protected void afterNavigation() {
    super.afterNavigation();

    try {
      final String target = RouteConfiguration.forSessionScope().getUrl(getContent().getClass());
      Optional<Component> tabToSelect = this.menu.getChildren().filter(tab -> {
        final Component child = tab.getChildren().findFirst().get();
        return child instanceof RouterLink && ((RouterLink) child).getHref().equals(target);
      }).findFirst();

      // fallback, check if child route
      if (!tabToSelect.isPresent()) {
        tabToSelect = this.menu.getChildren().filter(tab -> {
          final Component child = tab.getChildren().findFirst().get();
          return child instanceof RouterLink && target.startsWith(((RouterLink) child).getHref());
        }).findFirst();
      }

      tabToSelect.ifPresent(tab -> this.menu.setSelectedTab((Tab) tab));
    } catch (final NotFoundException ignored) {

    }
  }

  private static Tabs createMenuTabs() {
    Tabs tabs = new Tabs();
    tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
    tabs.add(MainView.getAvailableTabs());
    return tabs;
  }

  private static Tab[] getAvailableTabs() {
    List<Tab> tabs = new ArrayList<>();

    tabs.add(MainView.createTab(VaadinIcon.HOME, TITLE_HOME, HomeViewRoute.class));

    tabs.add(MainView.createTab(VaadinIcon.CHAT, TITLE_CHAT, ChatViewRoute.class));

    if (SecurityUtils.isAccessGranted(RegisterPatientViewRoute.class)) {
      tabs.add(
          MainView.createTab(VaadinIcon.CLIPBOARD_CROSS, TITLE_REGISTER_PATIENT,
              RegisterPatientViewRoute.class));
    }
    
    if (SecurityUtils.isAccessGranted(PrescriptionPatientViewRoute.class)) {
    	tabs.add(
			createTab(VaadinIcon.CLIPBOARD_HEART, TITLE_PRESCRIPTION_OVERVIEW,
				PrescriptionPatientViewRoute.class));
    }

    if (SecurityUtils.isAccessGranted(ActivityDiaryOverviewViewRoute.class)) {
      tabs.add(
          MainView
              .createTab(VaadinIcon.GOLF, TITLE_ACTIVITY_DIARY,
                  ActivityDiaryOverviewViewRoute.class));
    }

    if (SecurityUtils.isAccessGranted(MoodDiaryOverviewViewRoute.class)) {
      tabs.add(
          MainView.createTab(VaadinIcon.SCALE, TITLE_MOOD_DIARY, MoodDiaryOverviewViewRoute.class));
    }

    tabs.add(
        MainView.createTab(VaadinIcon.USER, TITLE_USER_INFOS_EDIT, UserInfosEditViewRoute.class));

    String contextPath = VaadinServlet.getCurrent().getServletContext().getContextPath();
    Tab logoutTab = MainView.createTab(MainView.createLogoutLink(contextPath));
    tabs.add(logoutTab);

    return tabs.toArray(new Tab[tabs.size()]);
  }

  private static Tab createTab(final VaadinIcon icon, final String title,
      final Class<? extends Component> viewClass) {
    return MainView.createTab(MainView.populateLink(new RouterLink(null, viewClass), icon, title));
  }

  private static Tab createTab(final Component content) {
    Tab tab = new Tab();
    tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
    tab.add(content);
    return tab;
  }

  private static <T extends HasComponents> T populateLink(final T a, final VaadinIcon icon,
      final String title) {
    a.add(icon.create());
    a.add(title);
    return a;
  }

  private static Anchor createLogoutLink(final String contextPath) {
    Anchor a = MainView.populateLink(new Anchor(), VaadinIcon.ARROW_RIGHT, TITLE_LOGOUT);
    a.setHref(contextPath + "/logout");
    return a;
  }
}
