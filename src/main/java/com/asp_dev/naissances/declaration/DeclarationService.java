package com.asp_dev.naissances.declaration;

import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.services.ProfilesService;
import com.asp_dev.naissances.security.services.SecurityService;
import com.asp_dev.naissances.shared.entities.Company;
import com.asp_dev.naissances.shared.repository.CompaniesRepository;
import com.asp_dev.naissances.shared.services.CompaniesService;
import com.asp_dev.naissances.shared.services.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeclarationService {
    private final DeclarationRepository declarationRepository;
    private final CompaniesService companiesService;
    private final ProfilesService profilesService;
    private final StatusService statusService;
    private final SecurityService securityService;


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
        this.declarationRepository.save(declaration);
    }

}
