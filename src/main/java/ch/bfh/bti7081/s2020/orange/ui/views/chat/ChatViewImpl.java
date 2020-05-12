package ch.bfh.bti7081.s2020.orange.ui.views.chat;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import com.vaadin.componentfactory.Chat;
import com.vaadin.componentfactory.Chat.ChatNewMessageEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.spring.annotation.UIScope;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@UIScope
@Component
@RequiredArgsConstructor
public class ChatViewImpl extends Div implements ChatView {

  @Setter
  private ChatView.Observer observer;

  private final CurrentUser currentUser;

  Chat chat = new Chat();

  @PostConstruct
  public void init() {
    this.setWidth("100%");
    // somehow loading indicator is shown, even if we setLoading(false)
    chat.setLoadingIndicator(new Div());
    chat.addChatNewMessageListener(this::onAddNewMessage);
    add(chat);
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
