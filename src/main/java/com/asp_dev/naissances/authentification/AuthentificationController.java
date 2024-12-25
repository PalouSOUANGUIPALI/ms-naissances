package com.asp_dev.naissances.authentification;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(path = "auth", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class AuthentificationController {

    private final AuthentificationService authentificationService;
    private final AuthenticationManager authenticationManager;

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
    public void login(@RequestBody Map<String, String> connectionParameters) {
        Authentication authentification = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        connectionParameters.get("email"),
                        connectionParameters.get("password"))
                );
        log.info("Utilisateur connecté est : {}", authentification.getName());
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
