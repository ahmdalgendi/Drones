package com.start.drones.Drone.DTOs;


import com.start.drones.Drone.Drone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DroneDTO {
    @Size(max = 100)
    private String serialNumber;

    @Pattern(regexp = "Lightweight|Middleweight|Cruiserweight|Heavyweight", message = "Model must be one of the following: Lightweight, Middleweight, Cruiserweight, Heavyweight")
    private String model;
    //pattert for enum ignore case sensitive
    @Pattern(regexp = "IDLE|LOADING|LOADED|UNLOADING", message = "DroneState must be one of the following: IDLE, LOADING, LOADED, UNLOADING")
    private String droneState;
    @Min(value = 0, message = "Weight must be greater than 0")
    @Max(value = 100, message = "Battery capacity must be between 0 and 100")
    private double batteryPercentage;
    @Max(value = 500 , message = "Payload capacity must be between 0 and 500")
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

    public DroneDTO(String serialNumber, String model, String droneState, double batteryPercentage, double maxWeight) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.droneState = droneState;
        this.batteryPercentage = batteryPercentage;
        this.maxWeight = maxWeight;
    }
}
