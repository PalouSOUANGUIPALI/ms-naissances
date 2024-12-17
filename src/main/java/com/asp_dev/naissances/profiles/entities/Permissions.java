package com.asp_dev.naissances.profiles.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "permissions")
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private LocalDateTime creation;


    public Permissions() {
        this.creation = LocalDateTime.now(); // Initialisation de la date de création
    }
}
