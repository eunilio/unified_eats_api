package postech.unifiedeats.unified_eats_api.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import postech.unifiedeats.unified_eats_api.dtos.UserErrorDTO;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserAlreadyExistsException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorDTO> handleUserNotFoundException(UserNotFoundException e) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new UserErrorDTO(e.getMessage(), status.value()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<UserErrorDTO> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        var status = HttpStatus.CONFLICT;
        return ResponseEntity.status(status.value()).body(new UserErrorDTO(e.getMessage(), status.value()));
    }
}
