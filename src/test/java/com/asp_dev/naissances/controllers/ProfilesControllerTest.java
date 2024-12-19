package com.asp_dev.naissances.controllers;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.profiles.emuns.Civility;
import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.controllers.ProfilesController;
import com.asp_dev.naissances.profiles.services.ProfilesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProfilesControllerTest {

    @Mock
    private ProfilesService profilesService;  // Mock du service


    @InjectMocks
    private ProfilesController profilesController;  // Le contrôleur à tester

    private MockMvc mockMvc;  // Permet de simuler des appels HTTP

    private Profiles profile;


    private Set<ProfilesDTO> mockProfiles;


    @BeforeEach
    public void setUp() {
        // Initialisation du MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(profilesController).build();

        // Initialisation d'un profil pour les tests update, delete, getOne-profile
        profile = new Profiles();
        profile.setId(1);
        profile.setEmail("test@example.com");
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setPhone("123456789");



        // Initialisation des profils fictifs pour le test testGetAllProfiles
        mockProfiles = new HashSet<>();

        ProfilesDTO profile1 = new ProfilesDTO(
                Civility.MR,
                "John",
                "Doe",
                "john.doe@example.com",
                "1234567890",
                "password123"
        );
        ProfilesDTO profile2 = new ProfilesDTO(
                Civility.MR,
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "0987654321",
                "password456"
        );
        mockProfiles.add(profile1);
        mockProfiles.add(profile2);

        // Simuler la méthode search() du service
        when(profilesService.search()).thenReturn(mockProfiles);

    }


    @Test
    @DisplayName("Lire une liste de profile")
    public void testGetAllProfiles() throws Exception {
        // Effectuer une requête GET et vérifier la réponse
        mockMvc.perform(get("/api/profiles/get-all-profiles")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // Vérifier le code de statut HTTP 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))  // Vérifier le type de contenu
                .andExpect(jsonPath("$[0].civility").value(Civility.MR.name()))  // Vérifier la civilité du premier profil
                .andExpect(jsonPath("$[1].civility").value(Civility.MR.name()))  // Vérifier la civilité du deuxième profil
                .andExpect(jsonPath("$[0].firstName").value("John"))  // Vérifier le prénom du premier profil
                .andExpect(jsonPath("$[1].firstName").value("Jane"))  // Vérifier le prénom du deuxième profil
                .andExpect(jsonPath("$[0].lastName").value("Doe"))  // Vérifier le nom du premier profil
                .andExpect(jsonPath("$[1].lastName").value("Doe"))  // Vérifier le nom du deuxième profil
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))  // Vérifier l'email du premier profil
                .andExpect(jsonPath("$[1].email").value("jane.doe@example.com"))  // Vérifier l'email du deuxième profil
                .andExpect(jsonPath("$[0].phone").value("1234567890"))  // Vérifier le téléphone du premier profil
                .andExpect(jsonPath("$[1].phone").value("0987654321"))  // Vérifier le téléphone du deuxième profil
                .andExpect(jsonPath("$[0].password").value("password123"))  // Vérifier le mot de passe du premier profil
                .andExpect(jsonPath("$[1].password").value("password456"));  // Vérifier le mot de passe du deuxième profil

        // Vérifier que la méthode search() a été appelée une seule fois
        verify(profilesService, times(1)).search();
    }





    @Test
    @DisplayName("Lire un profile")
    public void testGetOneProfile() throws Exception {
        // Arrange
        when(profilesService.searchOneProfile(1)).thenReturn(profile);  // Simuler la réponse du service

        // Act & Assert
        mockMvc.perform(get("/profiles/get-one-profile/1"))
                .andExpect(status().isOk())  // Vérifie que le code de statut HTTP est 200 (OK)
                .andExpect(jsonPath("$.email").value("test@example.com"));  // Vérifie que l'email du profil est correct

        verify(profilesService, times(1)).searchOneProfile(1);  // Vérifie que `searchOneProfile` a été appelé une fois
    }

    @Test
    @DisplayName("Mettre à jour un profile")
    public void testUpdateProfile() throws Exception {
        // Arrange : Créer un profil existant pour le test
        Profiles existingProfile = new Profiles();
        existingProfile.setId(1);
        existingProfile.setEmail("oldemail@example.com");
        existingProfile.setFirstName("John");
        existingProfile.setLastName("Doe");
        existingProfile.setPhone("123456789");

        // Créer un profil mis à jour
        Profiles updatedProfile = new Profiles();
        updatedProfile.setId(1);
        updatedProfile.setEmail("newemail@example.com");
        updatedProfile.setFirstName("John");
        updatedProfile.setLastName("Doe");
        updatedProfile.setPhone("987654321");

        // Simuler la réponse de la méthode updateProfile()
        when(profilesService.updateProfile(eq(1), any(Profiles.class))).thenReturn(updatedProfile);

        // Act & Assert : Appel à l'API pour mettre à jour le profil et vérification de la réponse
        mockMvc.perform(put("/profiles/update-profile/1")
                        .contentType("application/json")
                        .content("{\"email\":\"newemail@example.com\",\"firstName\":\"John\",\"lastName\":\"Doe\",\"phone\":\"987654321\"}"))
                .andExpect(status().isOk())  // Vérifie que le code de statut HTTP est 200 (OK)
                .andExpect(jsonPath("$.email").value("newemail@example.com"))  // Vérifie que l'email mis à jour est correct
                .andExpect(jsonPath("$.phone").value("987654321"));  // Vérifie que le numéro de téléphone mis à jour est correct

        // Vérifie que la méthode updateProfile() a bien été appelée une fois
        verify(profilesService, times(1)).updateProfile(eq(1), any(Profiles.class));
    }


    @Test
    @DisplayName("Supprimer un profile")
    public void testDeleteProfile() throws Exception {
        // Arrange
        doNothing().when(profilesService).deleteProfile(1);  // Simuler que le profil est supprimé

        // Act & Assert
        mockMvc.perform(delete("/profiles/delete-profile/1"))
                .andExpect(status().isNoContent());  // Vérifie que le code de statut HTTP est 204 (NO_CONTENT)

        verify(profilesService, times(1)).deleteProfile(1);  // Vérifie que `deleteProfile` a été appelé une fois
    }
}
