package io.github.mangkyu.httpinterface.autofactory.core;

import org.springframework.web.service.annotation.GetExchange;

public interface NoHttpExchangeHttpInterface {

    @GetExchange("/hello")
    String getLatest();

}
