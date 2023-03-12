package io.github.mangkyu.httpinterface.autofactory.autoconfigure;

import io.github.mangkyu.httpinterface.autofactory.core.SimpleHttpInterfaceFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HttpInterfaceAutoFactoryRegistrarTest {

    @Test
    void findBasePackage() {
        // given
        HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar registrar = new HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean(TestMainClass.class);
        context.registerBean(SimpleHttpInterfaceFactory.class, WebClient.create());
        context.refresh();

        // when
        String result = registrar.findBasePackage(context);

        // then
        assertThat(result).isEqualTo("io.github.mangkyu.httpinterface.autofactory.autoconfigure");
    }

    @Test
    void findBasePackage_NoSpringBootApplication() {
        // given
        HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar registrar = new HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar();

        // when
        assertThatThrownBy(() -> registrar.findBasePackage(new AnnotationConfigApplicationContext()))
                .isInstanceOf(IllegalStateException.class);

        // then
    }

    @Test
    void findHttpInterfaceBeanDefinitions() {
        // given
        HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar registrar = new HttpInterfaceFactoryBeanFactoryPostProcessorRegistrar();

        // when
        Set<BeanDefinition> result = registrar.findHttpInterfaceBeanDefinitions(
                "io.github.mangkyu.httpinterface.autofactory.autoconfigure",
                new AnnotationConfigApplicationContext()
        );

        // then
        assertThat(result).hasSize(1);
    }

}