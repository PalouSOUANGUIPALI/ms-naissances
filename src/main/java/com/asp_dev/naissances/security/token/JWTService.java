package com.asp_dev.naissances.security.token;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class JWTService {

    JwtEncoder jwtEncoder;


    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Set<String> userPermissions = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet
                .builder()
                .subject(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .claim("username", authentication.getName())
                .claim("scp", userPermissions)
                .issuer("self")
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }
}
