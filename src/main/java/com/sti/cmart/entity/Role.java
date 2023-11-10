package com.sti.cmart.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @ManyToOne()
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "user")
    private Set<Trip> user;

    @OneToMany(mappedBy = "driver")
    private Set<Trip> driver;

    @OneToMany(mappedBy = "user")
    private Set<Chat> accountUser;

    @OneToMany(mappedBy = "driver")
    private Set<Chat> accountDriver;

    public Role(RoleName roleName, Account account) {
        this.roleName = roleName;
        this.account = account;
    }

    public String getRoleName() {
        return roleName.toString();
    }
}
