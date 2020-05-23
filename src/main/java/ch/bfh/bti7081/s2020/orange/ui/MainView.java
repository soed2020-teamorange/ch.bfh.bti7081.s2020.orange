package ch.bfh.bti7081.s2020.orange.ui;

import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_CHAT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_HOME;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_LOGOUT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_MOOD_DIARY;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_REGISTER_PATIENT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_USER_INFOS_EDIT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_USER_INFOS_SHOW;

import ch.bfh.bti7081.s2020.orange.application.security.SecurityUtils;
import ch.bfh.bti7081.s2020.orange.ui.views.chat.ChatViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.home.HomeViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview.MoodDiaryOverviewViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.register_patient.RegisterPatientViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.user_infos.edit.UserInfosEditViewRoute;
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
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ParentLayout(value = AppView.class)
public class MainView extends AppLayout implements RouterLayout {

  private final Tabs menu;

  public MainView() {
    this.setDrawerOpened(false);
    Span appName = new Span("Team Orange - Projekt MHC-PMS");
    appName.addClassName("hide-on-mobile");

    menu = createMenuTabs();

    this.addToNavbar(appName);
    this.addToNavbar(true, menu);

    getElement().addEventListener("search-focus", e -> {
      getElement().getClassList().add("hide-navbar");
    });

    getElement().addEventListener("search-blur", e -> {
      getElement().getClassList().remove("hide-navbar");
    });
  }

  @Override
  protected void afterNavigation() {
    super.afterNavigation();

    try {
      String target = RouteConfiguration.forSessionScope().getUrl(this.getContent().getClass());
      Optional<Component> tabToSelect = menu.getChildren().filter(tab -> {
        Component child = tab.getChildren().findFirst().get();
        return child instanceof RouterLink && ((RouterLink) child).getHref().equals(target);
      }).findFirst();

      // fallback, check if child route
      if (!tabToSelect.isPresent()) {
        tabToSelect = menu.getChildren().filter(tab -> {
          Component child = tab.getChildren().findFirst().get();
          return child instanceof RouterLink && target.startsWith(((RouterLink) child).getHref());
        }).findFirst();
      }

      tabToSelect.ifPresent(tab -> menu.setSelectedTab((Tab) tab));
    } catch (NotFoundException ignored) {

    }
  }

  private static Tabs createMenuTabs() {
    final Tabs tabs = new Tabs();
    tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
    tabs.add(getAvailableTabs());
    return tabs;
  }

  private static Tab[] getAvailableTabs() {
    final List<Tab> tabs = new ArrayList<>();

    tabs.add(createTab(VaadinIcon.HOME, TITLE_HOME, HomeViewRoute.class));

    tabs.add(createTab(VaadinIcon.CHAT, TITLE_CHAT, ChatViewRoute.class));

    if (SecurityUtils.isAccessGranted(RegisterPatientViewRoute.class)) {
      tabs.add(
          createTab(VaadinIcon.CLIPBOARD_CROSS, TITLE_REGISTER_PATIENT,
              RegisterPatientViewRoute.class));
    }

    tabs.add(createTab(VaadinIcon.WORKPLACE, TITLE_USER_INFOS_EDIT, UserInfosEditViewRoute.class));
    tabs.add(createTab(VaadinIcon.USER, TITLE_USER_INFOS_SHOW,
        ch.bfh.bti7081.s2020.orange.ui.views.user_infos.show.UserInfosShowViewRoute.class));

    if (SecurityUtils.isAccessGranted(MoodDiaryOverviewViewRoute.class)) {
      tabs.add(createTab(VaadinIcon.SCALE, TITLE_MOOD_DIARY, MoodDiaryOverviewViewRoute.class));
    }

    final String contextPath = VaadinServlet.getCurrent().getServletContext().getContextPath();
    final Tab logoutTab = createTab(createLogoutLink(contextPath));
    tabs.add(logoutTab);

    return tabs.toArray(new Tab[tabs.size()]);
  }

  private static Tab createTab(VaadinIcon icon, String title,
      Class<? extends Component> viewClass) {
    return createTab(populateLink(new RouterLink(null, viewClass), icon, title));
  }

  private static Tab createTab(Component content) {
    final Tab tab = new Tab();
    tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
    tab.add(content);
    return tab;
  }

  private static <T extends HasComponents> T populateLink(T a, VaadinIcon icon, String title) {
    a.add(icon.create());
    a.add(title);
    return a;
  }

  private static Anchor createLogoutLink(String contextPath) {
    final Anchor a = populateLink(new Anchor(), VaadinIcon.ARROW_RIGHT, TITLE_LOGOUT);
    a.setHref(contextPath + "/logout");
    return a;
  }
}
