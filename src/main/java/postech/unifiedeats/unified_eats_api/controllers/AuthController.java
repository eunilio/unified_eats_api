package postech.unifiedeats.unified_eats_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static postech.unifiedeats.unified_eats_api.docs.ApiExamples.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Auth", description = "Operações de autenticação")
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Realizar login",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = LOGIN_REQUEST)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Login realizado com sucesso",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoginResponseDTO.class),
                                    examples = @ExampleObject(value = LOGIN_SUCCESS_RESPONSE)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos na requisição",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_VALIDATION_ERROR)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Login ou senha inválidos",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_UNAUTHORIZED)
                            )
                    )
            }
    )
    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        log.info("POST -> /v1/auth/login");
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }
}
