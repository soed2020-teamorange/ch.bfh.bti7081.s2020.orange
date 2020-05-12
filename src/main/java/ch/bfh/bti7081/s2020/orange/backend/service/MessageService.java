package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MessageState;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MessageRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

@Service
@RequiredArgsConstructor
@SessionScope
public class MessageService {

  private final UnicastProcessor<Message> publisher;
  private final Flux<Message> messages;
  private final MessageRepository messageRepository;
  private final ChatService chatService;

  // TODO save to repo
  public void addMessage(Long chatId, String content, User user) {
    Chat chat = chatService.getById(chatId);

    Message message = new Message(content, LocalDateTime.now(), MessageState.UNREAD, user, chat);

    publisher.onNext(messageRepository.save(message));
    System.out.println("Published and added new message");
  }

  public List<Message> loadInitialMessagesForChat(Long chatId) {
    return messageRepository.findAllByChatId(chatId);
  }

  // TODO filter by chatid
  public Flux<Message> getMessagesForChatId(Long chatId) {
    return messages;
  }
}
