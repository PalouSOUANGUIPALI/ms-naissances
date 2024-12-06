package com.asp_dev.naissances.shared.exceptions.services;

import org.springframework.stereotype.Component;

@Component
public class ValidationsService {

    /*
        Validation de l'adresse email
     */
    public void validateEmail(String email){
        if (email == null) {
            throw new RuntimeException("L'adresse email est requise");
        }

        if (!email.contains("@") || !email.contains(".")) {
            throw new RuntimeException("Format d'email invalide");
        }

    }

    /*
        Validation du numero de téléphone
     */
    public void validatePhoneNumber(String phoneNumber){
        if (phoneNumber == null) {
            throw new RuntimeException("Le numéro de téléphone est requis");
        }
        /*if (!phoneNumber.matches("[0-9]{10}")) {
            throw new RuntimeException("Format du numéro de téléphone invalide");
        }

         */
        if (!phoneNumber.matches("^\\+?[1-9]\\d{0,2}(\\s?[-]?\\(?\\d{1,5}\\)?\\s?[-]?\\d{1,5}){1,5}$") &&
                !phoneNumber.matches("^[1-9]\\d{9,19}$")) {
            throw new RuntimeException("Format du numéro de téléphone invalide");
        } else {
            System.out.println("Numéro de téléphone valide !");
        }

    }
}
