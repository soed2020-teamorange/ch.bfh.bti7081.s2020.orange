package ch.bfh.bti7081.s2020.orange.ui;

import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.TITLE_HOME;
import static ch.bfh.bti7081.s2020.orange.ui.utils.AppConst.VIEWPORT;

import ch.bfh.bti7081.s2020.orange.ui.views.home.HomeViewRoute;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Viewport(VIEWPORT)
@PWA(name = "Team Orange - Projekt MHC-PMS", shortName = "MHC-PMS Orange", startPath = "login")
public class MainView extends AppLayout {

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
}
