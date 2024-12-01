package com.asp_dev.naissances.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("profiles")
public class ProfilesController {

   Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(path = "all-profiles")
    public void create(@RequestBody Profile profile) {
        logger.debug(String.valueOf(profile.getId()));
        logger.trace(profile.getEmail());
        logger.error(profile.getFirstName());
        logger.info(profile.getLastName());
        logger.warn(profile.getPassword());

    }
}
