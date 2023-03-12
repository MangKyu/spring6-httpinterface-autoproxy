package io.github.mangkyu.httpinterface.autofactory.sample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HttpInterfaceAutoFactorySampleTest {

    @Autowired
    private ExchangeRateHttpClient httpClient;

    @Test
    void httpInterfaceBeanGenerated() {
        assertThat(httpClient).isNotNull();
        assertThat(((Map<String, Map<String, Double>>) httpClient.getLatest()).get("rates").get("KRW")).isNotNull();
    }

}