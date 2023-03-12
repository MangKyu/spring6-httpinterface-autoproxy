package io.github.mangkyu.httpinterface.autoproxy.core;

public interface HttpInterfaceFactory {
    <S> S create(Class<S> clientClass);
}
