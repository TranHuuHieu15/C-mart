package com.sti.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tripFeedback")
public class TripFeedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip", nullable = false)
    private Trip trip;

    @Size(max = 255)
    @Nationalized
    @Column(name = "content")
    private String content;

    @NotNull
    @Column(name = "star", nullable = false)
    private Short star;

    @NotNull
    @Column(name = "attitude", nullable = false)
    private Short attitude;

    @NotNull
    @Column(name = "safety", nullable = false)
    private Short safety;

    @NotNull
    @Column(name = "speed", nullable = false)
    private Short speed;

    @NotNull
    @Column(name = "efficiency", nullable = false)
    private Short efficiency;

}