package com.sti.cmart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
@Builder
public class Account implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "username", nullable = false, length = 50, unique = true)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 50)
    @Column(columnDefinition = "nvarchar(50) not null")
    private String fullname;

    @Size(max = 10)
    @NotNull
    @Column(name = "phone", nullable = false, length = 10, unique = true)
    private String phone;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @NotNull
    @Column(name = "isActive", nullable = false)
    private Boolean isActive;

    @Size(max = 255)
    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "user")
    private Set<Trip> trips;

    @OneToMany(mappedBy = "driver")
    private Set<Trip> trip;

    @NotNull
    @Column(name = "status", nullable = false)
    private Short status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    List<Role> roles;

    @OneToMany(mappedBy = "accounts")
    private Set<FeedbackSy> feedbackSys;

    @OneToMany(mappedBy = "account")
    private Set<EWallet> eWallets;

    @OneToMany(mappedBy = "account")
    private Set<Vehicle> vehicles;

    @OneToMany(mappedBy = "account")
    private Set<Message> messages;

    @OneToMany(mappedBy = "user")
    private Set<Chat> accountUser;

    @OneToMany(mappedBy = "driver")
    private Set<Chat> accountDriver;

    public Account(String username, String fullname, String email, String password,String phone,boolean isActive,short status, List<Role> roles) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
        this.status = status;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
