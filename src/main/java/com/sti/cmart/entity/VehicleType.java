package com.sti.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vehicle_types")
@Builder
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 255)
    @Nationalized
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "rate", nullable = false)
    private Double rate;

    @OneToMany(mappedBy = "vehicleType")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "vehicleType")
    private Set<Service> services;

}