package com.exemplo.estudos_java.application.handlers;

import com.exemplo.estudos_java.application.dtos.RestErrorDto;
import com.exemplo.estudos_java.application.exceptions.ForbiddenException;
import com.exemplo.estudos_java.application.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<RestErrorDto> notFoundErrorHandler(NotFoundException exception) {
        var response = new RestErrorDto(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ForbiddenException.class)
    private ResponseEntity<RestErrorDto> internalErrorHandler(ForbiddenException exception) {
        var response = new RestErrorDto(HttpStatus.FORBIDDEN, exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

//    @ExceptionHandler(Exception.class)
//    private ResponseEntity<RestErrorDto> internalErrorHandler(Exception exception) {
//        var response = new RestErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }
}
