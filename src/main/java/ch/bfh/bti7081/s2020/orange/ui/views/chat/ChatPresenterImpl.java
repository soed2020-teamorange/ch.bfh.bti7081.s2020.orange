package ch.bfh.bti7081.s2020.orange.ui.views.chat;

import ch.bfh.bti7081.s2020.orange.application.security.CurrentUser;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MessageRepository;
import ch.bfh.bti7081.s2020.orange.backend.service.ChatService;
import ch.bfh.bti7081.s2020.orange.backend.service.MessageService;
import ch.bfh.bti7081.s2020.orange.ui.utils.View;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class ChatPresenterImpl implements ChatPresenter, ChatView.Observer {

  private final ChatView view;
  private final ChatService chatService;
  private final MessageService messageService;
  private final MessageRepository messageRepository;
  private final CurrentUser user;
  private Long chatId;

  @PostConstruct
  public void init() {
    this.messageService.getMessages().subscribe(this::addMessageToView);
  }

  private void addMessageToView(final Message message) {
    this.view.addMessage(message);
  }

  @Override
  public View getView() {
    return this.view;
  }

  @Override
  public void onAddMessage(final String content) {
    this.messageService.addMessage(chatId, content, this.user.getUser());
  }

  @Override
  public void onBeforeEnter() {
    this.view.setObserver(this);
    this.view.setChats(this.chatService.getChatsByUserId(this.user.getUser().getId()));
  }

  @Override
  public void onLoadChat(final long chatId) {
    this.chatId = chatId;
    for (final Message message : this.messageRepository.findAllByChatId(chatId)) {
      this.view.addMessage(message);
    }
  }
}
