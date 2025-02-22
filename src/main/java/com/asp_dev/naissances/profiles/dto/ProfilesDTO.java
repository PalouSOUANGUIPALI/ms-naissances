package com.asp_dev.naissances.profiles.dto;

import com.asp_dev.naissances.profiles.emuns.Civility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProfilesDTO{
    Civility civility;
    String firstName;
    String lastName;
    String email;
    String phone;
    String password;
    String role;
    LocalDateTime birthDate;
}
