package com.github.jan222ik.di.own;

import java.util.HashMap;
import java.util.function.Consumer;

@SuppressWarnings("WeakerAccess")
public abstract class InterfaceMappings {
    private HashMap<Class<? extends InjectInterface>, Consumer<? extends InjectInterface>> mappings = new HashMap<>();

    public abstract void configure();

    public void createMapping(Class<? extends InjectInterface> interfaceType, Consumer<? extends InjectInterface> action) {
        mappings.put(interfaceType, action);
    }

    public Consumer<? extends InjectInterface> getMapping(Class<? extends InjectInterface> type) {
        return mappings.get(type);
    }
}
