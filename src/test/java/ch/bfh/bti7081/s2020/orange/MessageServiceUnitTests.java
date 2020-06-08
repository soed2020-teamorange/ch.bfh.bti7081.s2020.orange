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
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import reactor.test.StepVerifier;

public class MessageServiceUnitTests {

  MessageRepository messageRepository;
  ChatService chatService;
  UnicastProcessor<Message> publisher;
  Flux<Message> messages;

  MessageService messageService;

 /* @Before
  public void before() {
    messageRepository = mock(MessageRepository.class);
    chatService = mock(ChatService.class);
    publisher = UnicastProcessor.create();
    messages = publisher.replay(0).autoConnect();

    messageService = new MessageService(publisher, messages, messageRepository,
        chatService);
  }*/


 /* @Test
  public void messageServiceShouldPublishAddedMessage() {
    when(chatService.getById(1L)).thenReturn(new Chat());
    when(messageRepository.save(Mockito.any(Message.class))).then(returnsFirstArg());

    String expectedMessageContent = "Test Message";

    Flux<Message> messageFlux = messageService.getMessagesForChatId(1L);
    messageService.addMessage(1L, expectedMessageContent, new User());

    publisher.onComplete();

    StepVerifier.create(messageFlux)
        .consumeNextWith(message -> assertEquals(expectedMessageContent, message.getContent()))
        .verifyComplete();
  }*/
}
