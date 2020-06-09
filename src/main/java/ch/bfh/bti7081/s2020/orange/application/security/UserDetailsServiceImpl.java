package ch.bfh.bti7081.s2020.orange.application.security;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import ch.bfh.bti7081.s2020.orange.backend.repositories.UserRepository;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implements the {@link UserDetailsService}.
 * <p>
 * This implementation searches for {@link User} entities by the e-mail address supplied in the
 * login screen.
 */
@Service
@Primary
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  /**
   * Recovers the {@link User} from the database using the e-mail address supplied in the login
   * screen. If the user is found, returns a {@link org.springframework.security.core.userdetails.User}.
   *
   * @param username User's e-mail address
   */
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    final User user = this.userRepository.findByEmailIgnoreCase(username);
    if (null == user) {
      throw new UsernameNotFoundException("No user present with username: " + username);
    } else {
      return new org.springframework.security.core.userdetails.User(user.getEmail(),
          user.getPasswordHash(),
          Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
  }
}
