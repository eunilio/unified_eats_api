package postech.unifiedeats.unified_eats_api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import postech.unifiedeats.unified_eats_api.dtos.UserUpdateRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserResponseDTO;
import postech.unifiedeats.unified_eats_api.entities.User;
import postech.unifiedeats.unified_eats_api.mappers.UserMapper;
import postech.unifiedeats.unified_eats_api.repositories.UserRepository;
import postech.unifiedeats.unified_eats_api.services.exceptions.InvalidParameterException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserAlreadyExistsException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDTO> findAll() {
        var user = userRepository.findAll();

        return userMapper.toResponseList(user);
    }

    public UserResponseDTO findById(Long id) {
        var user = getUserOrThrow(id);

        return userMapper.toResponse(user);
    }

    public List<UserResponseDTO> findByName(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new InvalidParameterException("name", "Parâmetro 'name' é obrigatório");

        var user = userRepository.findByNameContainingIgnoreCase(nome);
        return userMapper.toResponseList(user);
    }

    @Transactional
    public Long save(UserRequestDTO userRequestDTO) {
        ensureUniqueEmail(userRequestDTO.email());
        ensureLoginIsUnique(userRequestDTO.login());

        var user = userRepository.save(userMapper.toEntity(userRequestDTO, LocalDateTime.now()));

        return user.getId();
    }

    @Transactional
    public void update(UserUpdateRequestDTO userUpdateRequestDTO, Long id) {
        var user = getUserOrThrow(id);

        if(!userUpdateRequestDTO.email().equals(user.getEmail()))
            ensureUniqueEmail(userUpdateRequestDTO.email());

        if(!userUpdateRequestDTO.login().equals(user.getLogin()))
            ensureLoginIsUnique(userUpdateRequestDTO.login());

        userMapper.toUpdateEntity(userUpdateRequestDTO, user, LocalDateTime.now());
    }

    @Transactional
    public void delete(Long id) {
        var user = getUserOrThrow(id);
        userRepository.delete(user);
    }

    private void ensureUniqueEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email já cadastrado");
        }
    }

    private void ensureLoginIsUnique(String login) {
        if (userRepository.existsByLogin(login)) {
            throw new UserAlreadyExistsException("Login já cadastrado");
        }
    }

    private User getUserOrThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }
}
