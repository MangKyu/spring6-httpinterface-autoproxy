package io.github.mangkyu.httpinterface.autoproxy.autoconfigure;

import io.github.mangkyu.httpinterface.autoproxy.core.HttpInterfaceFactory;
import io.github.mangkyu.httpinterface.autoproxy.core.SimpleHttpInterfaceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

@ConditionalOnProperty(
        prefix = "spring.web.httpinterface",
        name = {"enabled"},
        havingValue = "true",
        matchIfMissing = true
)
@Import({HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar.class})
@Configuration(proxyBeanMethods = false)
public class HttpInterfaceAutoProxyAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(HttpInterfaceFactory.class)
    public HttpInterfaceFactory httpInterfaceFactory(WebClient webClient) {
        return new SimpleHttpInterfaceFactory(webClient);
    }

}
