package com.asp_dev.naissances.profiles.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private LocalDateTime creation;

    @ManyToMany
            @JoinTable(name = "roles_permissions", joinColumns =
                    @JoinColumn(name = "roles_id"),
                   inverseJoinColumns = @JoinColumn(name = "permissions_id")
            )
    private List<Permissions> permissions;



    public Roles() {
        this.creation = LocalDateTime.now(); // Initialisation de la date de création
    }
}
