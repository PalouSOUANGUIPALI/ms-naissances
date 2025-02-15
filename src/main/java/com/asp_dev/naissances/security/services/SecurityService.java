package com.asp_dev.naissances.security.services;

import com.asp_dev.naissances.profiles.entities.Profiles;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    public Profiles getCurrentUser(){
        return
                (Profiles) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

    }
}
