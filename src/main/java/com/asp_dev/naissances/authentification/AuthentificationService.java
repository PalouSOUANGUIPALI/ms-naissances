package com.asp_dev.naissances.authentification;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.mappingDtoToObject.ProfilesMapper;
import com.asp_dev.naissances.profiles.repository.ProfilesRepository;
import com.asp_dev.naissances.shared.entities.Address;
import com.asp_dev.naissances.shared.services.AddressService;
import com.asp_dev.naissances.shared.services.ValidationsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@AllArgsConstructor
@Service
public class AuthentificationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ValidationsService validationsService;
    private final ProfilesRepository profilesRepository;
    private final AddressService addressService;
    private final ProfilesMapper profilesMapper;

    public Profiles create(ProfilesDTO profilesDTO) {
        log.info("l'email du nouveau profile {} ", profilesDTO.email());

        Profiles profiles = this.profilesMapper.dtoToEntity(profilesDTO);

        if (profiles.getAddress() != null) {
            Address address = this.addressService.create(profiles.getAddress());
            profiles.setAddress(address);
        }


        String userPassword = profiles.getPassword();
        String hashedPassword = bCryptPasswordEncoder.encode(userPassword);
        profiles.setPassword(hashedPassword);
        this.validationsService.validateEmail(profiles.getEmail());
        this.validationsService.validatePhoneNumber(profiles.getPhone());

        this.profilesRepository.save(profiles);

        return profiles;
    }

}
