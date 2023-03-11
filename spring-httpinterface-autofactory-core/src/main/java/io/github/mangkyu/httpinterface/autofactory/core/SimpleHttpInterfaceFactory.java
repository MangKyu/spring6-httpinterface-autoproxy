package io.github.mangkyu.httpinterface.autofactory.core;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class SimpleHttpInterfaceFactory {

    public <S> S create(Class<S> clientClass, WebClient webClient) {
        HttpExchange httpExchange = AnnotationUtils.getAnnotation(clientClass, HttpExchange.class);
        if (httpExchange == null) {
            throw new IllegalStateException("HttpExchange annotation not found");
        }

        if (!StringUtils.hasText(httpExchange.url())) {
            throw new IllegalArgumentException("HttpExchange url is empty");
        }

        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient.mutate().baseUrl(httpExchange.url()).build()))
                .build();

        return proxyFactory.createClient(clientClass);
    }
}
