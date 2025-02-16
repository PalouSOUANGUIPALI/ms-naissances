package com.asp_dev.naissances.profiles.services;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.mappingDtoToObject.ProfilesMapper;
import com.asp_dev.naissances.shared.exceptions.ProfilesNotFoundException;
import com.asp_dev.naissances.profiles.repository.ProfilesRepository;
import com.asp_dev.naissances.shared.services.AddressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Service
public class ProfilesService {

    private final AddressService addressService;
    private final ProfilesRepository profilesRepository;
    private final ProfilesMapper profilesMapper;


    public Set<ProfilesDTO> search() {
        List<Profiles> profiles = (List<Profiles>) this.profilesRepository.findAll();


        // Throw exception if no profiles are found
        if (profiles.isEmpty()) {
            throw new ProfilesNotFoundException("Aucun profile n'existe.");
        }
        return profiles.stream().map(this.profilesMapper::entityToDto).collect(Collectors.toSet());

    }


    public Profiles searchOneProfile(int id) {
        Optional<Profiles> optionalProfiles = this.profilesRepository.findById(id);
        return optionalProfiles.orElseThrow(() -> new EntityNotFoundException(
                "Aucune response ne correspond aux paramètres fournis"));
    }


    public Profiles updateProfile(int id, Profiles profiles) {
        Optional<Profiles> optionalProfilesInDataBase = Optional.ofNullable(this.searchOneProfile(id));
        if (optionalProfilesInDataBase.isPresent()) {
            optionalProfilesInDataBase.get().setEmail(profiles.getEmail());
            optionalProfilesInDataBase.get().setFirstName(profiles.getFirstName());
            optionalProfilesInDataBase.get().setLastName(profiles.getLastName());
            optionalProfilesInDataBase.get().setPhone(profiles.getPhone());
        }
        return this.profilesRepository.save(optionalProfilesInDataBase.get());
    }

    public void deleteProfile(int id) {
        Profiles profiles = this.searchOneProfile(id);
        this.profilesRepository.delete(profiles);
    }

    public Profiles createIfNotExist(Profiles profiles) {
        Optional<Profiles> optionalProfiles = this.profilesRepository.findByEmail(profiles.getEmail());
        if(optionalProfiles.isEmpty()) {
            profiles = this.profilesRepository.save(profiles);
        }else {
            profiles = optionalProfiles.get();
        }
        return profiles;
    }
}
