package ch.bfh.bti7081.s2020.orange.ui.views.landingPage;

import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.BoxSizing;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.textfield.TextField;



public class LandingPage extends VerticalLayout {

    /**
     * Creates a new LandingPageAlt.
     */
    public LandingPage() {
        // You can initialise any data required for the connected UI components here.
    	
    	//Titel der Startseite
        TextField startseite = new TextField();
        startseite.setValue("PMS Startseite");
        
        
        //Layout for the different Elements e.g. Tagebuch, Aktivitäten, Medikamente 
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
        
        
        //Notification for latest Chat Message
        Label content = new Label(
                "Hello, I am a notification with components!");
        NativeButton buttonInside = new NativeButton("Bye");
        Notification notification = new Notification();
        notification.setDuration(3000);
        buttonInside.addClickListener(event -> notification.close());
        notification.setPosition(Position.MIDDLE);
        //button.addClickListener(event -> notification.open());
    }
    
  

    /**
     * This model binds properties between LandingPageAlt and landing-page-alt
     */
    public interface LandingPageAltModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
	private Div createComponent(int i, String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
