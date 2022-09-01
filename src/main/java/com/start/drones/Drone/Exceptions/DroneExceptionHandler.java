package com.start.drones.Drone.Exceptions;

import com.start.drones.Drone.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class DroneExceptionHandler extends ResponseEntityExceptionHandler {
    public ResponseEntity<DroneErrorResponse> droneNotFoundHandler(
            DroneNotFoundException exception,
            HttpServletRequest req) {
        DroneErrorResponse error = new DroneErrorResponse(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                req.getRequestURI(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, "validation error", errors);
        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }

    @ExceptionHandler
    public ResponseEntity<DroneErrorResponse> droneNotReadableException(
            RuntimeException exception,
            HttpServletRequest req) {
        DroneErrorResponse error = new DroneErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                req.getRequestURI(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler //for all other exceptions
    public ResponseEntity<DroneErrorResponse> droneException(
            Exception exception,
            HttpServletRequest req) {
        DroneErrorResponse error = new DroneErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                req.getRequestURI(),
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
