package ch.bfh.bti7081.s2020.orange.backend.repositories;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Chat;
import ch.bfh.bti7081.s2020.orange.backend.data.entities.MedicalSpecialist;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {

  List<Chat> findByMedicalSpecialist(MedicalSpecialist ms);

}
