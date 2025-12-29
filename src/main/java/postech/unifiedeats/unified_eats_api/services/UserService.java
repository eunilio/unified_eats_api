package postech.unifiedeats.unified_eats_api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postech.unifiedeats.unified_eats_api.entities.User;
import postech.unifiedeats.unified_eats_api.repositories.UserRepository;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserAlreadyExistsException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado")));
    }

    public void save(User user) {
        userRepository.findByEmail(user.getEmail()).ifPresent(u -> {
            throw new UserAlreadyExistsException("Email já cadastrado");
        });
        userRepository.save(user);
    }
}
