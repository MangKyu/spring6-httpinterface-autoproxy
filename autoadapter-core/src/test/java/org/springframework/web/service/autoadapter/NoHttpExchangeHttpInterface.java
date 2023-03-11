package org.springframework.web.service.autoadapter;

import org.springframework.web.service.annotation.GetExchange;

public interface NoHttpExchangeHttpInterface {

    @GetExchange("/hello")
    String getLatest();

}
