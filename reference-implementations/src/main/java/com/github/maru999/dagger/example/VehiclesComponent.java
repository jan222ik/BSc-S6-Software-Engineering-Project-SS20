package com.github.maru999.dagger.example;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = VehiclesModule.class)
public interface VehiclesComponent {
    Car buildCar();
}
