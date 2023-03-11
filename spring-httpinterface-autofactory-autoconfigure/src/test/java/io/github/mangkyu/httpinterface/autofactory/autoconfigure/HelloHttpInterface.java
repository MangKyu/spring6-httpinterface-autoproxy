package io.github.mangkyu.httpinterface.autofactory.autoconfigure;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("http://localhost:7070")
public interface HelloHttpInterface {

    @GetExchange("/hello")
    String getLatest();

}
