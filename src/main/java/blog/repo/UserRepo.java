package blog.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import blog.model.*;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);


}
