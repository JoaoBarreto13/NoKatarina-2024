package BaixaQuality.NoKatarina2024.adapters.in;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import domain.exception.BaixaQuality.NoKatarina2024.domain.exception.ChampionNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler (ChampionNotFoundException.class)
    public ResponseEntity<ApiError> handleDomainException(ChampionNotFoundException domainError) {
        return ResponseEntity
        .unprocessableEntity()
        .body(new ApiError(domainError.getMessage()));


    }
    
    @ExceptionHandler (Exception.class)
    public ResponseEntity<ApiError> handleDomainException(Exception unexpectedError) {
        String message = "Ocorreu um erro entre contato comigo";
        logger.error(message, unexpectedError);
        return ResponseEntity
                .internalServerError()
                .body(new ApiError(message));


    }
    public record ApiError(String message) { }
}