package com.asp_dev.naissances.services;

import com.asp_dev.naissances.entities.Profiles;
import com.asp_dev.naissances.shared.exceptions.ProfilesNotFoundException;
import com.asp_dev.naissances.repository.ProfilesRepository;
import com.asp_dev.naissances.shared.exceptions.services.ValidationsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class ProfilesService {

    private final ProfilesRepository profilesRepository;
    private final ValidationsService validationsService;

    public void create(Profiles profiles) {
        log.info("l'email du nouveau profile {} ", profiles.getEmail());

        this.validationsService.validateEmail(profiles.getEmail());
        this.validationsService.validatePhoneNumber(profiles.getPhone());

        this.profilesRepository.save(profiles);

    }

    public List<Profiles> search() {
        List<Profiles> profiles = (List<Profiles>) this.profilesRepository.findAll();

        // Throw exception if no profiles are found
        if (profiles.isEmpty()) {
            throw new ProfilesNotFoundException("Aucun profile n'existe.");
        }
        return profiles;
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
        this.profilesRepository.save(optionalProfilesInDataBase.get());
        return optionalProfilesInDataBase.orElse(null);
    }

    public void deleteProfile(int id) {
        Profiles profiles = this.searchOneProfile(id);
        this.profilesRepository.delete(profiles);
    }
}
