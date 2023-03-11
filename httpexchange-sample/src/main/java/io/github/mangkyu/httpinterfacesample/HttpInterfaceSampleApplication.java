package io.github.mangkyu.httpinterfacesample;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.Map;

@SpringBootApplication
public class HttpInterfaceSampleApplication {

    @Bean
    ApplicationRunner init(ErAPI erAPI) {
        return args -> System.out.println(((Map<String, Map<String, Double>>) erAPI.getLatest()).get("rates").get("KRW"));
    }

    @Bean
    ErAPI erAPI() {
        WebClient client = WebClient.create("https://open.er-api.com");
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(client))
                .build();

        return proxyFactory.createClient(ErAPI.class);

    }

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context = SpringApplication.run(HttpInterfaceSampleApplication.class, args)) {

        };
    }

}
