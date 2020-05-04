package ch.bfh.bti7081.s2020.orange.ui.views.home;

import ch.bfh.bti7081.s2020.orange.ui.MainView;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.templatemodel.TemplateModel;

/**
 * A Designer generated component for the home-view template.
 * <p>
 * Designer will add and remove fields with @Id mappings but does not overwrite or otherwise change
 * this file.
 */
@Route(value = AppConst.PAGE_HOME, layout = MainView.class)
@RouteAlias(value = AppConst.PAGE_ROOT, layout = MainView.class)
@PageTitle(AppConst.TITLE_HOME)
@Tag("home-view")
@JsModule("./src/views/home/home-view.js")
public class HomeView extends PolymerTemplate<HomeView.HomeViewModel> {

  /**
   * Creates a new HomeView.
   */
  public HomeView() {
    // You can initialise any data required for the connected UI components here.
  }

  /**
   * This model binds properties between HomeView and home-view
   */
  public interface HomeViewModel extends TemplateModel {
    // Add setters and getters for template properties here.
  }
}
