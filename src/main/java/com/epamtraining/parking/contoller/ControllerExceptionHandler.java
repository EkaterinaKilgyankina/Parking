package com.epamtraining.parking.contoller;

import com.epamtraining.parking.domain.exception.ApplicationException;
import com.epamtraining.parking.domain.exception.RestError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
//    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseEntity handle(ApplicationException e) {
//        return new RestError()
//                .setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new RestError()
                        .setMessage(e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handle(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
    }

}
