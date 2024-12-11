package com.asp_dev.naissances.controllers;

import com.asp_dev.naissances.entities.Profiles;
import com.asp_dev.naissances.services.ProfilesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProfilesControllerTest {

    @Mock
    private ProfilesService profilesService;  // Mock du service

    @InjectMocks
    private ProfilesController profilesController;  // Le contrôleur à tester

    private MockMvc mockMvc;  // Permet de simuler des appels HTTP

    private Profiles profile;


    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Initialisation du MockMvc
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(profilesController).build();

        // Initialisation d'un profil pour les tests
        profile = new Profiles();
        profile.setId(1);
        profile.setEmail("test@example.com");
        profile.setFirstName("John");
        profile.setLastName("Doe");
        profile.setPhone("123456789");

    }



    @Test
    public void testCreateProfile() throws Exception {
        // Créer un objet Profiles fictif
        Profiles profile = new Profiles();
        profile.setFirstName("John Doe");
        profile.setEmail("john.doe@example.com");

        // Convertir l'objet en JSON
        String profileJson = objectMapper.writeValueAsString(profile);

        // Effectuer une requête POST
        mockMvc.perform(post("/profiles/create-profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isCreated()); // Vérifier que le statut HTTP est 201 (Created)

        // Vérifier que le service a été appelé avec le bon objet Profiles
        verify(profilesService, times(1)).create(any(Profiles.class));
    }


    @Test
    public void testGetAllProfiles() throws Exception {
        // Arrange
        when(profilesService.search()).thenReturn(Collections.singletonList(profile));  // Simuler la réponse du service

        // Act & Assert
        ResultActions resultActions = mockMvc.perform(get("/profiles/get-all-profiles"))
                .andExpect(status().isOk())  // Vérifie que le code de statut HTTP est 200 (OK)
                .andExpect(jsonPath("$[0].email").value("test@example.com"));  // Vérifie que l'email du premier profil est correct

        // Afficher la réponse dans la console
        resultActions.andDo(MockMvcResultHandlers.print());  // Cette ligne va afficher la réponse complète dans la console

        verify(profilesService, times(1)).search();  // Vérifie que `search` a été appelé une fois
    }


    @Test
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
    public void testDeleteProfile() throws Exception {
        // Arrange
        doNothing().when(profilesService).deleteProfile(1);  // Simuler que le profil est supprimé

        // Act & Assert
        mockMvc.perform(delete("/profiles/delete-profile/1"))
                .andExpect(status().isNoContent());  // Vérifie que le code de statut HTTP est 204 (NO_CONTENT)

        verify(profilesService, times(1)).deleteProfile(1);  // Vérifie que `deleteProfile` a été appelé une fois
    }
}
