package com.start.drones.Drone;

public class DroneNotFoundException extends RuntimeException {

    public DroneNotFoundException() {
        super();
    }

    public DroneNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DroneNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DroneNotFoundException(String message) {
        super(message);
    }

    public DroneNotFoundException(Throwable cause) {
        super(cause);
    }
}
