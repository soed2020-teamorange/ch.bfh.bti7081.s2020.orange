package ch.bfh.bti7081.s2020.orange.backend.repository;

import ch.bfh.bti7081.s2020.orange.backend.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersonBaseRepository<T extends Person> extends CrudRepository<T, Long> {

}
