package blog.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import blog.model.*;

@Repository 
public interface PostRepo extends JpaRepository<Post, Long> {
  List<Post> findByUsername(String username);
}
