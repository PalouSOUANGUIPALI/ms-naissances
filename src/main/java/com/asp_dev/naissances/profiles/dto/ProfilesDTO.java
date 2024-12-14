package com.asp_dev.naissances.profiles.dto;

import com.asp_dev.naissances.profiles.emuns.Civility;


public record ProfilesDTO(
        Civility civility,
        String firstName,
        String lastName,
        String email,
        String phone,
        String password
) {
}
