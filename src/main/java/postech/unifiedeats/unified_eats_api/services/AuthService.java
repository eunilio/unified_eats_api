package postech.unifiedeats.unified_eats_api.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import postech.unifiedeats.unified_eats_api.dtos.ChangePasswordDTO;
import postech.unifiedeats.unified_eats_api.dtos.LoginRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.LoginResponseDTO;
import postech.unifiedeats.unified_eats_api.repositories.UserRepository;
import postech.unifiedeats.unified_eats_api.services.exceptions.InvalidCredentialsException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserNotFoundException;

import java.time.LocalDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var user = userRepository.findByLoginAndPassword(loginRequestDTO.login(), loginRequestDTO.password()).orElseThrow(() -> new InvalidCredentialsException("Login ou senha inválidos"));

        return new LoginResponseDTO(user.getName(), user.getType());
    }

    public void changePassword(Long id, ChangePasswordDTO changePasswordDTO) {
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

        if (!user.getPassword().equals(changePasswordDTO.oldPassword())){
            throw new InvalidCredentialsException("Senha atual inválida");
        }

        user.setPassword(changePasswordDTO.newPassword());
        user.setLastUpdated(LocalDateTime.now());
    }
}
