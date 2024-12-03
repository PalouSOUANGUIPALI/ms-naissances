package com.asp_dev.naissances.repository;

import com.asp_dev.naissances.entities.Profiles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilesRepository extends CrudRepository<Profiles, Integer> {

}
