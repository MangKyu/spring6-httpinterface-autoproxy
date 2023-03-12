package io.github.mangkyu.httpinterface.autofactory.autoconfigure;

import io.github.mangkyu.httpinterface.autofactory.core.HttpInterfaceFactory;
import io.github.mangkyu.httpinterface.autofactory.core.SimpleHttpInterfaceFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

@ConditionalOnProperty(
        prefix = "spring.web.httpinterface",
        name = {"enabled"},
        havingValue = "true",
        matchIfMissing = true
)
@Import({HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar.class})
public class HttpInterfaceAutoFactoryAutoConfigure {

    @Bean
    @ConditionalOnMissingBean(HttpInterfaceFactory.class)
    public HttpInterfaceFactory httpInterfaceFactory(WebClient webClient) {
        return new SimpleHttpInterfaceFactory(webClient);
    }

}
