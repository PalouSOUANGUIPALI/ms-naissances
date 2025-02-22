package com.asp_dev.naissances.authentification;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.mappingDtoToObject.ProfilesMapper;
import com.asp_dev.naissances.profiles.repository.ProfilesRepository;
import com.asp_dev.naissances.shared.services.ValidationsService;
import com.asp_dev.naissances.profiles.emuns.Civility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthentificationServiceTest {

    @InjectMocks // Injection de la classe de test
    private AuthentificationService authentificationService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private ValidationsService validationsService;

    @Mock
    private ProfilesRepository profilesRepository;

    @Mock
    private ProfilesMapper profilesMapper;

    private ProfilesDTO profilesDTO;
    private Profiles profiles;

    @BeforeEach
    void setUp() {
        // Initialisation des mocks avant chaque test
        MockitoAnnotations.openMocks(this);

        // Initialisation d'un ProfilesDTO avec les propriétés nécessaires pour le test
        profilesDTO = ProfilesDTO.builder()
                .civility(Civility.MR)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .password("password123")
                .build();

        // Utilisation du Builder pour créer l'objet Profiles sans l'adresse
        profiles = Profiles.builder()
                .civility(Civility.MR) // Civilité
                .firstName("John")     // Prénom
                .lastName("Doe")       // Nom de famille
                .email("john.doe@example.com") // Email
                .phone("0123456789")   // Téléphone
                .password("securePassword") // Mot de passe
                .build(); // Pas d'adresse dans ce test

        // Simulation des comportements des mocks
        when(profilesMapper.dtoToEntity(profilesDTO)).thenReturn(profiles);
        when(bCryptPasswordEncoder.encode(profilesDTO.getPassword())).thenReturn("hashedPassword");
    }

    @Test
    void testCreate_withValidProfilesDTO_shouldSaveProfile() {
        // Arrange
        // Appel de la méthode à tester
        Profiles result = authentificationService.create(profilesDTO);


        // Act
        // Capture de l'argument passé à la méthode profilesRepository.save
        ArgumentCaptor<Profiles> profilesCaptor = ArgumentCaptor.forClass(Profiles.class);
        verify(profilesRepository).save(profilesCaptor.capture());

        Profiles savedProfile = profilesCaptor.getValue();


        // Assert
        // Vérifications des valeurs enregistrées
        assertNotNull(savedProfile); // Vérifier que le profil n'est pas null
        assertEquals(Civility.MR, savedProfile.getCivility()); // Vérifier que la civilité est correcte
        assertEquals("John", savedProfile.getFirstName()); // Vérifier le prénom
        assertEquals("Doe", savedProfile.getLastName()); // Vérifier le nom de famille
        assertEquals("john.doe@example.com", savedProfile.getEmail()); // Vérifier l'email
        assertEquals(savedProfile.getPassword(), "hashedPassword"); // Vérifier que le mot de passe est bien haché

        // Vérification des appels aux services de validation
        verify(validationsService).validateEmail(profilesDTO.getEmail()); // Vérifier que l'email a été validé
        verify(validationsService).validatePhoneNumber(profilesDTO.getPhone()); // Vérifier que le téléphone a été validé
    }

    @Test
    void testCreate_withNullAddress_shouldSaveProfileWithoutAddress() {
        // Ce test vérifie que l'objet adresse est absent dans l'objet créé, car l'adresse ne fait pas partie du DTO

        // Arrange
        // Création d'un ProfilesDTO sans adresse
        ProfilesDTO profilesDTOWithoutAddress = ProfilesDTO.builder()
                .civility(Civility.MR)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .password("password123")
                .build();

        // Création d'un profil sans adresse
        Profiles profilesWithoutAddress = Profiles.builder()
                .civility(Civility.MR) // Civilité
                .firstName("Jane")     // Prénom
                .lastName("Doe")       // Nom de famille
                .email("jane.doe@example.com") // Email
                .phone("0123456789")   // Téléphone
                .password("securePassword") // Mot de passe
                .build(); // Pas d'adresse ici

        // Act
        // Simulation du comportement de profilesMapper pour le nouveau DTO
        when(profilesMapper.dtoToEntity(profilesDTOWithoutAddress)).thenReturn(profilesWithoutAddress);

        // Appel de la méthode à tester
        Profiles result = authentificationService.create(profilesDTOWithoutAddress);

        // Capture de l'argument passé à la méthode profilesRepository.save
        ArgumentCaptor<Profiles> profilesCaptor = ArgumentCaptor.forClass(Profiles.class);
        verify(profilesRepository).save(profilesCaptor.capture());

        Profiles savedProfile = profilesCaptor.getValue();

        // Assert
        // Vérifications des valeurs enregistrées
        assertNotNull(savedProfile); // Vérifier que le profil n'est pas null
        assertNull(savedProfile.getAddress());  // Vérifier que l'adresse est bien absente
        verify(validationsService).validateEmail(profilesDTOWithoutAddress.getEmail()); // Vérifier la validation de l'email
        verify(validationsService).validatePhoneNumber(profilesDTOWithoutAddress.getPhone()); // Vérifier la validation du téléphone
    }
}
