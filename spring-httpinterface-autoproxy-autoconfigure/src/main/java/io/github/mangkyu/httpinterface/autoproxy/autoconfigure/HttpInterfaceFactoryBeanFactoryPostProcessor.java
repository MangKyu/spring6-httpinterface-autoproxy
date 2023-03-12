package io.github.mangkyu.httpinterface.autoproxy.autoconfigure;

import io.github.mangkyu.httpinterface.autoproxy.core.HttpInterfaceFactory;
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
        beanDefinitions.stream()
                .filter(v -> StringUtils.hasText(v.getBeanClassName()))
                .forEach(v -> findClassAndRegisterAsSingletonBean(beanFactory, v));
    }

    private void findClassAndRegisterAsSingletonBean(ConfigurableListableBeanFactory beanFactory, BeanDefinition v) {
        beanFactory.registerSingleton(v.getBeanClassName(), createHttpInterfaceProxy(v));
    }

    private Object createHttpInterfaceProxy(BeanDefinition beanDefinition) {
        return factory.create(findHttpInterfaceClass(beanDefinition));
    }

    private Class<?> findHttpInterfaceClass(BeanDefinition beanDefinition) {
        try {
            return ClassUtils.forName(beanDefinition.getBeanClassName(), this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
