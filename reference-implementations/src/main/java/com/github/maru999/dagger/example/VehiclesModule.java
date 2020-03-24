package com.github.maru999.dagger.example;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class VehiclesModule {
    @Provides
    public Engine provideEngine() {
        return new Engine();
    }

    @Provides
    @Singleton
    public Brand provideBrand() {
        return new Brand("Porsche");
    }
}
