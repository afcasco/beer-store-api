package dev.afcasco.spring6restmvc.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class CustomErrorController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity handleBindErrors(MethodArgumentNotValidException exception){

        /*var errorList = exception.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                })
                .toList();*/
        var errorList = exception.getFieldErrors().stream()
                .map(fieldError -> Map.of(fieldError.getField(), Objects.requireNonNull(fieldError.getDefaultMessage())))
                .toList();
        return ResponseEntity.badRequest().body(errorList);
    }

    @ExceptionHandler(TransactionSystemException.class)
    ResponseEntity handleJPAViolations(TransactionSystemException exception){
        ResponseEntity.BodyBuilder responseEntity = ResponseEntity.badRequest();

        if(exception.getCause().getCause() instanceof ConstraintViolationException ve) {

            var errors = ve.getConstraintViolations().stream()
                    .map(constraintViolation -> Map.of(constraintViolation.getPropertyPath().toString(),
                            constraintViolation.getMessage()))
                    .toList();
            return responseEntity.body(errors);
        }

        return responseEntity.build();

    }
}
