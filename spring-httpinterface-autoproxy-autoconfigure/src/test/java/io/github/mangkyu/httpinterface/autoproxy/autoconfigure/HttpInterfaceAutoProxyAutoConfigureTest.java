package io.github.mangkyu.httpinterface.autoproxy.autoconfigure;

import io.github.mangkyu.httpinterface.autoproxy.core.SimpleHttpInterfaceFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

class HttpInterfaceAutoProxyAutoConfigureTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(BaseConfiguration.class)
            .withConfiguration(AutoConfigurations.of(HttpInterfaceAutoProxyAutoConfigure.class));

    @Test
    void autoConfigureDisabled() {
        contextRunner.withSystemProperties("spring.web.httpinterface.enabled:false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(HttpInterfaceAutoProxyAutoConfigure.class);
                    assertThat(context).doesNotHaveBean(HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar.class);
                });
    }

    @Test
    void autoConfigureEnabled_EmptyProperty() {
        contextRunner.run(context -> {
                    assertThat(context).hasSingleBean(HttpInterfaceAutoProxyAutoConfigure.class);
                    assertThat(context).hasSingleBean(HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar.class);
                    assertThat(context).hasSingleBean(HelloHttpInterface.class);
                });
    }

    @Test
    void autoConfigureEnabled_enabledProperty() {
        contextRunner.withSystemProperties("spring.web.httpinterface.enabled:true")
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpInterfaceAutoProxyAutoConfigure.class);
                    assertThat(context).hasSingleBean(HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar.class);
                });
    }

    @Test
    void autoConfigureEnabled_httpInterfaceAlreadyRegistered() {
        contextRunner.withBean(SimpleHttpInterfaceFactory.class, WebClient.create())
                .withSystemProperties("spring.web.httpinterface.enabled:true")
                .run(context -> {
                    assertThat(context).hasSingleBean(HttpInterfaceAutoProxyAutoConfigure.class);
                    assertThat(context).hasSingleBean(HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar.class);
                    assertThat(context).hasSingleBean(SimpleHttpInterfaceFactory.class);
                });
    }

    private static class BaseConfiguration {

        @Bean
        public WebClient webClient() {
            return WebClient.create();
        }

        @Bean
        public TestMainClass mainClass() {
            return new TestMainClass();
        }

    }
}