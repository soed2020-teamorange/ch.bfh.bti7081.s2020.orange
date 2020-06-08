package ch.bfh.bti7081.s2020.orange.ui.views.landingPage;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.UIScope;
import ch.bfh.bti7081.s2020.orange.ui.views.landingPage.LandingPageView.Observer;
import lombok.Setter;

@UIScope
@Component
public class LandingPageViewImpl extends VerticalLayout implements LandingPageView {

	TextField titleTextField = new TextField("Landing Page");
	
	@Setter
	private Observer observer;

	  @PostConstruct
	  public void init() {
	    add(titleTextField);

	  }


	@Override
	public void setResult(String result) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public <C> C getComponent(Class<C> type) {
		return type.cast(this);
	}
}
