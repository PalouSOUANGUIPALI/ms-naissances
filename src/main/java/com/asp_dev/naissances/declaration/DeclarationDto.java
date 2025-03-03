package com.asp_dev.naissances.declaration;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.shared.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class DeclarationDto {
    private int id;
    private String name;
    private String description;
    private String comment;
    private LocalDateTime registered;
    private ProfilesDTO child;
    private ProfilesDTO firstParent;
    private ProfilesDTO secondParent;
    private List<DeclarationStatus> statuses;
    private Company company;


}
