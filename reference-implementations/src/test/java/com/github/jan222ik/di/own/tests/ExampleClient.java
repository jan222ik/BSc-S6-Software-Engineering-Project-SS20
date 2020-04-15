package com.github.jan222ik.di.own.tests;

import com.github.jan222ik.di.own.ServiceA;

import javax.inject.Inject;

public class ExampleClient {

    @Inject
    private ServiceA serviceA;

    @Inject
    public ExampleClient(ServiceA serviceA) {
        this.serviceA = serviceA;
    }

}
