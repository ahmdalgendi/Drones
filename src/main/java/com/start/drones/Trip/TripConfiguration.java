package com.start.drones.Trip;

import com.start.drones.Drone.DroneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class TripConfiguration {
    @Bean
    CommandLineRunner TripCommandRunner(TripRepository tripRepository, DroneRepository droneRepository) {
        return args -> {
            List<Trip> list = new ArrayList<>();
            list.add(new Trip(
                    1L,
                    LocalDateTime.now().minusDays(2),
                    //1 day in minutes
                    1440,
                    LocalDateTime.now(),
                    droneRepository.findFirstById(1L)

            ));

            tripRepository.saveAll(list);
        };
    }
}
