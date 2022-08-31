package com.start.drones.Trip;

import com.start.drones.Trip.Exceptions.TripNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public TripDTO getTripById(Long id) {

        Optional<Trip> trip = tripRepository.findById(id);
        if (trip.isEmpty()) {
            throw new TripNotFoundException("Trip with id " + id + " not found");
        }
        return new TripDTO(trip.get());
    }

    public List<TripDTO> index() {
        return tripRepository.findAll().stream().map(TripDTO::new).collect(java.util.stream.Collectors.toList());
    }
}
