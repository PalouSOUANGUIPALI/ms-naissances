package com.asp_dev.naissances.services;


import com.asp_dev.naissances.entities.Profiles;
import com.asp_dev.naissances.repository.ProfilesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfilesServiceTest {

    @Mock
    private ProfilesRepository profilesRepository;

    @InjectMocks
    private ProfilesService profilesService;

    private Profiles testProfile;


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

}