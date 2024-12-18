package com.asp_dev.naissances.profiles.services;

import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.repository.ActivationsRepository;
import com.asp_dev.naissances.security.activations.Activation;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@AllArgsConstructor
@Service
public class ActivationsService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ActivationsRepository activationsRepository;
    private final BCryptPasswordEncoder passwordEncoder;

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

    public Profiles validateAccountCodeAndReturnProfile(Map<String, String> activationCode) {
        // Chercher dans la BDD la liste des codes d'activation qui sont à true et qui matchent avec le code d'activation
        // envoyé par l'utilisateur dans la Map<String, String>
        List<Activation> activations = this.activationsRepository
                .findAllByActivationStatusAndDesactivationAfter(true, LocalDateTime.now());
      activations = activations.stream().filter(
              activation -> bCryptPasswordEncoder.matches(
                     activationCode.get("code"),
                      activation.getActivationUserCodeToPersist()
                      )
      ).toList();

      // Vérifier que le premier code retourné n'est pas vide et qu'il valide
      if (activations.isEmpty()) {
          throw new RuntimeException("Le code est invalide ou expiré");
      }
      Activation activation = activations.get(0);
      log.info("Activation code encodé est {}: ", activation.getActivationUserCodeToPersist());
      activation.setActivationStatus(Boolean.FALSE);
      this.activationsRepository.save(activation);

      return activation.getProfiles();
    }
}
