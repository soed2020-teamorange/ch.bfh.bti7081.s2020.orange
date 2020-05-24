package ch.bfh.bti7081.s2020.orange.backend.repositories;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface DiaryBaseRepository<T extends Diary> extends
    JpaRepository<T, Long> {

  T getByPatientId(Long patientId);
}
