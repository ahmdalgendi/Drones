package com.start.drones.Drone;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DroneDTO {
    @Size(max = 100)
    private String serialNumber;

    @Pattern(regexp = "Lightweight|Middleweight|Cruiserweight|Heavyweight", message = "Model must be one of the following: Lightweight, Middleweight, Cruiserweight, Heavyweight")
    private String model;
    @Pattern(regexp = "Idle|Flying|Loading|Unloading", message = "State must be one of the following: Idle, Flying, Loading, Unloading")
    private String droneState;
    @Min(value = 0, message = "Weight must be greater than 0")
    @Max(value = 100, message = "Battery capacity must be between 0 and 100")
    private double batteryPercentage;
    @Max(value = 500)
    private double maxWeight;

    private List<TripDTO> trips;

    public DroneDTO(Drone drone) {
        this.serialNumber = drone.getSerialNumber();
        this.model = drone.getModel().toString();
        this.droneState = drone.getDroneState().toString();
        this.batteryPercentage = drone.getBatteryPercentage();
        this.maxWeight = drone.getMaxWeight();
        this.trips = drone.getTrips().stream().map(TripDTO::new).collect(Collectors.toList());
    }
}
