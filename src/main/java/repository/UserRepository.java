package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
