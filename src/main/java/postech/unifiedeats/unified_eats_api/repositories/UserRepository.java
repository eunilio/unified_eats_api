package postech.unifiedeats.unified_eats_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import postech.unifiedeats.unified_eats_api.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);

    List<User> findByNameContainingIgnoreCase(String name);
}
