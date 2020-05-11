package ch.bfh.bti7081.s2020.orange.ui.views.home;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import javax.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class HomeViewImpl extends VerticalLayout implements HomeView {

  private final Label resultTextField = new Label("Resultat: ");

  TextField baseTextField = new TextField("Base");
  TextField powerTextField = new TextField("Power");

  @Setter
  private Observer observer;

  @PostConstruct
  public void init() {
    add(baseTextField);
    add(powerTextField);

    Button button = new Button("Absenden");
    button.addClickListener(this::onClick);
    add(button);

    add(resultTextField);
  }

  private void onClick(ClickEvent<Button> buttonClickEvent) {
    observer.onCalculate(Long.parseLong(baseTextField.getValue()),
        Long.parseLong(powerTextField.getValue()));
  }

  @Override
  public void setResult(String result) {
    resultTextField.setText(result);
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
