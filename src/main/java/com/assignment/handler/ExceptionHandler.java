package com.assignment.handler;


import lombok.Data;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleNotFoundDataException(final AccessDeniedException ex) {
        return ErrorMessage.create(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> handleNotFoundDataException(final BadCredentialsException ex) {
        return ErrorMessage.create(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolationException(
            final ConstraintViolationException ex) {
        return ErrorMessage.create(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> illegalArgument(final IllegalArgumentException ex) {
        return ErrorMessage.create(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> badRequest(final Exception ex) {
        return ErrorMessage.create(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    private static class ErrorMessage {
        private HttpStatus status;
        private String error;

        private static ResponseEntity<Object> create(final String error, HttpStatus status) {
            final ErrorMessage message = new ErrorMessage();
            message.status = status;
            message.error = error;
            return new ResponseEntity<>(message, new HttpHeaders(), status);
        }
    }
}
