package ch.bfh.bti7081.s2020.orange;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.backend.repositories.MessageRepository;
import ch.bfh.bti7081.s2020.orange.backend.service.ChatService;
import ch.bfh.bti7081.s2020.orange.backend.service.MessageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import reactor.test.StepVerifier;

public class MessageServiceUnitTests {

  MessageRepository messageRepository;
  ChatService chatService;
  UnicastProcessor<Message> publisher;
  Flux<Message> messages;

  MessageService messageService;

  @Before
  public void before() {
    this.messageRepository = mock(MessageRepository.class);
    this.chatService = mock(ChatService.class);
    this.publisher = UnicastProcessor.create();
    this.messages = this.publisher.replay(0).autoConnect();

    this.messageService = new MessageService(this.publisher, this.messageRepository, this.messages,
        this.chatService);
  }


  @Test
  public void messageServiceShouldPublishAddedMessage() {
    when(this.chatService.getById(1L)).thenReturn(new Chat());
    when(this.messageRepository.save(ArgumentMatchers.any(Message.class))).then(returnsFirstArg());

    final String expectedMessageContent = "Test Message";

    final Flux<Message> messageFlux = this.messageService.getMessages();
    this.messageService.addMessage(1L, expectedMessageContent, new User());

    this.publisher.onComplete();

    StepVerifier.create(messageFlux)
        .consumeNextWith(message -> assertEquals(expectedMessageContent, message.getContent()))
        .verifyComplete();
  }
}
