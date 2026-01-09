package postech.unifiedeats.unified_eats_api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import postech.unifiedeats.unified_eats_api.dtos.UserUpdateRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserResponseDTO;
import postech.unifiedeats.unified_eats_api.mappers.UserMapper;
import postech.unifiedeats.unified_eats_api.repositories.UserRepository;
import postech.unifiedeats.unified_eats_api.services.exceptions.InvalidParameterException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserAlreadyExistsException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserNotFoundException;

import java.util.List;

@Transactional
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

    public List<UserResponseDTO> findByName(String nome) {
        if (nome == null || nome.isEmpty())
            throw new InvalidParameterException("name", "Parâmetro 'name' é obrigatório");

        var user = userRepository.findByNameContainingIgnoreCase(nome);
        return userMapper.toResponseList(user);
    }

    public Long save(UserRequestDTO userRequestDTO) {
        validateEmail(userRequestDTO.email());
        validateLogin(userRequestDTO.login());

        var user = userRepository.save(userMapper.toEntity(userRequestDTO));

        return user.getId();
    }

    public void update(UserUpdateRequestDTO userUpdateRequestDTO, Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        if(!userUpdateRequestDTO.email().equals(user.getEmail()))
            validateEmail(userUpdateRequestDTO.email());

        if(!userUpdateRequestDTO.login().equals(user.getLogin()))
            validateLogin(userUpdateRequestDTO.login());

        userRepository.save(userMapper.toUpdateEntity(userUpdateRequestDTO, user));
    }


    private void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email já cadastrado");
        }
    }

    private void validateLogin(String login) {
        if (userRepository.existsByLogin(login)) {
            throw new UserAlreadyExistsException("Login já cadastrado");
        }
    }

    public void delete(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        userRepository.delete(user);
    }
}
