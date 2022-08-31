package com.start.drones.Drone;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class DroneDTO {
    @Size(max = 100)
    private String serialNumber;

     @Pattern(regexp = "Lightweight|Middleweight|Cruiserweight|Heavyweight" , message = "Model must be one of the following: Lightweight, Middleweight, Cruiserweight, Heavyweight")
    private String model;

    private State state;
    @Min(value = 0 , message = "Weight must be greater than 0")
    @Max(value = 100 , message = "Battery capacity must be between 0 and 100")
    private float batteryPercentage;
    @Max(value = 500)
    private double maxWeight;

}
