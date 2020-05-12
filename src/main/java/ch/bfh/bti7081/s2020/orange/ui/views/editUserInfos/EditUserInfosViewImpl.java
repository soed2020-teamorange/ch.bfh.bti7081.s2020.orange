package ch.bfh.bti7081.s2020.orange.ui.views.editUserInfos;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.UIScope;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@UIScope
@Component
@RequiredArgsConstructor
public class EditUserInfosViewImpl extends VerticalLayout implements EditUserInfosView, HasLogger {

  private final CurrentUser currentUser;

  @Setter
  private Observer observer;

  Binder<User> binder = new BeanValidationBinder<>(User.class);

  @PostConstruct
  public void init() {

    add(new H1(AppConst.TITLE_EDITUSERINFOS),
        buildForm());

    Button submit = new Button("Absenden");
    submit.addClickListener(this::onSubmit);

    add(submit);
  }

  private void onSubmit(ClickEvent<Button> buttonClickEvent) {
    observer.onSaveUser(binder.getBean());
  }

  private Div buildForm() {
    // Create UI components
    TextField firstName = new TextField("Vorname");
    TextField lastName = new TextField("Nachname");

    Div errorsLayout = new Div();

    // Wrap components in layouts
    HorizontalLayout formLayout = new HorizontalLayout(firstName, lastName);
    Div wrapperLayout = new Div(formLayout, errorsLayout);
    formLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
    wrapperLayout.setWidth("100%");

    binder.forField(firstName)
        .bind(User::getFirstName, User::setFirstName);

    binder.forField(lastName)
        .bind(User::getLastName, User::setLastName);

    binder.setBean(currentUser.getUser());

    return wrapperLayout;
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }

  @Override
  public void setUser(User user) {
    binder.setBean(user);
  }
}
