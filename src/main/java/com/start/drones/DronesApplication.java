package com.start.drones;

import com.start.drones.Drone.Drone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync

public class DronesApplication {

    public static void main(String[] args) {
        SpringApplication.run(DronesApplication.class, args);
    }

}
