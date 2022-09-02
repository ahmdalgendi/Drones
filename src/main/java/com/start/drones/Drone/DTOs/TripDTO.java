package com.start.drones.Drone.DTOs;

import com.start.drones.Medication.Medication;
import com.start.drones.Trip.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {
    @Min(1)
    private int timeToDeliver;
    List<MedicationDTO> medications = new ArrayList<>();

    public TripDTO(Trip trip) {
        this.timeToDeliver = trip.getTimeToDeliver();
        List<Medication> medicationList = trip.getMedications();
        if (medicationList != null) {
            for (Medication medication : medicationList) {
                this.medications.add(new MedicationDTO(medication));
            }
        }
    }
}
