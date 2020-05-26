package ch.bfh.bti7081.s2020.orange.ui.views.chat;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import ch.bfh.bti7081.s2020.orange.backend.service.PatientService;
import com.vaadin.componentfactory.Chat;
import com.vaadin.componentfactory.Chat.ChatNewMessageEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.spring.annotation.UIScope;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@UIScope
@Component
@RequiredArgsConstructor
public class ChatViewImpl extends SplitLayout implements ChatView {

  @Setter
  private ChatView.Observer observer;

  private final CurrentUser currentUser;

  PatientService patientService;

  Chat chat = new Chat();
  VerticalLayout verticalLayout = new VerticalLayout();
  ListBox listBox = new ListBox<>();
  Button newChatButton = new Button(new Icon(VaadinIcon.PLUS_CIRCLE));
  Select newChatSelect = new Select<>();

  @PostConstruct
  public void init() {
    this.setWidth("100%");
    this.setSplitterPosition(10);
    this.setPrimaryStyle("minWidth", "10%");
    this.setPrimaryStyle("maxWidth", "20%");

    // somehow loading indicator is shown, even if we setLoading(false)
    chat.setLoadingIndicator(new Div());
    chat.addChatNewMessageListener(this::onAddNewMessage);

    listBox.addValueChangeListener(item -> {
      //TODO: Switch chat
      System.out.println("Changed chat to: " + item.getValue());
    });

    newChatButton.addClickListener(click -> {
      //TODO: Add new chat
      System.out.println("Added " + newChatSelect.getValue() + " to chat");
      //TODO: Remove observer?
      //observer.onStartNewConversation();
    });

    newChatSelect.setPlaceholder("Neuer Chat");

    addToPrimary(verticalLayout);
    addToSecondary(chat);
    verticalLayout.add(listBox, newChatButton, newChatSelect);
  }

  private void onAddNewMessage(ChatNewMessageEvent chatNewMessageEvent) {
    observer.onAddMessage(chatNewMessageEvent.getMessage());

    chatNewMessageEvent.getSource().clearInput();
    chatNewMessageEvent.getSource().scrollToBottom();
  }

  @Override
  public void addMessage(Message message) {
    getUI().ifPresentOrElse(ui ->
            ui.access(() -> chat.addNewMessage(toChatMessage(message))),
        // if ui isn't yet present
        () -> chat.addNewMessage(toChatMessage(message))
    );

  }

  @Override
  public void showMedicalSpecialists(MedicalSpecialist medicalSpecialist) {
    listBox.setItems(Arrays.asList(medicalSpecialist));
    listBox.setRenderer(new TextRenderer(m -> {
      return ((MedicalSpecialist) m).getFirstName() + " " + ((MedicalSpecialist) m).getLastName();
    }));
  }

  @Override
  public void showPatients(List<Patient> patients) {
    listBox.setItems(patients);
    listBox.setRenderer(new TextRenderer<>(p -> {
      return ((Patient) p).getFirstName() + " " + ((Patient) p).getLastName();
    }));
  }

  @Override
  public void listNewChatPartners(List<Patient> patients) {
    newChatSelect.setItems(patients);
    newChatSelect.setRenderer(new TextRenderer<>(p -> {
      return ((Patient) p).getFirstName() + " " + ((Patient) p).getLastName();
    }));
  }

  private com.vaadin.componentfactory.model.Message toChatMessage(Message message) {
    return new com.vaadin.componentfactory.model.Message(message.getContent(), null,
        message.getSender().getFirstName() + " " + message.getSender().getLastName(),
        currentUser.getUser().getId().equals(message.getSender().getId()));
  }

  @Override
  public <C> C getComponent(Class<C> type) {
    return type.cast(this);
  }
}
