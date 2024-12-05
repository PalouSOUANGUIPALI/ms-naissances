package com.asp_dev.naissances.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ApplicationControllerAdvise {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody ErrorEntity entityNotFoundException(EntityNotFoundException exception){
        log.error(exception.getMessage());
        // Return 404 with the exception message
        return new ErrorEntity(null, exception.getMessage(), LocalDateTime.now(), NOT_FOUND.value());
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ProfilesNotFoundException.class)
    public @ResponseBody ErrorEntity profilesNotFoundException(ProfilesNotFoundException ex) {
        log.error(ex.getMessage());
        // Return 404 with the exception message
        return new ErrorEntity(null, ex.getMessage(), LocalDateTime.now(), NOT_FOUND.value());
    }

}
