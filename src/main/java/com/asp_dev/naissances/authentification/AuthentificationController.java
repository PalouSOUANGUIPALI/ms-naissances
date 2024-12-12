package com.asp_dev.naissances.authentification;

import com.asp_dev.naissances.profiles.entities.Profiles;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "auth", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class AuthentificationController {
    private final AuthentificationService authentificationService;

    /*
       Creation de profile
       @Params : profiles
       @Return : void
    */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "sign-up")
    public void create(@RequestBody Profiles profiles) {
        log.trace(String.valueOf(profiles.toString()));
        this.authentificationService.create(profiles);

    }
}
