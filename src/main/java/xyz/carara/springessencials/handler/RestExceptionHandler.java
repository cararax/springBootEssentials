package xyz.carara.springessencials.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.carara.springessencials.exception.BadRequestException;
import xyz.carara.springessencials.exception.BadRequestExceptionDetails;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> badRequestException(BadRequestException badRequestException){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Excetion: Check the documentation.")
                        .details(badRequestException.getMessage())
                        .developerMEssage(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
