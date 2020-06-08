package ch.bfh.bti7081.s2020.orange.ui.views.home;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import javax.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.stereotype.Component;

@UIScope
@Component
public class HomeViewImpl extends VerticalLayout implements HomeView {

  Label userLabel = new Label("Angemeldet als:");
  Label userNameLabel = new Label();
  H2 accessLabel = new H2("Schnellzugriff:");

  @Setter
  private Observer observer;
  //private CurrentUser currentUser;

  @PostConstruct
  public void init() {
	  
	add(createTitle());
    add(userLabel);
    add(userNameLabel);
    add(accessLabel);
    add(quickAccess());    
  }
  
  private Div quickAccess() {
	  //if (currentUser.getUser() instanceof Patient)  {
		  VerticalLayout formLayout = new VerticalLayout(accessLabel, chatButton(),activityButton(),moodButton());
		  Div wrapperLayout = new Div(formLayout);
		  return wrapperLayout;
	  //}
	  //else {
		  //FormLayout formLayout = new FormLayout(accessLabel, chatButton(),patientButton());
		  //Div wrapperLayout = new Div(formLayout);
		//  return wrapperLayout;
	  //}
  }
  
  private Button chatButton() {
	  Button button = new Button("zum Chat");
	  button.addClickListener(
	  e -> button.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_CHAT)));

	  return button;
  }
  private Button patientButton() {
	  Button button = new Button("zum Chat");
	  button.addClickListener(
	  e -> button.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_REGISTER_PATIENT)));

	  return button;
  }
  
  private Button activityButton() {
	  Button button = new Button("zum Aktivitätentagebuch");
	  button.addClickListener(
	  e -> button.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_ACTIVITY_DIARY_OVERVIEW)));

	  return button;
  }
  
  private Button moodButton() {
	  Button button = new Button("zum Stimmungstagebuch");
	  button.addClickListener(
	  e -> button.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_MOOD_DIARY_OVERVIEW)));

	  return button;
  }

  @Override
  public void setResult(String result) {
	  userNameLabel.setText(result);
  }
  
  /*public void setUser(CurrentUser user) {
	  currentUser = user;
  }*/

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
  
  private H1 createTitle() {
	    return new H1("Willkommen im PMS");
  }
  
}
