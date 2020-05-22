package ch.bfh.bti7081.s2020.orange.backend.service;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.backend.repositories.UserRepository;
import ch.bfh.bti7081.s2020.orange.ui.exceptions.UserAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public boolean emailIsUnique(String email) throws UserAlreadyExistsException {
      if (userRepository.existsByEmail(email)) {
        throw new UserAlreadyExistsException(email);
      }
      return true;
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

}
