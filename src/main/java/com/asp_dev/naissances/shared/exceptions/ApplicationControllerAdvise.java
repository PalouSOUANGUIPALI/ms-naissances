package com.asp_dev.naissances.shared.exceptions;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
public class ApplicationControllerAdvise {

    /*
        Exception pour un profil inexistant
     */
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public @ResponseBody ErrorEntity entityNotFoundExceptionHandler(EntityNotFoundException exception){
        log.error(exception.getMessage());
        // Return 404 with the exception message
        return new ErrorEntity(null, exception.getMessage(), LocalDateTime.now(), NOT_FOUND.value());
    }

    /*
        Exception pour une liste de profil inexistant
     */
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ProfilesNotFoundException.class)
    public @ResponseBody ErrorEntity profilesNotFoundExceptionHandler(ProfilesNotFoundException ex) {
        log.error(ex.getMessage());
        // Return 404 with the exception message
        return new ErrorEntity(null, ex.getMessage(), LocalDateTime.now(), NOT_FOUND.value());
    }

    /*
        Exception pour les vérifications de mail et de téléphone
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ErrorEntity runtimeExceptionHandler(RuntimeException ex) {
        log.error(ex.getMessage());
        return new ErrorEntity(null, ex.getMessage(), LocalDateTime.now(), BAD_REQUEST.value());
    }

    /*
        Exception pour une violation de requête en base de donnée
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public @ResponseBody ErrorEntity dataIntegrityViolationHandler(DataIntegrityViolationException ex) {
        log.error(ex.getMessage());
        return new ErrorEntity(null, "La donnée que vous avez saisie est invalide",
                LocalDateTime.now(), BAD_REQUEST.value());
    }
}
