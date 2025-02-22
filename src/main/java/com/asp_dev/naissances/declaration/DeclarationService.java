package com.asp_dev.naissances.declaration;

import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.services.ProfilesService;
import com.asp_dev.naissances.security.services.SecurityService;
import com.asp_dev.naissances.shared.entities.Company;
import com.asp_dev.naissances.shared.entities.Status;
import com.asp_dev.naissances.shared.services.CompaniesService;
import com.asp_dev.naissances.shared.services.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DeclarationService {
    private final DeclarationRepository declarationRepository;
    private final CompaniesService companiesService;
    private final ProfilesService profilesService;
    private final StatusService statusService;
    private final SecurityService securityService;
    private final DeclarationStatusRepository declarationStatusRepository;
    private final DeclarationsMapper declarationsMapper;



    public void create(Declaration declaration) {
        Profiles firstParent = this.securityService.getCurrentUser();
        declaration.setFirstParent(firstParent);

        Profiles SecondParent = this.profilesService.createIfNotExist(declaration.getSecondParent());
        declaration.setSecondParent(SecondParent);

        Profiles child = this.profilesService.createIfNotExist(declaration.getChild());
        declaration.setChild(child);

        Company company = this.companiesService.createIfNotExist(declaration.getCompany());
        declaration.setCompany(company);

        String name = String.format(
                "Déclaration de %s %s pour %s %s",
                firstParent.getFirstName(),
                firstParent.getLastName(),
                child.getFirstName(),
                child.getLastName()
        );
        declaration.setName(name);

        declaration = this.declarationRepository.save(declaration);
        Status status = this.statusService.search(Map.of("name", "NEW"));
        DeclarationStatus declarationStatus = DeclarationStatus.builder()
                .status(status)
                .declaration(declaration)
                .registered(LocalDateTime.now())
                .build();
        this.declarationStatusRepository.save(declarationStatus);
    }


    public List<DeclarationDto> search() {
        List<Declaration> declarations = this.declarationRepository.findAll();
        return declarations.stream().map(declarationsMapper::entityToDTO).collect(Collectors.toList());

    }
}
