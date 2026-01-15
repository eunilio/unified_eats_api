package postech.unifiedeats.unified_eats_api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import postech.unifiedeats.unified_eats_api.dtos.ChangePasswordDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserResponseDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserUpdateRequestDTO;
import postech.unifiedeats.unified_eats_api.services.AuthService;
import postech.unifiedeats.unified_eats_api.services.UserService;

import java.net.URI;
import java.util.List;

import static postech.unifiedeats.unified_eats_api.docs.ApiExamples.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
@Tag(name = "Users", description = "Operações relacionadas a usuários")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Operation(
            summary = "Listar usuários",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Lista de usuários retornada com sucesso"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        log.info("GET -> /v1/users");
        return ResponseEntity.ok(userService.findAll());
    }

    @Operation(
            summary = "Buscar usuário por ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuário encontrado e retornado com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_USER_NOT_FOUND)
                            )
                    )
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable("id") Long id) {
        log.info("GET -> /v1/users/{}", id);
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Buscar usuários por nome",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Busca realizada com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Parâmetro 'name' ausente",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_INVALID_PARAMETER)
                            )
                    )
            }
    )
    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> findUserByName(@Parameter(
            description = "Nome (ou parte do nome) para busca",
            example = "joão",
            required = true
    ) @RequestParam("name") String name) {
        log.info("GET /v1/users/search?name={}", name);
        var users = userService.findByName(name);
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Cadastrar usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = USER_CREATE_REQUEST)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário criado"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Erro de validação",
                            content = @Content(examples = @ExampleObject(value = PROBLEM_VALIDATION_ERROR))
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Conflito",
                            content = @Content(examples = @ExampleObject(value = PROBLEM_CONFLICT))
                    )
            }
    )
    @PostMapping
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("POST -> /v1/users");
        long id = userService.save(userRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(
            summary = "Atualizar usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = USER_UPDATE_REQUEST)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso"),

                    @ApiResponse(
                            responseCode = "400",
                            description = "Dados inválidos na requisição",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_VALIDATION_ERROR)
                            )
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_USER_NOT_FOUND)
                            )
                    ),

                    @ApiResponse(
                            responseCode = "409",
                            description = "Email ou login já cadastrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_CONFLICT)
                            )
                    )
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        log.info("PUT -> /v1/users/{}", id);
        userService.update(userUpdateRequestDTO, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Alterar senha do usuário",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = CHANGE_PASSWORD_REQUEST)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Senha alterada com sucesso"
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
                            description = "Senha atual inválida",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_INVALID_PASSWORD)
                            )
                    ),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_USER_NOT_FOUND)
                            )
                    )
            }
    )
    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> password(@PathVariable("id") Long id, @Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        log.info("PATCH -> /v1/users/{}/password", id);
        authService.changePassword(id, changePasswordDTO);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Remover usuário",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Usuário removido com sucesso"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Usuário não encontrado",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = PROBLEM_USER_NOT_FOUND)
                            )
                    )
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        log.info("DELETE -> /v1/users/{}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
