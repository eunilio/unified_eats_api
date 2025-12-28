package postech.unifiedeats.unified_eats_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import postech.unifiedeats.unified_eats_api.entities.User;
import postech.unifiedeats.unified_eats_api.enums.UserType;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Checks for unique email
    Optional<User> findByEmail(String email);

    // Login validation (login and password)
    Optional<User> findByLoginAndPassword(String login, String password);

    // Search users by name (case-insensitive, partial match)
    List<User> findByNameContainingIgnoreCase(String name);

    // Filter users by type
    List<User> findByType(UserType type);
}
