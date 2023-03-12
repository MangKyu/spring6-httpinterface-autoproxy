package io.github.mangkyu.httpinterface.autoproxy.core;

import org.springframework.web.service.annotation.GetExchange;

public interface NoHttpExchangeHttpInterface {

    @GetExchange("/hello")
    String getLatest();

}
