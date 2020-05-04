package ch.bfh.bti7081.s2020.orange.backend.repositories;

import ch.bfh.bti7081.s2020.orange.backend.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
