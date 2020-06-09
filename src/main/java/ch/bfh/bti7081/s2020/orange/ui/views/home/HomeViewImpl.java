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

    final H1 titleLabel = new H1(
        String.format("Herzlich Willkommen %s %s", this.currentUser.getUser().getFirstName(),
            this.currentUser.getUser().getLastName()));

    final H2 accessLabel = new H2("Schnellzugriffe");

    final Text info = new Text("");

    final Div content = new Div();
    content.addClassName("home-content");

    if (this.currentUser.getUser().getRole().equals(Role.PATIENT)) {
      final Patient patient = (Patient) this.currentUser.getUser();

      info.setText(String.format("Ihr zugewiesener Therapeut: %s %s",
          patient.getMedicalSpecialist().getFirstName(),
          patient.getMedicalSpecialist().getLastName()));

      content.add(this.createChatInfo("Mit dem Chat können Sie Ihren Therapeuten kontaktieren."));

      content.add(this.createPersInfo(
          "Sehen Sie ihre aktuelle Informationen und den zugewiesenen Therapeuten an. Die Angaben können auch bearbeitet werden."));

      content.add(this.createActivityInfo());

      content.add(this.createMoodInfo());
    } else {
      content.add(this.createChatInfo("Mit dem Chat können Sie Ihre Patienten kontaktieren."));
      content.add(this.createPersInfo(
          "Sehen Sie ihre aktuelle Informationen und die zugewiesenen Patienten an. Die Angaben können auch bearbeitet werden."));

      content.add(this.createPatientInfo());
    }

    this.add(titleLabel);
    this.add(info);

    this.add(accessLabel);

    this.add(content);
  }

  private Div createMoodInfo() {
    final H3 moodTitle = new H3("Stimmungstagebuch");
    final Text moodDesc = new Text(
        "Erfassen Sie ihre Stimmung und analysieren Sie diese danach.");
    final Button moodButton = new Button("Zur Übersicht");
    moodButton
        .addClickListener(
            e -> this.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_MOOD_DIARY_OVERVIEW)));
    final Button moodCreateButton = new Button("Eintrag erstellen");
    moodCreateButton
        .addClickListener(
            e -> this.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_MOOD_DIARY_CREATE_ENTRY)));

    final Div moodInfo = new Div();
    moodInfo.add(moodTitle);
    moodInfo.add(moodDesc);
    moodInfo.add(moodButton);
    moodInfo.add(moodCreateButton);
    return moodInfo;
  }

  private Div createActivityInfo() {
    final H3 activityTitle = new H3("Aktivitätenbuch");
    final Text activityDesc = new Text(
        "Erfassen Sie ihre Tätigkeiten und analysieren Sie diese danach.");
    final Button activityButton = new Button("Zur Übersicht");
    activityButton
        .addClickListener(
            e -> this.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_ACTIVITY_DIARY_OVERVIEW)));
    final Button activityCreateButton = new Button("Eintrag erstellen");
    activityCreateButton
        .addClickListener(
            e -> this.getUI()
                .ifPresent(ui -> ui.navigate(AppConst.PAGE_ACTIVITY_DIARY_CREATE_ENTRY)));

    final Div activityInfo = new Div();
    activityInfo.add(activityTitle);
    activityInfo.add(activityDesc);
    activityInfo.add(activityButton);
    activityInfo.add(activityCreateButton);
    return activityInfo;
  }

  private Div createPersInfo(final String text) {
    final H3 persInfoTitle = new H3("Persönliche Informationen");
    final Text persInfoDesc = new Text(text);
    final Button persInfoButton = new Button("Zu persönlichen Informationen");
    persInfoButton
        .addClickListener(
            e -> this.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_USER_INFOS_EDIT)));

    final Div persInfoInfo = new Div();
    persInfoInfo.add(persInfoTitle);
    persInfoInfo.add(persInfoDesc);
    persInfoInfo.add(persInfoButton);
    return persInfoInfo;
  }

  private Div createChatInfo(final String text) {
    final H3 chatTitle = new H3("Chat");
    final Text chatDesc = new Text(text);
    final Button chatButton = new Button("Zum Chat");
    chatButton.addClickListener(e -> this.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_CHAT)));

    final Div chatInfo = new Div();
    chatInfo.add(chatTitle);
    chatInfo.add(chatDesc);
    chatInfo.add(chatButton);
    return chatInfo;
  }

  private Div createPatientInfo() {
    final H3 chatTitle = new H3("Patient registrieren");
    final Text chatDesc = new Text("Registrieren Sie hier einen neuen Kunden.");
    final Button chatButton = new Button("Zum Patient erstellen");
    chatButton.addClickListener(
        e -> this.getUI().ifPresent(ui -> ui.navigate(AppConst.PAGE_REGISTER_PATIENT)));

    final Div chatInfo = new Div();
    chatInfo.add(chatTitle);
    chatInfo.add(chatDesc);
    chatInfo.add(chatButton);
    return chatInfo;
  }

  @Override
  public <C> C getComponent(final Class<C> type) {
    return type.cast(this);
  }
}
