package com.asp_dev.naissances.configurations;

import com.asp_dev.naissances.notifications.Mailpitclient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ApplicationConfigurations {

    String mailClientUrl = "http://localhost:8025/";

    @Bean
    Mailpitclient mailpitclient(){
        RestClient restClient = RestClient.create(mailClientUrl);

        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();
        return httpServiceProxyFactory.createClient(Mailpitclient.class);
    }
}
