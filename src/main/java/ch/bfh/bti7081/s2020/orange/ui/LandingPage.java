package ch.bfh.bti7081.s2020.orange.ui;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.component.polymertemplate.Id;

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
    TextField startseite = new TextField();
    startseite.setValue("PMS Startseite");
    
    HorizontalLayout layout = new HorizontalLayout();
    layout.setWidth("300px");
    layout.getStyle().set("border", "1px solid #9E9E9E");
    layout.setPadding(true);

    /*
     * Makes the component consider border and paddings when computing the
     * size
     */
    layout.setBoxSizing(BoxSizing.BORDER_BOX);

    Div component1 = createComponent(1, "#78909C");
    component1.setWidth("50%");
    Div component2 = createComponent(2, "#546E7A");
    component2.setWidth("50%");
    layout.add(component1, component2);

    /**
     * This model binds properties between LandingPage and landing-page
     */
    public interface LandingPageModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
	private Div createComponent(int i, String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
