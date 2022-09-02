package com.start.drones.Drone;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.start.drones.Drone.DTOs.CanLoadDTO;
import com.start.drones.Drone.DTOs.DroneBatteryDTO;
import com.start.drones.Drone.DTOs.DroneDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/drones")
public class DroneController {
    final DroneService droneService;

    DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping
    public Iterable<DroneDTO> index() {
        return droneService.index();
    }

    @GetMapping("/{id}")
    public DroneDTO show(@PathVariable("id") long id) {
        return droneService.show(id);
    }

    @PostMapping
    public DroneDTO store(@Valid @RequestBody DroneDTO drone) {
        return droneService.store(drone);
    }

    @JsonAnyGetter
    @GetMapping("{id}/battery")
    public DroneBatteryDTO showBattery(@PathVariable("id") long id) {
        DroneBatteryDTO battery = new DroneBatteryDTO();
        double level = droneService.findById(id).getBatteryPercentage();
        battery.setBatteryPercentage(level);
        return battery;
    }

    @JsonAnyGetter
    @GetMapping("{id}/can-load")
    public CanLoadDTO canLoad(@PathVariable("id") long id) {
        CanLoadDTO canLoadDTO = new CanLoadDTO();
        canLoadDTO.setCanLoad(droneService.canLoad(id));
        return canLoadDTO;
    }

    @RequestMapping(value = "{id}/load", method = RequestMethod.POST,
            consumes = "application/json")
    public Object load(@PathVariable("id") long id, @RequestBody LoadDroneRequest loadDroneRequest) {
        return droneService.load(id, loadDroneRequest);
    }

}
