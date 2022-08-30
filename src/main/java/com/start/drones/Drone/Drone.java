package com.start.drones.Drone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "drones")
public class Drone {
//    - serial number (100 characters max);
//- model (Lightweight, Middleweight, Cruiserweight, Heavyweight); - weight limit (500gr max);
//- battery capacity (percentage);
//- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Size(max = 100)
    private String serialNumber;
    private Model model;

    private State state;
    @Min(value = 0 , message = "Weight must be greater than 0")
    @Max(value = 100 , message = "Battery capacity must be between 0 and 100")
    private float batteryPercentage;
    @Max(value = 500)
    private double maxWeight;

    @Override
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", model=" + model +
                ", state=" + state +
                ", batteryPercentage=" + batteryPercentage +
                ", maxWeight=" + maxWeight +
                '}';
    }
}
