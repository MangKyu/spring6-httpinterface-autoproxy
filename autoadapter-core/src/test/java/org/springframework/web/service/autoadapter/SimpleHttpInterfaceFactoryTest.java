package org.springframework.web.service.autoadapter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleHttpInterfaceFactoryTest {

    @Test
    void createClient() {
        // given
        SimpleHttpInterfaceFactory generator = new SimpleHttpInterfaceFactory();

        // when
        LocalhostHttpInterface result = generator.create(LocalhostHttpInterface.class, WebClient.create());

        // then
        assertThat(result).isNotNull();
    }

    @MethodSource("invalidHttpInterface")
    @ParameterizedTest
    void createClient_Fail_NoHttpExchangeHttpInterface(Class<?> httpInterface, Class<?> e) {
        // given
        SimpleHttpInterfaceFactory factory = new SimpleHttpInterfaceFactory();

        // when
        assertThatThrownBy(() -> factory.create(httpInterface, WebClient.create()))
                .isInstanceOf(e);

        // then
    }

    private static List<Arguments> invalidHttpInterface() {
        return List.of(
                Arguments.of(EmptyUrlHttpExchangeHttpInterface.class, IllegalArgumentException.class),
                Arguments.of(NoHttpExchangeHttpInterface.class, IllegalStateException.class)
        );
    }

}