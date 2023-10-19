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
@Table(name = "staff")
@Builder
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;

    @Size(max = 10)
    @NotNull
    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @NotNull
    @Column(name = "isActive", nullable = false)
    private Boolean isActive = false;

    @Size(max = 255)
    @Nationalized
    @Column(name = "image")
    private String image;

    @Size(max = 255)
    @Nationalized
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "status", nullable = false)
    private Short status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin", nullable = false)
    private Admin admin;

    @OneToMany(mappedBy = "staff")
    private Set<Customer> customers;

    @OneToMany(mappedBy = "staff")
    private Set<Driver> drivers;

}