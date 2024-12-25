package com.asp_dev.naissances.authentification;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public void create(@RequestBody ProfilesDTO profilesDTO) {
        log.trace(String.valueOf(profilesDTO.toString()));
        this.authentificationService.create(profilesDTO);

    }

    /*
      Connxion de profile/utilisateur
      @Params : Map<String, String> login
      @Return : void
   */
    @PostMapping(path = "sign-in")
    public void login(@RequestBody Map<String, String> loginProfile) {
    }

    /*
     Activation du profile avec le code à six chiffres
     @Params : Map<String, String>
     @Return : void
  */
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "activate-account")
    public void activate(@RequestBody Map<String, String> activationCode) {
        log.trace("le code d'activation du nouveau profile {} ", activationCode.get("code"));
        this.authentificationService.activateAccount(activationCode);

    }
}
