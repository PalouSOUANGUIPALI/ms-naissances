package com.asp_dev.naissances.repository;


import com.asp_dev.naissances.entities.Profiles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static org.springframework.boot.jdbc.EmbeddedDatabaseConnection.H2;

@DataJpaTest
@AutoConfigureTestDatabase(connection = H2)
public class ProfilesRepositoryTest {
    @Autowired
    ProfilesRepository profilesRepository;


    @Test
    void shouldReturnListOfProfiles() {
        // Arrange
        Profiles profilesOne = Profiles.builder()
                .email("test@test.com")
                .build();

        Profiles profilesTwo = Profiles.builder()
                .email("two@test.com")
                .build();
        this.profilesRepository.saveAll(List.of(profilesOne, profilesTwo));

         // Act
        List<Profiles> profiles = (List<Profiles>) this.profilesRepository.findAll();

        // Assert (assert 9 parce que sept autres profiles existent déjà dans la base)
        Assertions.assertEquals(9, profiles.size());

    }

}