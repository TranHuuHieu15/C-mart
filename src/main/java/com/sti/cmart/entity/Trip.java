package com.sti.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "trip")
@Builder
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "\"date\"", nullable = false)
    private Date date;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "startLocation", nullable = false)
    private String startLocation;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "endLocation", nullable = false)
    private String endLocation;

    @NotNull
    @Column(name = "distance", nullable = false)
    private Double distance;

    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    @NotNull
    @Column(name = "status", nullable = false)
    private Short status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "payment", nullable = false)
    private Payment payment;

    @NotNull
    @Column(name = "drop_time", nullable = false)
    private Date dropTime;

    @NotNull
    @Column(name = "pick_up_time", nullable = false)
    private Date pickUpTime;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service", nullable = false)
    private Service service;

    @OneToOne(mappedBy = "trip")
    private TripFeedback tripFeedbacks;

    @OneToOne(mappedBy = "trip")
    private Voucher vouchers;

    @OneToOne(mappedBy = "trip")
    private Chat chats;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountDriver", nullable = false)
    private Role driver;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountDriver", nullable = false)
    private Role user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "address", nullable = false)
    private Address address;
}