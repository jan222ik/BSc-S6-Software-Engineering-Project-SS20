package com.github.jan222ik.di.own.test;

import com.github.jan222ik.di.own.AbstractModule;

public class ModuleA extends AbstractModule {
    @Override
    public void configure() {
        map(InterfaceA.class, ClassA.class);
    }



}
