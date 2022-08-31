package com.start.drones.Drone;

import com.start.drones.Trip.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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


    public TripDTO(Trip trip) {
        this.id = trip.getId();
        this.timeToDeliver = trip.getTimeToDeliver();
        this.droneId = trip.getDrone().getId();
        this.loadedAt = trip.getLoadedAt();
        this.deliveredAt = trip.getDeliveredAt();
    }
}
