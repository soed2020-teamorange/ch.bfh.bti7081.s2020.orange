package ch.bfh.bti7081.s2020.orange.ui;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the landing-page template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("landing-page")
@JsModule("./src/main/java/ch/bfh/bti7081/s2020/orange/ui/landing-page.js")
public class LandingPage extends PolymerTemplate<LandingPage.LandingPageModel> {

    /**
     * Creates a new LandingPage.
     */
    public LandingPage() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between LandingPage and landing-page
     */
    public interface LandingPageModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
