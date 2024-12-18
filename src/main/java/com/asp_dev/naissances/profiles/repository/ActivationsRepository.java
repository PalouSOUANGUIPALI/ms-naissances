package com.asp_dev.naissances.profiles.repository;

import com.asp_dev.naissances.security.activations.Activation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ActivationsRepository extends CrudRepository<Activation, Integer> {
    List<Activation> findAllByActivationStatusAndDesactivationAfter(Boolean activationStatus, LocalDateTime desactivationDate);
}
