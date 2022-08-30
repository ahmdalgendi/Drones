package com.start.drones.Drone;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@ControllerAdvice
public class DroneExceptionHandler {
    @ExceptionHandler
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
