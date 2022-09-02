package com.start.drones.Drone;

import com.start.drones.Trip.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "drones")
public class Drone {
    final static double MIN_BATTERY_FOR_LOADING = 25;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(max = 100)
    private String serialNumber;
    private Model model;

    private DroneState droneState;

    private double batteryPercentage;

    private double maxWeight;

    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL)
    private List<Trip> trips;

    public Drone(DroneDTO drone) {
        this.serialNumber = drone.getSerialNumber();
        this.model = Model.valueOf(drone.getModel());
        this.droneState = DroneState.valueOf(drone.getDroneState());
        this.batteryPercentage = drone.getBatteryPercentage();
        this.maxWeight = drone.getMaxWeight();
    }

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", state=" + droneState +
                ", batteryPercentage=" + batteryPercentage +
                ", maxWeight=" + maxWeight +
                '}';
    }
}
