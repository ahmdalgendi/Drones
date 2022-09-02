package com.start.drones.Drone;

import com.start.drones.Drone.DTOs.TripDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoadDroneRequest {
    private TripDTO trip;
    private List<Long> med;
}
