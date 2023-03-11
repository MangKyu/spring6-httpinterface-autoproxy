package io.github.mangkyu.httpinterfacesample;/*
 *
 *  io.github.mangkyu.httpinterfacesample.ErAPI.java 2023-03-11
 *
 *  Copyright 2023 WorksMobile Corp. All rights Reserved.
 *  WorksMobile PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

import org.springframework.web.service.annotation.GetExchange;

import java.util.Map;

public interface ErAPI {

    @GetExchange("/v6/latest")
    Map getLatest();
}
