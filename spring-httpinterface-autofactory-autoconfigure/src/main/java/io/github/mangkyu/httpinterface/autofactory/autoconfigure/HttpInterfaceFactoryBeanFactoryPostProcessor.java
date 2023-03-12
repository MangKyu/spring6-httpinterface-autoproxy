package io.github.mangkyu.httpinterface.autofactory.autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.mangkyu.httpinterface.autofactory.core.HttpInterfaceFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Set;

public class HttpInterfaceFactoryBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    private final HttpInterfaceFactory factory;
    private final Set<BeanDefinition> beanDefinitions;

    public HttpInterfaceFactoryBeanFactoryPostProcessor(HttpInterfaceFactory factory, Set<BeanDefinition> beanDefinitions) {
        this.factory = factory;
        this.beanDefinitions = beanDefinitions;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            Class<?> httpInterfaceClass = findHttpInterfaceClass(beanDefinition);
            beanFactory.registerSingleton(beanDefinition.getBeanClassName(), factory.create(httpInterfaceClass));
        }
    }

    private Class<?> findHttpInterfaceClass(BeanDefinition beanDefinition) {
        if (!StringUtils.hasText(beanDefinition.getBeanClassName())) {
            throw new IllegalStateException("BeanClassName is empty");
        }

        try {
            return ClassUtils.forName(beanDefinition.getBeanClassName(), this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
