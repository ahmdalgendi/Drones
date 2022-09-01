package com.start.drones.Trip;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.start.drones.Drone.Drone;
import com.start.drones.Medication.Medication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    LocalDateTime loadedAt;
    // time to deliver in minutes
    int timeToDeliver;
    // delivered at
    LocalDateTime deliveredAt;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//, fetch=FetchType.LAZY
//    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    Drone drone;

    public Trip(Long id, LocalDateTime loadedAt, int timeToDeliver, LocalDateTime deliveredAt, Drone drone) {
        this.id = id;
        this.loadedAt = loadedAt;
        this.timeToDeliver = timeToDeliver;
        this.deliveredAt = deliveredAt;
        this.drone = drone;
    }

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnoreProperties("trips")
    List<Medication> medications = new ArrayList<>();

    public void addMedication(Medication medication) {
        medications.add(medication);
    }

    public void addMedications(List<Medication> medicationEntities) {
        medications.addAll(medicationEntities);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", loadedAt=" + loadedAt +
                ", timeToDeliver=" + timeToDeliver +
                ", deliveredAt=" + deliveredAt +
                '}';
    }
}
