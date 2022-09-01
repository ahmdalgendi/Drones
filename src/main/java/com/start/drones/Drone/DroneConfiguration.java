package com.start.drones.Drone;

import com.start.drones.Trip.Trip;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DroneConfiguration {
    @Bean
    CommandLineRunner DroneCommandRunner(DroneRepository repository) {
        return args -> {
            List<Drone> list = new ArrayList<>();
            list.add(new Drone(1L , "123456789", Model.Cruiserweight, State.IDLE , 55 , 300 , new ArrayList<>()));
            list.add(new Drone(2L , "123456789", Model.Middleweight, State.LOADING , 100 , 400 , new ArrayList<>()));

            repository.saveAll(list);
        };
    }
}
