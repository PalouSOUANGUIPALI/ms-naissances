package com.asp_dev.naissances.authentification;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.entities.Roles;
import com.asp_dev.naissances.profiles.mappingDtoToObject.ProfilesMapper;
import com.asp_dev.naissances.profiles.repository.ProfilesRepository;
import com.asp_dev.naissances.profiles.repository.RolesRepository;
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
    private final RolesRepository rolesRepository;

    public Profiles create(ProfilesDTO profilesDTO) {
        log.info("l'email du nouveau profile {} ", profilesDTO.email());

        // Mapping du dto en objet
        Profiles profiles = this.profilesMapper.dtoToEntity(profilesDTO);

        // Vérification de l'adresse du profile
        if (profiles.getAddress() != null) {
            Address address = this.addressService.create(profiles.getAddress());
            profiles.setAddress(address);
        }


        // Encrypter le mot de passe du profile
        String userPassword = profiles.getPassword();
        String hashedPassword = bCryptPasswordEncoder.encode(userPassword);
        profiles.setPassword(hashedPassword);

        // Chercher les roles enregistrés dans la base de données en amont
        // Ici, le role "PUBLIC" étant considérés comme role par défaut
        Roles roles = this.rolesRepository.findByName("PUBLIC");

        // Ajouter le role au profil
        profiles.setRoles(roles);

        // Valider l'email de l'utilisateur
        this.validationsService.validateEmail(profiles.getEmail());

        // Valider le phone de l'utilisateur
        this.validationsService.validatePhoneNumber(profiles.getPhone());

        // enregistrer le profile ou l'utilisateur dans la base de données
        this.profilesRepository.save(profiles);

        // Retourner le profile enregistré dans la base de donnés
        return profiles;
    }

}
