package com.start.drones.Trip;

import com.start.drones.Trip.DTOs.TripDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    final TripService tripService;

    TripController(TripService tripService) {
        this.tripService = tripService;
    }
    @GetMapping
    public List<TripDTO>index(){
        return tripService.index();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public TripDTO getTripById(@PathVariable Long id) {
        return tripService.getTripById(id);
    }
}
