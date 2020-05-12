package ch.bfh.bti7081.s2020.orange.backend.repositories;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {

  List<Message> findAllByChatId(Long chatId);
}
