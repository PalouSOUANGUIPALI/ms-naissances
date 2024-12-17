package com.asp_dev.naissances.profiles.controllers;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.profiles.entities.Profiles;
import com.asp_dev.naissances.profiles.services.ProfilesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "profiles")
public class ProfilesController {

    private final ProfilesService profilesService;

     /*
       liste de profile
        @Params : void
        @Return : List<Profiles>
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "get-all-profiles", produces = APPLICATION_JSON_VALUE)
    public Set<ProfilesDTO> search() {
        return this.profilesService.search();
    }

     /*
        Profile by id
        @Params : id of profile
        @Return : Profile
     */
    @GetMapping(path = "get-one-profile/{id}", produces = APPLICATION_JSON_VALUE)
    public Profiles getOneProfile(@PathVariable int id) {
        return this.profilesService.searchOneProfile(id);
    }

    /*
       Update profile
       @Params : profiles, id in the path variable
       @Return : profile whose update
    */
    @PutMapping(path = "update-profile/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Profiles updateProfile(@PathVariable int id, @RequestBody Profiles profiles) {
        return this.profilesService.updateProfile(id, profiles);
    }


    /*
       deleting profile
       @Params : id profile
       @Return : void
    */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(path = "delete-profile/{id}")
    public void deleteProfile(@PathVariable int id) {
        this.profilesService.deleteProfile(id);
    }
}
