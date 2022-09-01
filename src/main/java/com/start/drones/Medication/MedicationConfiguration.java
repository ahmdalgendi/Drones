package com.start.drones.Medication;

import com.start.drones.Drone.Drone;
import com.start.drones.Drone.DroneRepository;
import com.start.drones.Drone.Model;
import com.start.drones.Drone.State;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MedicationConfiguration {
    @Bean
    CommandLineRunner MedicationCommandRunner(MedicationRepository repository) {
        return args -> {
            List<Medication> list = new ArrayList<>();
            list.add(new Medication(1L, "123456789", 12.5, "HELLO"));
            list.add(new Medication(2L, "second", 17.5, "WORLD"));


            repository.saveAll(list);
        };
    }
}
