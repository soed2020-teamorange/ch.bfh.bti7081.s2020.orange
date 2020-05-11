package ch.bfh.bti7081.s2020.orange.backend.repository;

import ch.bfh.bti7081.s2020.orange.backend.model.Person;
import javax.transaction.Transactional;

@Transactional
public interface PersonRepository extends PersonBaseRepository<Person> {

}
