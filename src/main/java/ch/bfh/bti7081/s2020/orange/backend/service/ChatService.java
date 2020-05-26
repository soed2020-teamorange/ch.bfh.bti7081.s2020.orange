package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat;
import ch.bfh.bti7081.s2020.orange.backend.repositories.ChatRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

  private final ChatRepository chatRepository;

  public Chat getById(Long chatId) {
    return chatRepository.findById(chatId).get();
  }

  public List<Chat> getChatsByUserId(long userId) {
    return chatRepository.findByMedicalSpecialistIdOrPatientId(userId, userId);
  }
}
