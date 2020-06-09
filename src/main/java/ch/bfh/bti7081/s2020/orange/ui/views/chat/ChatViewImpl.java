package ch.bfh.bti7081.s2020.orange.ui.views.chat;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Patient;
import com.vaadin.componentfactory.Chat;
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

  com.vaadin.componentfactory.Chat chat = this.createChat();
  ListBox<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat> listBox = new ListBox<>();
  Div chatContent = new Div();

  @PostConstruct
  public void init() {
    setWidth("100%");
    setSplitterPosition(50);

    this.listBox.addValueChangeListener(item -> {
      this.chatContent.removeAll();
      this.chat = this.createChat();
      this.chatContent.add(this.chat);

      if (null != item.getValue()) {
        this.observer.onLoadChat(item.getValue().getId());
      }
    });

    this.chatContent.add(chat);

    this.addToPrimary(this.listBox);
    this.addToSecondary(this.chatContent);
  }

  private com.vaadin.componentfactory.Chat createChat() {
    final com.vaadin.componentfactory.Chat chat = new com.vaadin.componentfactory.Chat();

    // somehow loading indicator is shown, even if we setLoading(false)
    chat.setLoadingIndicator(new Div());
    chat.addChatNewMessageListener(this::onAddNewMessage);

    return chat;
  }

  private void onAddNewMessage(final Chat.ChatNewMessageEvent chatNewMessageEvent) {
    this.observer.onAddMessage(chatNewMessageEvent.getMessage());

    chatNewMessageEvent.getSource().clearInput();
    chatNewMessageEvent.getSource().scrollToBottom();
  }

  @Override
  public void addMessage(final Message message) {
    this.getUI().ifPresentOrElse(ui ->
            ui.access(() -> this.chat.addNewMessage(this.toChatMessage(message))),
        // if ui isn't yet present
        () -> this.chat.addNewMessage(this.toChatMessage(message))
    );

  }

  @Override
  public void setChats(final List<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat> chats) {
    this.listBox.setItems(chats);

    if (this.currentUser.getUser() instanceof MedicalSpecialist) {
      this.listBox
          .setRenderer(new TextRenderer<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat>(
                  c -> c.getPatient().getFirstName() + " " + c.getPatient().getLastName()
              )
          );
    } else if (this.currentUser.getUser() instanceof Patient) {
      this.listBox
          .setRenderer(new TextRenderer<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat>(
                  c -> c.getMedicalSpecialist().getFirstName() + " " + c.getMedicalSpecialist()
                      .getLastName()
              )
          );
    }

    this.listBox.setValue(chats.get(0));
  }

  private com.vaadin.componentfactory.model.Message toChatMessage(final Message message) {
    return new com.vaadin.componentfactory.model.Message(message.getContent(), null,
        message.getSender().getFirstName() + " " + message.getSender().getLastName(),
        this.currentUser.getUser().getId().equals(message.getSender().getId()));
  }

  @Override
  public <C> C getComponent(final Class<C> type) {
    return type.cast(this);
  }
}
