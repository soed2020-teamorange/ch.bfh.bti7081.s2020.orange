package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.MessageState;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MessageRepository;
import java.time.LocalDateTime;
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
  private final MessageRepository messageRepository;
  private final Flux<Message> messages;
  private final ChatService chatService;

  public void addMessage(Long chatId, String content, User user) {
    Chat chat = chatService.getById(chatId);

    Message message = new Message(content, LocalDateTime.now(), MessageState.UNREAD, user, chat);

    publisher.onNext(messageRepository.save(message));
  }

  public Flux<Message> getMessages() {
    return messages;
  }
}
