package com.start.drones.Drone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    Drone findFirstById(Long id);

}
