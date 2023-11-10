package com.sti.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
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
    @Column(name = "licensePlate", nullable = false, length = 20, unique = true)
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
    private Date yearOfManufacture;

    @Size(max = 255)
    @Nationalized
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicleTypeId", nullable = false)
    private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountId", nullable = false)
    private Account account;
}