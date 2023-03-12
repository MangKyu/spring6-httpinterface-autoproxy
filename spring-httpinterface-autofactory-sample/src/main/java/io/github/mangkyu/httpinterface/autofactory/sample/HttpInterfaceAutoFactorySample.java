package io.github.mangkyu.httpinterface.autofactory.sample;

import io.github.mangkyu.httpinterface.autofactory.autoconfigure.HttpInterfaceAutoFactoryAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

// Have to resolve why autoconfiguration is not automatically imported, AutoConfigure class should be registered by spring.factories
@Import(HttpInterfaceAutoFactoryAutoConfigure.class)
@SpringBootApplication
public class HttpInterfaceAutoFactorySample {

    @Bean
    WebClient webClient() {
        return WebClient.create();
    }

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(HttpInterfaceAutoFactorySample.class, args)) {

        }
    }

}