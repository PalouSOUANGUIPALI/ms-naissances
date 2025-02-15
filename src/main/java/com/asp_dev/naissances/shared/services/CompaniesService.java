package com.asp_dev.naissances.shared.services;

import com.asp_dev.naissances.shared.entities.Company;
import com.asp_dev.naissances.shared.repository.CompaniesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class CompaniesService {
    private final CompaniesRepository companiesRepository;

    public Company createIfNotExist(Company company) {
        Optional<Company> optionalCompany = this.companiesRepository.findByName(company.getName());
        return optionalCompany.isPresent() ? optionalCompany.get() : this.companiesRepository.save(company);
    }
}
