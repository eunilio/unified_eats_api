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
import postech.unifiedeats.unified_eats_api.dtos.UserUpdateRequestDTO;
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
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        log.info("GET -> /v1/users");
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUserById(@PathVariable("id") Long id) {
        log.info("GET -> /v1/users/{}", id);
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponseDTO>> findUserByName(@RequestParam("name") String name) {
        log.info("GET /v1/users/search?name={}", name);
        var users = userService.findByName(name);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info(" POST -> /v1/users");
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

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateRequestDTO userUpdateRequestDTO) {
        log.info("PUT -> /v1/users/{}", id);
        userService.update(userUpdateRequestDTO, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        log.info("DELETE /v1/users/" + id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
