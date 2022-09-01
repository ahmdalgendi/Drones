package com.start.drones.Drone;

import com.start.drones.Drone.Exceptions.DroneNotFoundException;
import com.start.drones.Medication.Medication;
import com.start.drones.Medication.MedicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DroneService {
    final DroneRepository droneRepository;
    final MedicationRepository medicationRepository;

    DroneService(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    @Transactional
    public List<DroneDTO> index() {
        return droneRepository.findAll().stream().map(DroneDTO::new).collect(java.util.stream.Collectors.toList());
    }

    @Transactional
    public DroneDTO store(@Valid DroneDTO drone) {
        return new DroneDTO(droneRepository.save(new Drone(drone)));
    }

    @Transactional
    public Drone patch(long id, Map<String, Object> updates) {
        Optional<Drone> drone = droneRepository.findById(id);

        if (drone.isPresent()) {
            updates.forEach((key, value) -> {
                System.out.println("Key: " + key + " Value: " + value);
                Field field = ReflectionUtils.findField(Drone.class, key);
                if (field != null) {
                    ReflectionUtils.makeAccessible(field);
                    ReflectionUtils.setField(field, drone.get(), value);
                }

            });
        } else
            throw new DroneNotFoundException("Drone with id {" + id + "} not found");

        return droneRepository.save(drone.get());
    }

    public Drone findById(long id) {
        Drone drone = droneRepository.findById(id).orElse(null);
        if (drone == null)
            throw new DroneNotFoundException("Drone with id {" + id + "} not found");
        return drone;
    }

    public void delete(long id) {

        Drone drone = this.findById(id);
        if (drone != null) {
            droneRepository.delete(drone);
        } else {
            throw new DroneNotFoundException("Drone not found");
        }
    }

    public boolean canLoad(long id) {
        Drone drone = this.findById(id);
        return drone.getState() == State.IDLE;
    }

    public DroneDTO show(long id) {
        Drone drone = this.findById(id);
        return new DroneDTO(drone);
    }

    protected boolean isIdle(Drone drone) {
        return drone.getState() == State.IDLE;
    }


    protected boolean canLoadMedications(Drone drone, List<Medication> medications) {
        double medicationWeight = 0;
        if (!isIdle(drone)) {
            return false;
        }

        for (Medication medication : medications) {
            medicationWeight += medication.getWeight();
        }
        return drone.getMaxWeight() >= medicationWeight && drone.getBatteryPercentage() >= Drone.MIN_BATTERY_FOR_LOADING;
    }

    public Object load(long id, LoadDroneRequest loadDroneRequest) {
        Drone drone = this.findById(id);
        List<Medication> medicationEntities = medicationRepository.findAllById(loadDroneRequest.getMed());
        if (medicationEntities.size() != loadDroneRequest.getMed().size()) {
            throw new RuntimeException("Medication not found");
        }
        if (canLoadMedications(drone, medicationEntities)) {
            drone.setState(State.LOADING);
        } else {
            throw new RuntimeException("Drone can't load medications");
        }
        droneRepository.save(drone);
        //start async task to load the trip

        return new DroneDTO(drone);
    }
}
