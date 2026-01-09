package postech.unifiedeats.unified_eats_api.controllers.handlers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import postech.unifiedeats.unified_eats_api.services.exceptions.InvalidCredentialsException;
import postech.unifiedeats.unified_eats_api.services.exceptions.InvalidParameterException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserAlreadyExistsException;
import postech.unifiedeats.unified_eats_api.services.exceptions.UserNotFoundException;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleUserNotFoundException(UserNotFoundException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());

        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setType(URI.create("urn:problem:user-not-found"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ProblemDetail handleUserAlreadyExistsException(UserAlreadyExistsException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());

        problemDetail.setTitle("Conflito");
        problemDetail.setType(URI.create("urn:problem:conflict"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ProblemDetail invalidCredentialsException(InvalidCredentialsException e, HttpServletRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.getMessage());

        problemDetail.setTitle("Não autorizado");
        problemDetail.setType(URI.create("urn:problem:unauthorized"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Campos inválidos na requisição");

        problemDetail.setTitle("Erro de validação");
        problemDetail.setType(URI.create("urn:problem:validation-error"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        Map<String, List<String>> errors = new HashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors
                    .computeIfAbsent(fieldError.getField(), key -> new ArrayList<>())
                    .add(fieldError.getDefaultMessage());
        }

        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ProblemDetail handleInvalidCredentialsException(InvalidParameterException e, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Parâmetro inválido");

        problemDetail.setTitle("Erro de validação");
        problemDetail.setType(URI.create("urn:problem:validation-error"));
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        Map<String, List<String>> errors = new HashMap<>();
        errors.put(e.getParamName(), List.of(e.getMessage()));

        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }
}
