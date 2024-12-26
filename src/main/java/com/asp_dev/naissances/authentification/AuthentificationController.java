package com.asp_dev.naissances.authentification;

import com.asp_dev.naissances.profiles.dto.ProfilesDTO;
import com.asp_dev.naissances.security.token.JWTService;
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
    private final JWTService jwtService;

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
    public @ResponseBody Map<String, String> login(@RequestBody Map<String, String> connectionParameters) {
        Authentication authentification = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        connectionParameters.get("email"),
                        connectionParameters.get("password"))
                );
        String bearer = jwtService.generateToken(authentification);

        return Map.of("bearer", bearer);
    }

    /*
     Activation du profile avec le code à six chiffres envoyé sur la boîte mail du user
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
