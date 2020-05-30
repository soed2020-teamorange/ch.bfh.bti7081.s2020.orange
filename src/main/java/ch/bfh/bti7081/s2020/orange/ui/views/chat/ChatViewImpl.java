package ch.bfh.bti7081.s2020.orange.ui.views.chat;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import com.vaadin.componentfactory.Chat.ChatNewMessageEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.spring.annotation.UIScope;
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

  com.vaadin.componentfactory.Chat chat = createChat();
  ListBox<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat> listBox = new ListBox<>();
  Div chatContent = new Div();

  @PostConstruct
  public void init() {
    this.setWidth("100%");
    this.setSplitterPosition(10);
    this.setPrimaryStyle("minWidth", "150px");
    this.setPrimaryStyle("width", "150px");

    listBox.addValueChangeListener(item -> {
      chatContent.removeAll();
      chat = createChat();
      chatContent.add(chat);

      observer.onLoadChat(item.getValue().getId());
    });

    chatContent.add(this.chat);

    addToPrimary(listBox);
    addToSecondary(chatContent);
  }

  private com.vaadin.componentfactory.Chat createChat() {
    com.vaadin.componentfactory.Chat chat = new com.vaadin.componentfactory.Chat();

    // somehow loading indicator is shown, even if we setLoading(false)
    chat.setLoadingIndicator(new Div());
    chat.addChatNewMessageListener(this::onAddNewMessage);

    return chat;
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
  public void setChats(List<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat> chats) {
    listBox.setItems(chats);

    if (currentUser.getUser() instanceof MedicalSpecialist) {
      listBox.setRenderer(new TextRenderer<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat>(
              c -> c.getPatient().getFirstName() + " " + c.getPatient().getLastName()
          )
      );
    } else if (currentUser.getUser() instanceof Patient) {
      listBox.setRenderer(new TextRenderer<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat>(
              c -> c.getMedicalSpecialist().getFirstName() + " " + c.getMedicalSpecialist()
                  .getLastName()
          )
      );
    }

    listBox.setValue(chats.get(0));
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
