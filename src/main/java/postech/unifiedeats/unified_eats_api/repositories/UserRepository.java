package postech.unifiedeats.unified_eats_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import postech.unifiedeats.unified_eats_api.entities.User;
import postech.unifiedeats.unified_eats_api.enums.UserType;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByLogin(String login);

    Optional<User> findByLoginAndPassword(String login, String password);

    List<User> findByNameContainingIgnoreCase(String name);
}
