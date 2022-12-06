package ru.gb.buv.spring_lesson10.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Делается, что бы приложение не падало при ошибке
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> catchResourceNotFoundException(ResourceNotFoundException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value()
                ,e.getMessage())
                ,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<FieldsValidatorError> catchValidatorException(ValidationException e){
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new FieldsValidatorError(e.getErrorFieldMessages())
                ,HttpStatus.BAD_REQUEST);
    }

}
