package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import application.project.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
