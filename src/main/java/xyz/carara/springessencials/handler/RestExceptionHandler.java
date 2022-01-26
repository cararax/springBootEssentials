package xyz.carara.springessencials.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import xyz.carara.springessencials.exception.BadRequestException;
import xyz.carara.springessencials.exception.BadRequestExceptionDetails;
import xyz.carara.springessencials.exception.ValidationExceptionDetails;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerDadRequestException(BadRequestException badRequestException) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Request Excetion: Check the documentation.")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException validException) {
//        log.info("fields{}", validException.getBindingResult().getFieldError().getField());
        List<FieldError> fieldErrorList = validException.getBindingResult().getFieldErrors();
        String fieldsError = fieldErrorList.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrorList.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request Excetion: Invalid field(s).")
                .details("Check the field(s) error(s)")
                .developerMessage(validException.getClass().getName())
                .fields(fieldsError)
                .fieldsMessage(fieldsMessage).build(), HttpStatus.BAD_REQUEST);


    }
}
