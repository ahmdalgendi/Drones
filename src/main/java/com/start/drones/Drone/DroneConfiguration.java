package com.start.drones.Drone;

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
            list.add(new Drone(1L , "123456789", Model.Cruiserweight, State.DELIVERED , 55 , 300));
            list.add(new Drone(2L , "123456789", Model.Middleweight, State.LOADING , 100 , 400));

            repository.saveAll(list);
        };
    }
}
