package com.start.drones.Drone;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Configuration
public class DroneConfiguration {
    @Bean
    CommandLineRunner DroneCommandRunner(DroneRepository repository) {
        return args -> {
            List<Drone> list = new ArrayList<>();
            // generate 10 drones
            for (int i = 0; i < 10; i++) {
                DroneDTO droneDTO = getRandomDrone();
                list.add(new Drone(droneDTO));
            }
            repository.saveAll(list);
        };
    }

    private DroneDTO getRandomDrone() {
        Faker faker = new Faker(new Locale("en-GB"));
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-GB"), new RandomService());
        return new DroneDTO(
                faker.name().username(),
                fakeValuesService.regexify("Lightweight|Middleweight|Cruiserweight|Heavyweight"),
                "IDLE",
                faker.number().numberBetween(0, 100),
                faker.number().numberBetween(50, 500)
        );
    }
}
