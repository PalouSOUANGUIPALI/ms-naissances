package com.asp_dev.naissances.shared.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH })
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "creation", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creation;


}
