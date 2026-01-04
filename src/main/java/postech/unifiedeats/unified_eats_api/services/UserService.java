package postech.unifiedeats.unified_eats_api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import postech.unifiedeats.unified_eats_api.dtos.UserRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserResponseDTO;
import postech.unifiedeats.unified_eats_api.mappers.UserMapper;
import postech.unifiedeats.unified_eats_api.repositories.UserRepository;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserAlreadyExistsException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserNotFoundException;

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
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        return userMapper.toResponse(user);
    }

    public Long save(UserRequestDTO userRequestDTO) {
        validateEmail(userRequestDTO);
        validateLogin(userRequestDTO);

        var user = userRepository.save(userMapper.toEntity(userRequestDTO));

        return user.getId();
    }

    private void validateEmail(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new UserAlreadyExistsException("Email já cadastrado");
        }
    }

    private void validateLogin(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByLogin(userRequestDTO.login())) {
            throw new UserAlreadyExistsException("Login já cadastrado");
        }
    }
}
