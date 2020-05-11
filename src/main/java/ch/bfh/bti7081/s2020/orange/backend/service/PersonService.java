package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.model.Person;
import ch.bfh.bti7081.s2020.orange.backend.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;

  public Person getPersonById(long id) {
    return this.personRepository.findById(id).get();
  }
}
