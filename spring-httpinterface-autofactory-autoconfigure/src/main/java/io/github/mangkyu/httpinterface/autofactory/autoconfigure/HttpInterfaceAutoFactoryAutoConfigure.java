package io.github.mangkyu.httpinterface.autofactory.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;

@ConditionalOnProperty(
        prefix = "spring.web.httpinterface",
        name = {"enabled"},
        havingValue = "true",
        matchIfMissing = true
)
@Import({HttpInterfaceAutoFactoryRegistrar.class})
public class HttpInterfaceAutoFactoryAutoConfigure {

}
