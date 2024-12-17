package com.asp_dev.naissances.security.activations;

import com.asp_dev.naissances.profiles.entities.Profiles;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "activations")
public class Activation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Transient
    private int userCodeNotToPersist;
    private String activationUserCodeToPersist;
    private Boolean activationStatus;
    private LocalDateTime creation;
    private LocalDateTime desactivation;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "profiles_id")
    Profiles profiles;
}
