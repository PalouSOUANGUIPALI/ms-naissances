package com.asp_dev.naissances.notifications;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;

import java.util.Map;
@Slf4j
@Component
public class EmailServices {

    public void sendEmail(Map<String, String> parameters) {
        String Message = this.buildEmail(parameters);
        log.info("Le message est {}", Message);
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
}
