package com.sti.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "licensePlate", nullable = false, length = 20)
    private String licensePlate;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "color", nullable = false, length = 20)
    private String color;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "brand", nullable = false, length = 50)
    private String brand;

    @NotNull
    @Column(name = "yearOfManufacture", nullable = false)
    private LocalDate yearOfManufacture;

    @Size(max = 255)
    @Nationalized
    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicleType", nullable = false)
    private VehicleType vehicleType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicleType", nullable = false)
    private Driver driver;
}