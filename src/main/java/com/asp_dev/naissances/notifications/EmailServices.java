package com.asp_dev.naissances.notifications;

import com.asp_dev.naissances.declaration.DeclarationStatus;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;

import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class EmailServices {

    private final Mailpitclient mailpitclient;


    String senderEmail = "no-reply@asp.dev.mesnaissances.com";
    String senderName = "Asp-Digital de mesnaissances.com";


    public EmailServices(Mailpitclient mailpitclient) {
        this.mailpitclient = mailpitclient;
    }

    public void sendEmail(Map<String, String> parameters) {
        String message = this.buildEmail(parameters);
        log.info("Le message est {}", message);

        Map<String, Object> emailParameters = Map.of(
                "Subject", "Votre code d'activation",
                "HTML", message,
                "text", message,
                "From", Map.of("Email", senderEmail, "Name", senderName),
                "To", List.of(Map.of("Email", parameters.get("email"), "Name", parameters.get("name")))
        );
        this.mailpitclient.send(emailParameters);

    }
    private String buildEmail(Map<String, String> parameters) {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(EmailServices.class, "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setObjectWrapper(new DefaultObjectWrapper());

        try {
            Template template = configuration.getTemplate(parameters.get("template"));
            StringWriter stringWriter = new StringWriter();
            Map<String, String> templateParameters = Map.of(
                    "name", parameters.get("name"),
                    "code", parameters.get("code"));

            template.process(templateParameters, stringWriter);
            return stringWriter.toString();
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    /*public void sendStatusNotification(DeclarationStatus declarationStatus) {
        String message = String.format(
                """
                    Bonjour %s %s, <br />
                    Votre déclaration a été traitée. <br />
                    Elle est désormais %s<br />
                    Cordialement,
                    %s
                """,
                declarationStatus.getDeclaration().getFirstParent().getFirstName(),
                declarationStatus.getDeclaration().getFirstParent().getLastName(),
                declarationStatus.getStatus().getName(),
                senderName
        );

        Map<String, Object> emailParameters = Map.of(
                "Subject", "Mis à jour de votre déclaration",
                "HTML", message,
                "text", message,
                "From",  Map.of("Email",senderEmail, "Name", senderName),
                "To", List.of(Map.of(
                        "Email", declarationStatus.getDeclaration().getFirstParent().getEmail(),
                        "Name", String.format(
                                "%s %s",
                                declarationStatus.getDeclaration().getFirstParent().getFirstName(),
                                declarationStatus.getDeclaration().getFirstParent().getLastName())
                ))
        );
        this.mailpitClient.send(emailParameters);
    }

     */

    public void sendStatusNotification(DeclarationStatus declarationStatus) {
        String NotificationDeclationStatus = String.format(
                """
                    Bonjour %s %s, <br />
                    Votre déclaration a été traitée. <br />
                    Elle est désormais %s<br />
                    Cordialement,
                    %s
                """,
                declarationStatus.getDeclaration().getFirstParent().getFirstName(),
                declarationStatus.getDeclaration().getFirstParent().getLastName(),
                declarationStatus.getStatus().getName(),
                senderName
        );

        Map<String, Object> emailParameters = Map.of(
                "Subject", "Mis à jour de votre déclaration",
                "HTML", NotificationDeclationStatus,
                "text", NotificationDeclationStatus,
                "From", Map.of("Email", senderEmail, "Name", senderName),
                "To",List.of(Map.of(
                        "Email", declarationStatus.getDeclaration().getFirstParent().getEmail(),
                        "Name", String.format(
                                "%s %s",
                                declarationStatus.getDeclaration().getFirstParent().getFirstName(),
                                declarationStatus.getDeclaration().getFirstParent().getLastName())
                ))
        );
        this.mailpitclient.send(emailParameters);
    }
}
