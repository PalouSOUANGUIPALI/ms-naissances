package com.asp_dev.naissances.security.services;

import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.repository.ProfilesRepository;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class SecurityService {
    private final ProfilesRepository profilesRepository;


    public Profiles getCurrentUser(){
        Jwt jwt = (Jwt) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        String email = jwt.getSubject();
        Optional<Profiles> profilesOptional = profilesRepository.findByEmail(email);
        return profilesOptional.orElseThrow(() -> new RuntimeException("Aucune response ne correspond aux paramètres fournis"));
    }
}
