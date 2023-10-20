package com.sti.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "\"date\"", nullable = false)
    private Date date;

    @NotNull
    @Column(name = "money", nullable = false)
    private Double money;

    @NotNull
    @Column(name = "status", nullable = false)
    private Short status;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "e_wallet", nullable = false)
    private EWallet eWallet;

}