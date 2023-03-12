package io.github.mangkyu.httpinterface.autoproxy.core;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface EmptyUrlHttpExchangeHttpInterface {

    @GetExchange("/hello")
    String getLatest();

}
