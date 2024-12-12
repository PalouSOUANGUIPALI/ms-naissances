package com.asp_dev.naissances.authentification;

import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.shared.entities.Address;
import com.asp_dev.naissances.profiles.repository.ProfilesRepository;
import com.asp_dev.naissances.shared.services.AddressService;
import com.asp_dev.naissances.shared.services.ValidationsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthentificationServiceTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private ValidationsService validationsService;

    @Mock
    private ProfilesRepository profilesRepository;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AuthentificationService authentificationService;

    private Profiles profile;
    private Address address;

    @BeforeEach
    public void setUp() {
        // Initialisation des objets
        address = new Address();  // Crée un objet Address fictif pour le test
        address.setStreet("123 Main St");
        address.setCity("Paris");
        address.setZip("75001");
        address.setCountry("France");

        profile = new Profiles();  // Crée un objet Profiles fictif pour le test
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setEmail("john.doe@example.com");
        profile.setPhone("123-456-7890");
        profile.setPassword("password123");  // Mot de passe non haché
        profile.setAddress(address);  // Ajouter une adresse pour tester le cas où une adresse est présente
    }

    @Test
    @DisplayName("Test de réussite de Création de profile")
    public void testCreateProfile_WithAddress() {
        // Configuration du mock pour l'encodeur de mot de passe
        when(bCryptPasswordEncoder.encode(profile.getPassword())).thenReturn("password123");

        // Simuler la création de l'adresse via le service
        when(addressService.create(any(Address.class))).thenReturn(address);

        // Exécution de la méthode à tester
        Profiles result = authentificationService.create(profile);

        // Vérification des interactions et de l'état
        verify(bCryptPasswordEncoder).encode(profile.getPassword());
        verify(validationsService).validateEmail(profile.getEmail());
        verify(validationsService).validatePhoneNumber(profile.getPhone());
        verify(profilesRepository).save(result);

        // Assertions
        assertNotNull(result);
        assertEquals("password123", result.getPassword());
    }

    @Test
    @DisplayName("Test d'échec de Création de profile")
    public void testCreateProfile_WithoutAddress() {
        // Configuration du mock pour l'encodeur de mot de passe
        profile.setAddress(null);
        when(bCryptPasswordEncoder.encode(profile.getPassword())).thenReturn("password123");

        // Exécution de la méthode à tester
        Profiles result = authentificationService.create(profile);

        // Vérification des interactions et de l'état
        verify(bCryptPasswordEncoder).encode(profile.getPassword());
        verify(validationsService).validateEmail(profile.getEmail());
        verify(validationsService).validatePhoneNumber(profile.getPhone());
        verify(profilesRepository).save(result);

        // Assertions
        assertNotNull(result);
        assertEquals("password123", result.getPassword());
    }
}
