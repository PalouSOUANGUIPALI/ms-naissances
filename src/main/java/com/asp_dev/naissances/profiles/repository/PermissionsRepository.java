package com.asp_dev.naissances.profiles.repository;

import com.asp_dev.naissances.profiles.entities.Permissions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsRepository extends CrudRepository<Permissions, Integer> {

}
