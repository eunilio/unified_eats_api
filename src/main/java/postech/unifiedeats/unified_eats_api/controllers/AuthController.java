package postech.unifiedeats.unified_eats_api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import postech.unifiedeats.unified_eats_api.dtos.LoginRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.LoginResponseDTO;
import postech.unifiedeats.unified_eats_api.services.AuthService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        log.info(" POST -> /v1/auth/login");
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
}
