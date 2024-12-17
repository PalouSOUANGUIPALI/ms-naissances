package com.asp_dev.naissances.profiles.entities;

import com.asp_dev.naissances.profiles.emuns.Civility;
import com.asp_dev.naissances.shared.entities.Address;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profiles implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private Civility civility;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Boolean active = false;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "addresses_id", nullable = true)
    private Address address;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "roles_id")
    private Roles roles;


    // Gestion des roles et des permissions accordées aux différents roles des profiles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Ajout du role
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.getRoles().getName().toUpperCase()));

        // Aout et/ou Association les permissions aux roles
        for (Permissions permission : this.getRoles().getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission.getName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active != null && this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active != null && this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active != null && this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active != null && this.active;
    }
}
