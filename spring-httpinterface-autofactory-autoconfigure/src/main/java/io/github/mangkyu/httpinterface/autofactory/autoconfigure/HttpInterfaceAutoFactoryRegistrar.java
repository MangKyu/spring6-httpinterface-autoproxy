package io.github.mangkyu.httpinterface.autofactory.autoconfigure;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.Set;

@Configuration(proxyBeanMethods = false)
public class HttpInterfaceAutoFactoryRegistrar {

    public String findBasePackage(ApplicationContext applicationContext) {
        return applicationContext.getBeansWithAnnotation(SpringBootApplication.class)
                .values()
                .stream()
                .map(v -> v.getClass().getPackage().getName())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("SpringBootApplication annotation not found"));
    }

    public Set<BeanDefinition> findHttpInterfaceBeanDefinitions(String basePackage, ApplicationContext context) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false, context.getEnvironment()) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface()
                        && beanDefinition.getMetadata().hasAnnotation(HttpExchange.class.getName());
            }
        };

        scanner.addIncludeFilter(new AnnotationTypeFilter(HttpExchange.class));
        return scanner.findCandidateComponents(basePackage);
    }

}
