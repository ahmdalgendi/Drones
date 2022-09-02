package com.start.drones.Drone;

import com.start.drones.Trip.Trip;
import com.start.drones.Trip.TripRepository;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Log4j2
public class DroneStateChecker {
    //    final Logger log = Logger.getLogger(DroneStateChecker.class.getName());
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

        //run async task for each drone
        List<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones) {
            log.info("Checking drone " + drone.getId());
            updateDroneState(drone);
        }
    }

    @Async
    void updateDroneState(Drone drone) throws InterruptedException {
        Optional<Trip> trip = tripRepository.findByDroneOrderByDeliveredAtDesc(drone);
        if (trip.isPresent() && drone.getDroneState() != DroneState.IDLE) {
            if (drone.getDroneState() == DroneState.DELIVERING) {
                handleDelivering(drone, trip.get());
            } else if (drone.getDroneState() == DroneState.RETURNING) {
                handleReturning(drone, trip.get());
            }

        } else {
            drone.setDroneState(DroneState.IDLE);
            log.info("Drone " + drone.getId() + " is idle");
            if (drone.getBatteryPercentage() < 100) {
                drone.setBatteryPercentage(drone.getBatteryPercentage() + 1);
            }
            log.info("Battery percentage of drone " + drone.getId() + " is " + drone.getBatteryPercentage());
        }
        droneRepository.save(drone);
    }

    private void handleReturning(Drone drone, Trip trip) {
        LocalDateTime now = LocalDateTime.now();
        if (trip.getDeliveredAt().plusSeconds(trip.getTimeToDeliver()).isAfter(now)) {
            drone.setDroneState(DroneState.IDLE);
            log.info("Drone " + drone.getId() + " is idle");
        } else {
            drone.setDroneState(DroneState.RETURNING);
            log.info("Drone " + drone.getId() + " is returning");
            //reduce battery percentage by 0.1
            drone.setBatteryPercentage((drone.getBatteryPercentage() - 0.1));
        }
        droneRepository.save(drone);
    }

    private void handleDelivering(Drone drone, Trip trip) throws InterruptedException {
        LocalDateTime loadedAt = trip.getLoadedAt();
        boolean changeToRetuning = false;
        if (loadedAt.plusSeconds(trip.getTimeToDeliver()).isAfter(LocalDateTime.now())) { // if drone is still delivering
            drone.setDroneState(DroneState.DELIVERING);
            log.info("Drone " + drone.getId() + " is delivering");
            // reduce battery percentage by 0.1% every second
            drone.setBatteryPercentage((drone.getBatteryPercentage() - 0.1));
        } else { // if drone is done delivering
            drone.setDroneState(DroneState.DELIVERED);
            log.info("Drone " + drone.getId() + " is delivered");
            changeToRetuning = true;
            trip.setDeliveredAt(LocalDateTime.now());
            tripRepository.save(trip);
        }
        droneRepository.save(drone);
        if (changeToRetuning) {
            Thread.sleep(1000);
            drone.setDroneState(DroneState.RETURNING);
            log.info("Drone " + drone.getId() + " is returning");
            // reduce battery percentage by 0.1% every second
            drone.setBatteryPercentage((drone.getBatteryPercentage() - 0.1));
            droneRepository.save(drone);
        }

    }
}
