package com.start.drones.Trip;

import com.start.drones.Drone.Drone;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @ManyToOne(cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})//, fetch=FetchType.LAZY
    @JoinColumn(name="drone_id", referencedColumnName = "id")
    Drone drone;

}
