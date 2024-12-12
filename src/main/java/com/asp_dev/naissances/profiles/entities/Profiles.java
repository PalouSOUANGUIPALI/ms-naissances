package com.asp_dev.naissances.profiles.entities;

import com.asp_dev.naissances.profiles.emuns.Civility;
import com.asp_dev.naissances.shared.entities.Address;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profiles {

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

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "addresses_id", nullable = true)
    private Address address;

}
