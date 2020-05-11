package ch.bfh.bti7081.s2020.orange.ui.views.editUserInfos;

import ch.bfh.bti7081.s2020.orange.backend.model.Patient;
import ch.bfh.bti7081.s2020.orange.backend.model.Person;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import ch.bfh.bti7081.s2020.orange.ui.utils.HasLogger;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class EditUserInfosViewImpl extends VerticalLayout implements EditUserInfosView, HasLogger {


  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {

    add(new H1(AppConst.TITLE_EDITUSERINFOS),
            buildForm());
  }

  private Div buildForm() {

    Button loadButton = new Button("Daten laden");
    add(loadButton);

    // Create UI components
    TextField firstName = new TextField("Vorname");
    TextField lastName = new TextField("Nachname");

    Div errorsLayout = new Div();

    // Wrap components in layouts
    HorizontalLayout formLayout = new HorizontalLayout(firstName, lastName);
    Div wrapperLayout = new Div(formLayout, errorsLayout);
    formLayout.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
    wrapperLayout.setWidth("100%");

    Binder<Person> binder = new Binder<>(Person.class);
    binder.forField(firstName)
            .bind(Person::getFirstName, Person::setFirstName);

    binder.forField(lastName)
            .bind(Person::getLastName, Person::setLastName);

    loadButton.addClickListener(click -> {
      binder.readBean(observer.getPersonById(1));
    });

    return wrapperLayout;
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
