package postech.unifiedeats.unified_eats_api.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import postech.unifiedeats.unified_eats_api.entities.User;
import postech.unifiedeats.unified_eats_api.services.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserContoller {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        log.info("/v1/users");
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable("id") Long id) {
        log.info("/v1/users/{}", id);
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        log.info(" POST -> /users");
        userService.save(user);
        return ResponseEntity.status(201).build();
    }
}
