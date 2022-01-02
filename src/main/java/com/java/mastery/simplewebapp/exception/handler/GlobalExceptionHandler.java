package com.java.mastery.simplewebapp.exception.handler;

import com.java.mastery.simplewebapp.exception.AppException;
import com.java.mastery.simplewebapp.exception.ErrorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorData> handleException(AppException exception) {
        ErrorData data = new ErrorData();
        data.setData(exception.getData());
        data.setMessage(exception.getMessage());
        log.error("exception, id = {}", exception.getData());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorData> handleException(Exception exception) {
        ErrorData data = new ErrorData();

        data.setMessage(exception.getMessage());
        log.error("Exception: " + data.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
