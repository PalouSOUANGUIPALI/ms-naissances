package com.asp_dev.naissances.shared.services;

import com.asp_dev.naissances.shared.entities.Address;
import com.asp_dev.naissances.shared.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AddressService {

    private final AddressRepository addressRepository;

    public Address create(Address address) {
        return this.addressRepository.save(address);
    }
}
