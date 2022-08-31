package com.start.drones.Trip;

import com.start.drones.Drone.Drone;
import com.start.drones.Medication.Medication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
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

    //drone
    //medications
    //loaded at
    LocalDateTime loadedAt;
    // time to deliver in minutes
    int timeToDeliver;
    // delivered at
    LocalDateTime deliveredAt;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drone_id")
    Drone drone;

    public Trip(Long id, LocalDateTime loadedAt, int timeToDeliver, LocalDateTime deliveredAt) {
        this.id = id;
        this.loadedAt = loadedAt;
        this.timeToDeliver = timeToDeliver;
        this.deliveredAt = deliveredAt;
    }
}
