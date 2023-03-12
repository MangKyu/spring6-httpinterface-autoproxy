package io.github.mangkyu.httpinterface.autoproxy.sample;

import io.github.mangkyu.httpinterface.autoproxy.autoconfigure.HttpInterfaceAutoProxyAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

// Have to resolve why autoconfiguration is not automatically imported, AutoConfigure class should be registered by spring.factories
@Import(HttpInterfaceAutoProxyAutoConfigure.class)
@SpringBootApplication
public class HttpInterfaceAutoProxySample {

    @Bean
    WebClient webClient() {
        return WebClient.create();
    }

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(HttpInterfaceAutoProxySample.class, args)) {

        }
    }

}