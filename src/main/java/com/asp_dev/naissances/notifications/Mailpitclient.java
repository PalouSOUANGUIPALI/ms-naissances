package com.asp_dev.naissances.notifications;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;


public interface Mailpitclient {

    @PostExchange("/api/v1/send")
    void send(@RequestBody Map<String, Object> requestData);
}
