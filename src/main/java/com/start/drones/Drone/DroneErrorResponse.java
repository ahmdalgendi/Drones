package com.start.drones.Drone;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
@AllArgsConstructor
@Setter
@Getter
public class DroneErrorResponse {
    private String message;
    private int statusCode;
    private String path;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime timestamp;

    //constructor
}
