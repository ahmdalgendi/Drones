package com.start.drones.Drone;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/drones")
public class DroneController {
    final DroneService droneService;

    DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @GetMapping
    public Iterable<Drone> index() {
        return droneService.index();
    }

    @GetMapping("/{id}")
    public Drone show(@PathVariable("id") long id) {
        return droneService.findById(id);
    }

    @PostMapping
    public Drone store(@Valid @RequestBody DroneDTO drone) {
        return droneService.store(drone);
    }

    @JsonAnyGetter
    @GetMapping("{id}/battery")
    public Map<String, String> showBattery(@PathVariable("id") long id) {
        Map<String, String> battery = new HashMap<>();
        double level = droneService.findById(id).getBatteryPercentage();
        battery.put("batteryPercentage", new String(String.valueOf(level)));
        return battery;
    }

    @JsonAnyGetter
    @GetMapping("{id}/can-load")
    public Map<String, String> canLoad(@PathVariable("id") long id) {
        Map<String, String> json = new HashMap<>();
        boolean canLoad = droneService.canLoad(id);
        State level = droneService.findById(id).getState();
        json.put("canLoad", String.valueOf(canLoad));
        return json;
    }


}
