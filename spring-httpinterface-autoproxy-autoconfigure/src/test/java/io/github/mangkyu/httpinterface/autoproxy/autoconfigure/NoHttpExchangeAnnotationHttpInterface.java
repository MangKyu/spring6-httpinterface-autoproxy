package io.github.mangkyu.httpinterface.autoproxy.autoconfigure;

import org.springframework.web.service.annotation.GetExchange;

public interface NoHttpExchangeAnnotationHttpInterface {

    @GetExchange("/hello")
    String getLatest();

}
