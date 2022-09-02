package com.start.drones.Medication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDTO {
    private long id;

    @Pattern(regexp = "^[a-zA-Z\\d_-]*$", message = "Name must contain only letters, numbers, ‘-‘, ‘_’")
    private String name;
    private double weight;
    //(allowed only upper case letters, underscore and numbers);
    @Pattern(regexp = "^[A-Z\\d_]*$", message = "Code must contain only upper case letters, numbers, ‘_’")
    private String code;

    public MedicationDTO(String name,  String code, double weight) {
        this.name = name;
        this.weight = weight;
        this.code = code;
    }
}
