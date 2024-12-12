package com.asp_dev.naissances.services;


import com.asp_dev.naissances.authentification.AuthentificationService;
import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.repository.ProfilesRepository;
import com.asp_dev.naissances.profiles.services.ProfilesService;
import com.asp_dev.naissances.shared.entities.Address;
import com.asp_dev.naissances.shared.services.AddressService;
import com.asp_dev.naissances.shared.services.ValidationsService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProfilesServiceTest {

    @Mock
    private ProfilesRepository profilesRepository;

    @Mock
    AuthentificationService authentificationService;

    @Mock
    private AddressService addressService;  // Mock du service d'adresse

    @Mock
    private ValidationsService validationsService;  // Mock du service de validation

    @InjectMocks
    private ProfilesService profilesService;

    private Profiles existingProfile;
    private Profiles updatedProfile;
    private Profiles profile;
    private Profiles profilesAndAddress;
    private Address address;


    @Test
    void should_find_all_profiles() {
        //Arrange
        when(this.profilesRepository.findAll())
                .thenReturn(
                        List.of(
                                Profiles.builder()
                                        .email("test@test.com")
                                        .firstName("jone")
                                        .lastName("test")
                                        .phone("123456789")
                                        .build()));

        //Act
        List<Profiles> profiles = this.profilesService.search();


        //Assert
        assertEquals(1, profiles.size());
    }


    @BeforeEach
    public void setUp() {
        // Initialisation des données pour le test
        existingProfile = new Profiles();
        existingProfile.setId(1);
        existingProfile.setEmail("old_email@example.com");
        existingProfile.setFirstName("OldName");
        existingProfile.setLastName("OldLastName");
        existingProfile.setPhone("12345");

        updatedProfile = new Profiles();
        updatedProfile.setEmail("new_email@example.com");
        updatedProfile.setFirstName("NewName");
        updatedProfile.setLastName("NewLastName");
        updatedProfile.setPhone("67890");


        // Initialisation des données pour le test de recherche d'un profile
        profile = new Profiles();
        profile.setId(3);
        profile.setEmail("test@example.com");
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setPhone("12345");

    }

    // Test de réussite
    @Test
    public void testUpdateProfile_Success() {
        // Arrange
        // Simuler le comportement de la méthode `findById` du repository
        when(profilesRepository.findById(1)).thenReturn(Optional.of(existingProfile));

        // Act
        Profiles result = profilesService.updateProfile(1, updatedProfile);

        // Assert
        assertNotNull(result);
        assertEquals("new_email@example.com", result.getEmail());
        assertEquals("NewName", result.getFirstName());
        assertEquals("NewLastName", result.getLastName());
        assertEquals("67890", result.getPhone());

        // Vérifie que `save` a été appelé une fois avec l'objet mis à jour
        verify(profilesRepository, times(1)).save(existingProfile);
    }

    // Test d'echec
    @Test
    public void testUpdateProfile_NotFound() {
        // Arrange
        // Simuler un profil non trouvé
        when(profilesRepository.findById(2)).thenReturn(Optional.empty());

        // Act
        Profiles result = profilesService.updateProfile(2, updatedProfile);

        // Assert
        assertNull(result);  // La méthode doit retourner null si le profil n'est pas trouvé
        // Vérifie que `save` n'a pas été appelé
        verify(profilesRepository, never()).save(any());
    }



    // Test de réussite de recherche d'un profile
    @Test
    public void testSearchOneProfile_Found() {
        // Arrange
        // Simuler que le repository retourne un profil lorsque l'ID 1 est recherché
        when(profilesRepository.findById(3)).thenReturn(java.util.Optional.of(profile));

        // Act
        Profiles result = profilesService.searchOneProfile(3);

        // Assert
        assertNotNull(result);  // Le résultat ne doit pas être nul
        assertEquals(3, result.getId());  // L'ID doit être 1
        assertEquals("test@example.com", result.getEmail());  // L'email doit correspondre
        verify(profilesRepository, times(1)).findById(3);  // Vérifier que `findById` a été appelé une fois
    }

    // Test d'échec de recherche d'un profile
    @Test
    public void testSearchOneProfile_NotFound() {
        // Arrange
        // Simuler que le repository ne trouve pas de profil pour l'ID 2
        when(profilesRepository.findById(2)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        // Vérifier qu'une exception EntityNotFoundException est lancée
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            profilesService.searchOneProfile(2);
        });

        // Vérifier le message de l'exception
        assertEquals("Aucune response ne correspond aux paramètres fournis", exception.getMessage());

        // Vérifier que `findById` a bien été appelé une fois
        verify(profilesRepository, times(1)).findById(2);
    }


    // Test de réussite de suppression d'un profile
    @Test
    public void testDeleteProfile_Success() {
        // Arrange
        // Simuler que le profil existe en base
        when(profilesRepository.findById(3)).thenReturn(java.util.Optional.of(profile));

        // Act
        profilesService.deleteProfile(3);

        // Assert
        // Vérifier que la méthode `delete` a été appelée une seule fois avec le profil attendu
        verify(profilesRepository, times(1)).delete(profile);
    }

    // Test d'échec de suppression d'un profile
    @Test
    public void testDeleteProfile_NotFound() {
        // Arrange
        // Simuler qu'aucun profil n'a été trouvé pour l'ID 2
        when(profilesRepository.findById(2)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        // Vérifier qu'une exception EntityNotFoundException est lancée
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            profilesService.deleteProfile(2);
        });

        // Vérifier que le message de l'exception correspond à ce qui est attendu
        assertEquals("Aucune response ne correspond aux paramètres fournis", exception.getMessage());

        // Vérifier que `delete` n'a pas été appelé
        verify(profilesRepository, never()).delete(any());
    }
}