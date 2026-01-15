    package postech.unifiedeats.unified_eats_api.services;

    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;
    import postech.unifiedeats.unified_eats_api.dtos.ChangePasswordDTO;
    import postech.unifiedeats.unified_eats_api.dtos.LoginRequestDTO;
    import postech.unifiedeats.unified_eats_api.dtos.LoginResponseDTO;
    import postech.unifiedeats.unified_eats_api.entities.User;
    import postech.unifiedeats.unified_eats_api.repositories.UserRepository;
    import postech.unifiedeats.unified_eats_api.services.exceptions.InvalidCredentialsException;
    import postech.unifiedeats.unified_eats_api.services.exceptions.UserNotFoundException;

    import java.time.LocalDateTime;

    @RequiredArgsConstructor
    @Service
    public class AuthService {

        private final UserRepository userRepository;

        public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
            var user = userRepository.findByLogin(loginRequestDTO.login()).orElseThrow(() -> new InvalidCredentialsException("Login ou senha inválidos"));

            if(!user.getPassword().equals(loginRequestDTO.password())) {
                throw new InvalidCredentialsException("Login ou senha inválidos");
            }

            return new LoginResponseDTO(user.getName(), user.getType());
        }

        @Transactional
        public void changePassword(Long id, ChangePasswordDTO changePasswordDTO) {
            var user = getUserOrThrow(id);

            if (!user.getPassword().equals(changePasswordDTO.oldPassword())){
                throw new InvalidCredentialsException("Senha atual inválida");
            }

            user.setPassword(changePasswordDTO.newPassword());
            user.setLastUpdated(LocalDateTime.now());
        }

        private User getUserOrThrow(Long id) {
            return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        }
    }
