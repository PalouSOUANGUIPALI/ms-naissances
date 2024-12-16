package com.asp_dev.naissances.authentification;

import com.asp_dev.naissances.profiles.entities.Profiles;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false) // Permet de désactiver la sécurité spring security
@ExtendWith(MockitoExtension.class)
class AuthentificationControllerTest {

    private MockMvc mockMvc;  // Permet de simuler des appels HTTP

    @InjectMocks  // Injection de la classe controller testée
    private AuthentificationController authentificationController;

    @Mock  // Injection du mock du service
    AuthentificationService authentificationService;


    // Mappers les objets en JSON et inversement
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        // Initialisation du MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(authentificationController).build();

        // Initialisation du mock mapper de json en objet et inversement
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("Création de profile") // donner un nom personnalisé à la méthode de test
    public void testCreateProfile() throws Exception {
        // Arrange
        // Créer un objet Profiles fictif
        Profiles profile = new Profiles();
        profile.setFirstName("John Doe");
        profile.setEmail("john.doe@example.com");

        // Convertir l'objet en JSON
        String profileJson = objectMapper.writeValueAsString(profile);


        // Act & Assert
        // Effectuer une requête POST
        mockMvc.perform(post("/auth/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(profileJson))
                .andExpect(status().isCreated()); // Vérifier que le statut de la réponse HTTP est 201 (Created)

        // Vérifier que le service a été appelé une fois avec le bon objet Profiles
        verify(authentificationService, times(1)).create(any(com.asp_dev.naissances.profiles.dto.ProfilesDTO.class));
    }
}