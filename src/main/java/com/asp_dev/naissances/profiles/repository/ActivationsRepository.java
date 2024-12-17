package com.asp_dev.naissances.profiles.repository;

import com.asp_dev.naissances.security.activations.Activation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationsRepository extends CrudRepository<Activation, Integer> {
}
