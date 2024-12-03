package com.asp_dev.naissances.controllers;

import com.asp_dev.naissances.entities.Profiles;
import com.asp_dev.naissances.services.ProfilesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "profiles")
public class ProfilesController {

    private final ProfilesService profilesService;


    @PostMapping(path = "all-profiles")
    public void create(@RequestBody Profiles profiles) {
        log.trace(String.valueOf(profiles.toString()));
        profilesService.create(profiles);

    }
}
