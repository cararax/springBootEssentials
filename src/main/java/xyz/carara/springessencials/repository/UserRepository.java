package xyz.carara.springessencials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.carara.springessencials.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
