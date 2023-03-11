package io.github.mangkyu.httpinterface.autofactory.core;

public interface HttpInterfaceFactory {
    <S> S create(Class<S> clientClass);
}
