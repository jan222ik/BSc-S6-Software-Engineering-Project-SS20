package com.github.jan222ik.di.own;

public interface IModule {

    void configure();

    <T> Class<? extends T> getMapping(Class<T> interfaceType);
}
