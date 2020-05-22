package ch.bfh.bti7081.s2020.orange.backend.repositories;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.MoodDiary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodDiaryRepository extends JpaRepository<MoodDiary, Long> {

  MoodDiary getByPatientId(Long patientId);
}
