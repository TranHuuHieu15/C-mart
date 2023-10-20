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
@Table(name = "e_wallet")
@Builder
public class EWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 20)
    @NotNull
    @Column(name = "identifier", nullable = false, length = 20)
    private String identifier;

    @NotNull
    @Column(name = "money", nullable = false)
    private Double money;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "bank", nullable = false)
    private String bank;

    @NotNull
    @Column(name = "status", nullable = false)
    private Short status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "eWallet")
    private Set<Transaction> transactions;

}