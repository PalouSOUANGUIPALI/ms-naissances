package com.asp_dev.naissances.shared.repository;

import com.asp_dev.naissances.shared.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {
}
