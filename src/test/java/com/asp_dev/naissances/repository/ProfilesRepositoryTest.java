package com.asp_dev.naissances.repository;


import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.repository.ProfilesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@DataJpaTest
@AutoConfigureTestDatabase(connection = H2)
public class ProfilesRepositoryTest {
    @Autowired
    ProfilesRepository profilesRepository;

    @BeforeEach
    void setUp() {
        Profiles profilesOne = Profiles.builder()
                .email("test@test.com")
                .build();

        Profiles profilesTwo = Profiles.builder()
                .email("two@test.com")
                .build();
        this.profilesRepository.saveAll(List.of(profilesOne, profilesTwo));
    }

    @AfterEach
    void tearDown() {
        this.profilesRepository.deleteAll();
    }


    @Test
    void shouldReturnListOfProfiles() {
        // Arrange


         // Act
        List<Profiles> profiles = (List<Profiles>) this.profilesRepository.findAll();

        // Assert (assert 9 parce que sept autres profiles existent déjà dans la base)
        Assertions.assertEquals(9, profiles.size());

    }

    @Test
    void shouldReturnProfileByEmail() {
        // Arrange

        // Acte
        Optional<Profiles> profiles = this.profilesRepository.findByEmail("two@test.com");


        // Assert
        Assertions.assertTrue(profiles.isPresent());
    }

    @Test
    void shouldReturnEmptyProfileByEmail() {
        // Arrange

        // Act
        Optional<Profiles> profiles = this.profilesRepository.findByEmail("no@test.com");


        // Assert
        Assertions.assertTrue(profiles.isEmpty());
    }

}