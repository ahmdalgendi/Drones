package com.start.drones.Trip.Exceptions;

import java.time.ZonedDateTime;

public class TripNotFoundException extends RuntimeException {
    public TripNotFoundException(String s) {
        super(s);
    }
}
