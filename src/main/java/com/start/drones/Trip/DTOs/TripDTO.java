package com.start.drones.Trip.DTOs;

import com.start.drones.Drone.Drone;
import com.start.drones.Medication.Medication;
import com.start.drones.Trip.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {
    private Long id;
    private int timeToDeliver;
    private Long droneId;
    LocalDateTime loadedAt;
    LocalDateTime deliveredAt;

    Drone drone;
    List<Medication> medications = new ArrayList<>();


    public TripDTO(Trip trip) {
        this.id = trip.getId();
        this.timeToDeliver = trip.getTimeToDeliver();
        this.droneId = trip.getDrone().getId();
        this.loadedAt = trip.getLoadedAt();
        this.deliveredAt = trip.getDeliveredAt();
    }
}
