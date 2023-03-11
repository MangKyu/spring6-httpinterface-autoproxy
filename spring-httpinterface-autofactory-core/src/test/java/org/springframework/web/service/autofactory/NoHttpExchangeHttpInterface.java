package org.springframework.web.service.autofactory;

import org.springframework.web.service.annotation.GetExchange;

public interface NoHttpExchangeHttpInterface {

    @GetExchange("/hello")
    String getLatest();

}
