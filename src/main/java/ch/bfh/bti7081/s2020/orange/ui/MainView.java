package ch.bfh.bti7081.s2020.orange.ui;


import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_ACTIVITY_DIARY;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_CHAT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_HOME;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_LOGOUT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_MOOD_DIARY;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_REGISTER_PATIENT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_USER_INFOS_EDIT;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_USER_INFOS_SHOW;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.VIEWPORT;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.application.security.SecurityUtils;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.create_entry.ActivityDiaryCreateEntryViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.activity_diary.overview.ActivityDiaryOverviewViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.chat.ChatViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.home.HomeViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.mood_diary.overview.MoodDiaryOverviewViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.register_patient.RegisterPatientViewRoute;
import ch.bfh.bti7081.s2020.orange.ui.views.user_infos.edit.UserInfosEditViewRoute;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Push
@Viewport(VIEWPORT)
@PWA(name = "Team Orange - Projekt MHC-PMS", shortName = "MHC-PMS Orange", startPath = "login")
public class MainView extends AppLayout {

  private final CurrentUser currentUser;
  private final Tabs menu;

  public MainView(CurrentUser currentUser) {
    this.currentUser = currentUser;
    this.setDrawerOpened(false);
    Span appName = new Span("Team Orange - Projekt MHC-PMS");
    appName.addClassName("hide-on-mobile");

    menu = createMenuTabs();

    this.addToNavbar(appName);
    this.addToNavbar(true, menu);

    if (this.currentUser.getUser() instanceof Patient) {
      createPopup();
    }

    getElement().addEventListener("search-focus", e -> {
      getElement().getClassList().add("hide-navbar");
    });

    getElement().addEventListener("search-blur", e -> {
      getElement().getClassList().remove("hide-navbar");
    });
  }

  private void createPopup() {
    final Button emergencyButton = new Button(new Icon(VaadinIcon.PHONE));
    ContextMenu contextMenu = new ContextMenu(emergencyButton);
    contextMenu.addItem("Notruf: 144");
    contextMenu.addItem("Dargebotene Hand: 143");
    Patient patient = (Patient) currentUser.getUser();
    MedicalSpecialist medicalSpecialist = patient.getMedicalSpecialist();
    if (medicalSpecialist.getPhone() != null) {
      contextMenu.addItem(
          medicalSpecialist.getFirstName() + " " + medicalSpecialist
              .getLastName() + ": " + medicalSpecialist.getPhone());
    }
    contextMenu.setOpenOnClick(true);
    contextMenu.setEnabled(false);
    this.addToNavbar(emergencyButton);
  }

  @Override
  protected void afterNavigation() {
    super.afterNavigation();
    String target = RouteConfiguration.forSessionScope().getUrl(this.getContent().getClass());
    Optional<Component> tabToSelect = menu.getChildren().filter(tab -> {
      Component child = tab.getChildren().findFirst().get();
      return child instanceof RouterLink && ((RouterLink) child).getHref().equals(target);
    }).findFirst();
    tabToSelect.ifPresent(tab -> menu.setSelectedTab((Tab) tab));
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

    if (SecurityUtils.isAccessGranted(ActivityDiaryCreateEntryViewRoute.class)) {
      tabs.add(
          createTab(VaadinIcon.GOLF, TITLE_ACTIVITY_DIARY, ActivityDiaryOverviewViewRoute.class));
    }

    if (SecurityUtils.isAccessGranted(MoodDiaryOverviewViewRoute.class)) {
      tabs.add(createTab(VaadinIcon.SCALE, TITLE_MOOD_DIARY, MoodDiaryOverviewViewRoute.class));
    }

    tabs.add(createTab(VaadinIcon.WORKPLACE, TITLE_USER_INFOS_EDIT, UserInfosEditViewRoute.class));
    tabs.add(createTab(VaadinIcon.USER, TITLE_USER_INFOS_SHOW,
        ch.bfh.bti7081.s2020.orange.ui.views.user_infos.show.UserInfosShowViewRoute.class));

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
