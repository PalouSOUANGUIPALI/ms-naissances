package com.asp_dev.naissances.declaration;

import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.shared.entities.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "declarations_status")
public class DeclarationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    @Column(name = "creation", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registered;

    @JsonProperty(access = WRITE_ONLY)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "declarations_id")
    private Declaration declaration;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "status_id")
    private Status status;

    @JsonProperty(access = WRITE_ONLY)
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "agents_id")
    private Profiles agent;

}
