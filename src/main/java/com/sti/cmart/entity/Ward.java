package com.sti.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wards")
@Builder
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 255)
//    @Nationalized
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "districtId", nullable = false)
    private District districts;

    @OneToMany(mappedBy = "ward")
    private Set<Address> addresses;

}