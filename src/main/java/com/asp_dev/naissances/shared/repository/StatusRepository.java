package com.asp_dev.naissances.shared.repository;

import com.asp_dev.naissances.shared.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    Optional<Status> findByName(String name);
}
