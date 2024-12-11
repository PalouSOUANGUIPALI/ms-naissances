package com.asp_dev.naissances.controllers;

import com.asp_dev.naissances.entities.Profiles;
import com.asp_dev.naissances.services.ProfilesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(value = "profiles")
public class ProfilesController {

    private final ProfilesService profilesService;


    /*
        Creation de profile
        @Params : profiles
        @Return : void
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "create-profiles", consumes = APPLICATION_JSON_VALUE)
    public void create(@RequestBody Profiles profiles) {
        log.trace(String.valueOf(profiles.toString()));
        this.profilesService.create(profiles);

    }

     /*
       liste de profile
        @Params : void
        @Return : List<Profiles>
     */
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(path = "get-all-profiles", produces = APPLICATION_JSON_VALUE)
    public List<Profiles> search() {
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
