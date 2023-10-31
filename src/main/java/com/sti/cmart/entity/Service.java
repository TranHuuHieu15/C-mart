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
@Table(name = "service")
@Builder
public class Service {
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
    @Column(name = "poster")
    private String poster;

    @NotNull
    @Column(name = "price_per_km", nullable = false)
    private Double pricePerKm;

    @NotNull
    @Column(name = "min_price", nullable = false)
    private Double minPrice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicleType", nullable = false)
    private VehicleType vehicleType;

    @NotNull
    @Column(name = "min_km_require", nullable = false)
    private Double minKmRequire;

    @Size(max = 255)
    @Nationalized
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "service")
    private Set<RegionApply> regionApplies;

    @OneToMany(mappedBy = "service")
    private Set<Trip> trips;

}