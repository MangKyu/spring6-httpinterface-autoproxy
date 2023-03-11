package io.github.mangkyu.httpinterface.autofactory.autoconfigure;

import org.springframework.web.service.annotation.GetExchange;

public interface NoHttpExchangeAnnotationHttpInterface {

    @GetExchange("/hello")
    String getLatest();

}
