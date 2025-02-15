package com.asp_dev.naissances.shared.repository;

import com.asp_dev.naissances.shared.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompaniesRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByName(String name);
}
