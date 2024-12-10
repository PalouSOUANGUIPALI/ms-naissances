package com.asp_dev.naissances.shared.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidationsServiceTest {
    @InjectMocks
    ValidationsService validationsService;


    // Test de réussite
    @Test
    void shouldTestThatEmailIsValid() {
        // Arrange (definition de la variable)
        String email = "test@test.com";

        // Act (Exécution de la méthode ou effectuer le test)
        this.validationsService.validateEmail(email);

        // Assert (Vérification des résultats
        assertTrue(true);
    }

    // Test d'echec
    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        // Arrange (definition de la variable)
        String invalidEmail = null;

        // Act (Exécution de la méthode ou effectuer le test)
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> this.validationsService.validateEmail(invalidEmail));

        // Assert (Vérification des résultats
        assertEquals(exception.getMessage(), "L'adresse email est invalide");

    }

    // Test de réussite
    @Test
    void shouldTestThatPhoneNumberIsValid() {
        // Arrange (definition de la variable)
        String phoneNumber = "123456789";

        // Acte (Exécution de la méthode ou effectuer le test)
        this.validationsService.validatePhoneNumber(phoneNumber);

        // Acte (Exécution de la méthode ou effectuer le test)
        assertTrue(true);
    }

    // Test d'echec
    @Test
    void shouldThrowExceptionWhenPhoneNumberIsInvalid() {
        // Arrange (definition de la variable)
        String invalidPhoneNumber = null;

        // Acte (Exécution de la méthode ou effectuer le test)
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> this.validationsService.validatePhoneNumber(invalidPhoneNumber));

        // Acte (Exécution de la méthode ou effectuer le test)
        assertEquals(exception.getMessage(), "Le numero de téléphone est invalide");
    }
}