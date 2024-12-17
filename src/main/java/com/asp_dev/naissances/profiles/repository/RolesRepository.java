package com.asp_dev.naissances.profiles.repository;

import com.asp_dev.naissances.profiles.entities.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<Roles, Integer> {
    Roles findByName(String name);
}
