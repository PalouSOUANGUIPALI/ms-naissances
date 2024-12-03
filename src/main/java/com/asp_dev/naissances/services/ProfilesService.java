package com.asp_dev.naissances.services;

import com.asp_dev.naissances.entities.Profiles;
import com.asp_dev.naissances.repository.ProfilesRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Slf4j
@Service
public class ProfilesService {

    private final ProfilesRepository profilesRepository;

    public void create(Profiles profiles) {
        log.info("l'email du nouveau profile {} ", profiles.getEmail());
        profilesRepository.save(profiles);

    }
}
