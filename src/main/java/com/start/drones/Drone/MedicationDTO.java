package com.start.drones.Drone;

import com.start.drones.Medication.Medication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicationDTO {
    public long id;
    @Pattern(regexp = "^[a-zA-Z0-9-_]*$", message = "Name can contain only letters, numbers, '-', '_'")
    public String name;
    public double weight;
    @Pattern(regexp = "^[A-Z_0-9]+$", message = "Code must contain only upper case letters, numbers and underscore")
    public String code;

    public MedicationDTO(Medication medication) {
        this.id = medication.getId();
        this.name = medication.getName();
        this.weight = medication.getWeight();
        this.code = medication.getCode();
    }
//    public String photoUrl;
}
