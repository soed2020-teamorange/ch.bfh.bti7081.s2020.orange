package ch.bfh.bti7081.s2020.orange.ui.views.home;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.Role;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.ui.utils.AppConst;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.spring.annotation.UIScope;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@UIScope
@Component
@RequiredArgsConstructor
public class HomeViewImpl extends VerticalLayout implements HomeView {

  private final CurrentUser currentUser;

  @PostConstruct
  public void init() {

    H1 titleLabel = new H1(
        String.format("Herzlich Willkommen %s %s", currentUser.getUser().getFirstName(),
            currentUser.getUser().getLastName()));

    H2 accessLabel = new H2("Schnellzugriffe");

    Text info = new Text("");

    Div content = new Div();
    content.addClassName("home-content");

    if (currentUser.getUser().getRole().equals(Role.PATIENT)) {
      Patient patient = (Patient) currentUser.getUser();

      info.setText(String.format("Ihr zugewiesener Therapeut: %s %s",
          patient.getMedicalSpecialist().getFirstName(),
          patient.getMedicalSpecialist().getLastName()));

      content.add(createChatInfo("Mit dem Chat können Sie Ihren Therapeuten kontaktieren."));

      content.add(createPersInfo(
          "Sehen Sie ihre aktuelle Informationen und den zugewiesenen Therapeuten an. Die Angaben können auch bearbeitet werden."));

      content.add(createActivityInfo());

      content.add(createMoodInfo());
    } else {
      content.add(createChatInfo("Mit dem Chat können Sie Ihre Patienten kontaktieren."));
      content.add(createPersInfo(
          "Sehen Sie ihre aktuelle Informationen und die zugewiesenen Patienten an. Die Angaben können auch bearbeitet werden."));

      content.add(createPatientInfo());
    }

    add(titleLabel);
    add(info);

    add(accessLabel);

    add(content);
  }

  private Div createMoodInfo() {
    H3 moodTitle = new H3("Stimmungstagebuch");
    Text moodDesc = new Text(
        "Erfassen Sie ihre Stimmung und analysieren Sie diese danach.");
    Button moodButton = new Button("Zur Übersicht");
    moodButton
        .addClickListener(
            e -> getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_MOOD_DIARY_OVERVIEW)));
    Button moodCreateButton = new Button("Eintrag erstellen");
    moodCreateButton
        .addClickListener(
            e -> getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_MOOD_DIARY_CREATE_ENTRY)));

    Div moodInfo = new Div();
    moodInfo.add(moodTitle);
    moodInfo.add(moodDesc);
    moodInfo.add(moodButton);
    moodInfo.add(moodCreateButton);
    return moodInfo;
  }

  private Div createActivityInfo() {
    H3 activityTitle = new H3("Aktivitätenbuch");
    Text activityDesc = new Text(
        "Erfassen Sie ihre Tätigkeiten und analysieren Sie diese danach.");
    Button activityButton = new Button("Zur Übersicht");
    activityButton
        .addClickListener(
            e -> getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_ACTIVITY_DIARY_OVERVIEW)));
    Button activityCreateButton = new Button("Eintrag erstellen");
    activityCreateButton
        .addClickListener(
            e -> getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_ACTIVITY_DIARY_CREATE_ENTRY)));

    Div activityInfo = new Div();
    activityInfo.add(activityTitle);
    activityInfo.add(activityDesc);
    activityInfo.add(activityButton);
    activityInfo.add(activityCreateButton);
    return activityInfo;
  }

  private Div createPersInfo(String text) {
    H3 persInfoTitle = new H3("Persönliche Informationen");
    Text persInfoDesc = new Text(text);
    Button persInfoButton = new Button("Zu persönlichen Informationen");
    persInfoButton
        .addClickListener(
            e -> getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_USER_INFOS_EDIT)));

    Div persInfoInfo = new Div();
    persInfoInfo.add(persInfoTitle);
    persInfoInfo.add(persInfoDesc);
    persInfoInfo.add(persInfoButton);
    return persInfoInfo;
  }

  private Div createChatInfo(String text) {
    H3 chatTitle = new H3("Chat");
    Text chatDesc = new Text(text);
    Button chatButton = new Button("Zum Chat");
    chatButton.addClickListener(e -> getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_CHAT)));

    Div chatInfo = new Div();
    chatInfo.add(chatTitle);
    chatInfo.add(chatDesc);
    chatInfo.add(chatButton);
    return chatInfo;
  }

  private Div createPatientInfo() {
    H3 chatTitle = new H3("Patient registrieren");
    Text chatDesc = new Text("Registrieren Sie hier einen neuen Kunden.");
    Button chatButton = new Button("Zum Patient erstellen");
    chatButton.addClickListener(
        e -> getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_REGISTER_PATIENT)));

    Div chatInfo = new Div();
    chatInfo.add(chatTitle);
    chatInfo.add(chatDesc);
    chatInfo.add(chatButton);
    return chatInfo;
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
