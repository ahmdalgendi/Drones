package com.start.drones.Drone;

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

    DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public List<Drone> index() {
        return droneRepository.findAll();
    }

    public Drone store(@Valid DroneDTO drone) {
        return droneRepository.save(new Drone(drone));
    }
    @Transactional
    public Drone patch(long id, Map<String, Object> updates) {
        Optional<Drone> drone = droneRepository.findById(id);

        if (drone.isPresent()) {
            updates.forEach((key, value) -> {
                System.out.println("Key: " + key + " Value: " + value);
                Field field = ReflectionUtils.findField(Drone.class, key);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, drone.get(), value);
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

}
