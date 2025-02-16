package com.asp_dev.naissances.declaration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationStatusRepository extends JpaRepository<DeclarationStatus, Integer> {
}
