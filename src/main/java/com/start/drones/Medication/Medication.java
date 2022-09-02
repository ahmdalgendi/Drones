package com.start.drones.Medication;

import com.start.drones.Medication.DTOs.MedicationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Medication(String name, double weight, String code) {
        this.name = name;
        this.weight = weight;
        this.code = code;
    }

    public Medication(MedicationDTO medicationDTO) {
        this.name = medicationDTO.getName();
        this.weight = medicationDTO.getWeight();
        this.code = medicationDTO.getCode();
    }

    @Override
    public String toString() {
        return "Medication{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", code='" + code + '\'' +
//                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }

    private String name;
    private double weight;
    private String code;
//    private String photoUrl;
}
