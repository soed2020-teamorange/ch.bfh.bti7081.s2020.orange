package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.backend.repositories.UserRepository;
import ch.bfh.bti7081.s2020.orange.ui.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public boolean emailIsUnique(final String email) throws UserAlreadyExistsException {
    if (this.userRepository.existsByEmail(email)) {
      throw new UserAlreadyExistsException(email);
    }
    return true;
  }

  public User saveUser(final User user) {
    return this.userRepository.save(user);
  }

}
