package ch.bfh.bti7081.s2020.orange.ui.views.chat;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import com.vaadin.componentfactory.Chat.ChatNewMessageEvent;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.data.renderer.TextRenderer;
import com.vaadin.flow.spring.annotation.UIScope;
import java.util.ArrayList;
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

  Div div = new Div();
  com.vaadin.componentfactory.Chat chat = new com.vaadin.componentfactory.Chat();
  VerticalLayout verticalLayout = new VerticalLayout();
  ListBox<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat> listBox = new ListBox<>();

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
      chat.setMessages(new ArrayList<>());
      observer.onLoadChat(item.getValue().getId());
    });

    addToPrimary(verticalLayout);
    addToSecondary(chat);
    verticalLayout.add(listBox);
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
    //TODO: inject current User (Patient or MedicalSpecialist) in ChatViewImpl
    listBox.setRenderer(new TextRenderer<ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat>(
        c -> c.getId().toString()));
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
