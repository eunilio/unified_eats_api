package postech.unifiedeats.unified_eats_api.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import postech.unifiedeats.unified_eats_api.dtos.ChangePasswordDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserRequestDTO;
import postech.unifiedeats.unified_eats_api.dtos.UserResponseDTO;
import postech.unifiedeats.unified_eats_api.services.AuthService;
import postech.unifiedeats.unified_eats_api.services.UserService;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public List<UserResponseDTO> findAll() {
        log.info("GET -> /v1/users");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable("id") Long id) {
        log.info("GET -> /v1/users/{}", id);
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info(" POST -> /users");
        long id = userService.save(userRequestDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> password(@PathVariable("id") Long id, @Valid @RequestBody ChangePasswordDTO changePasswordDTO) {
        log.info("PATCH -> /v1/users/{}/password", id);
        authService.changePassword(id, changePasswordDTO);
        return ResponseEntity.noContent().build();
    }
}
