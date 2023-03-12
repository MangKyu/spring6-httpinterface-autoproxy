package io.github.mangkyu.httpinterface.autofactory.autoconfigure;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class HttpInterfaceAutoFactoryAutoConfigureTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(HttpInterfaceAutoFactoryAutoConfigure.class));

    @Test
    void autoConfigureDisabled() {
        contextRunner.withSystemProperties("spring.web.httpinterface.enabled:false")
                .run(context -> {
                    assertThat(context).doesNotHaveBean(HttpInterfaceAutoFactoryAutoConfigure.class);
                    assertThat(context).doesNotHaveBean(HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar.class);
                });
    }

}