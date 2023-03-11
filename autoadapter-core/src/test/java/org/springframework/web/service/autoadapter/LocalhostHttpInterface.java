package org.springframework.web.service.autoadapter;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("http://localhost:7070")
public interface LocalhostHttpInterface {

    @GetExchange("/hello")
    String getLatest();

}
