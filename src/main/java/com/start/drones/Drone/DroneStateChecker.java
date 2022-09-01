package com.start.drones.Drone;

import com.start.drones.Trip.Trip;
import com.start.drones.Trip.TripRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DroneStateChecker {
    final Logger log = Logger.getLogger(DroneStateChecker.class.getName());
    final DroneRepository droneRepository;
    final TripRepository tripRepository;

    public DroneStateChecker(DroneRepository droneRepository, TripRepository tripRepository) {
        this.droneRepository = droneRepository;
        this.tripRepository = tripRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void checkDroneStates() throws InterruptedException {
        log.info("Checking drone states");
        Thread.sleep(1000);
        System.out.println("Checking drone states");
        Drone drone = droneRepository.findById(1L).get();

        Optional<Trip> trip = tripRepository.findByDroneOrderByDeliveredAtDesc(drone);
        if (trip.isPresent()) {
            System.out.println("Trip present");
            System.out.println(trip.get());
        }
        else {
            System.out.println("Trip not present");
        }
    }
}
