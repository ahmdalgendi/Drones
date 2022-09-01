package com.start.drones.Drone.DroneLoader;

import ch.qos.logback.classic.Logger;
import com.start.drones.Drone.Drone;
import com.start.drones.Drone.DroneRepository;
import com.start.drones.Drone.State;
import com.start.drones.Drone.TripDTO;
import com.start.drones.Medication.Medication;
import com.start.drones.Medication.MedicationRepository;
import com.start.drones.Trip.Trip;
import com.start.drones.Trip.TripRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneLoaderService {
    final DroneRepository droneRepository;
    final TripRepository tripRepository;
    final MedicationRepository medicationRepository;
    private final Logger log = (Logger) org.slf4j.LoggerFactory.getLogger(DroneLoaderService.class);

    DroneLoaderService(DroneRepository droneRepository, TripRepository tripRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.tripRepository = tripRepository;
        this.medicationRepository = medicationRepository;
    }
    @Async
    public void loadMedicationsToDrone(long droneId, List<Long> medicationids, TripDTO tripDTO) throws InterruptedException {
        Thread.sleep(1000);
        log.info("Loading medications to drone");
        Drone drone = droneRepository.findById(droneId).get();
        List<Medication> medicationEntities = medicationRepository.findAllById(medicationids);
        Trip trip = new Trip();
        trip.setTimeToDeliver(tripDTO.getTimeToDeliver());
        trip.addMedications(medicationEntities);
        tripRepository.save(trip);
        trip.setDrone(drone);
        tripRepository.save(trip);


        drone.setState(State.LOADED);
        log.info("Drone loaded");
        droneRepository.save(drone);
        Thread.sleep(1000);
        // set state to delivering
        drone.setState(State.DELIVERING);
        droneRepository.save(drone);
        log.info("Drone delivering");
    }
}
