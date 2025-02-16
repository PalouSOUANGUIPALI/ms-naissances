package com.asp_dev.naissances.declaration;

import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.shared.entities.Company;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "declarations")
public class Declaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String comment;

    @Column(name = "registered", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime registered;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "child_id", referencedColumnName = "id")
    private Profiles child;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "first_parent_id", referencedColumnName = "id")
    private Profiles firstParent;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "second_parent_id", referencedColumnName = "id")
    private Profiles secondParent;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @OneToMany(mappedBy = "declaration", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<DeclarationStatus> statuses;

}

