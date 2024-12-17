package com.asp_dev.naissances.profiles.services;

import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.repository.ActivationsRepository;
import com.asp_dev.naissances.security.activations.Activation;
import lombok.AllArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@AllArgsConstructor
@Service
public class ActivationsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ActivationsRepository activationsRepository;

    public Activation createProfileCode(Profiles profile) {
        Random random = new Random();
        int userCode = 100000 + random.nextInt(900000);
        Activation activation = Activation.builder()
                .activationStatus(Boolean.TRUE) // Le status boolean du code actif ou pas
                .userCodeNotToPersist(userCode) // Le code transient qui ne sera pas persisté dans la base de données
                .activationUserCodeToPersist(bCryptPasswordEncoder.encode(""+ userCode)) // Encodage du code qui sera persisté dans la base de données
                .creation(LocalDateTime.now())
                .desactivation(LocalDateTime.now().plusMinutes(5))
                .profiles(profile)
                .build();
        return this.activationsRepository.save(activation);
    }
}
