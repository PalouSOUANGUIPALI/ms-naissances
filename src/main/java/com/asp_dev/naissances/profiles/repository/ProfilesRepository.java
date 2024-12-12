package com.asp_dev.naissances.profiles.repository;

import com.asp_dev.naissances.profiles.entities.Profiles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilesRepository extends CrudRepository<Profiles, Integer> {

    Optional<Profiles> findByEmail(String email);
}
