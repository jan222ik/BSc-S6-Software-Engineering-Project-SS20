package com.github.jan222ik.di.own;

import com.github.jan222ik.helper.C;

import java.util.HashMap;

@SuppressWarnings("SameParameterValue")
public abstract class AbstractModule implements IModule {
    private HashMap<Class<?>, Class<?>> mappings = new HashMap<>();

    protected <T> void createMapping(Class<T> interfaceType, Class<? extends T> implType) {
        if (interfaceType == null) throw new NullPointerException("Interface type may not be null");
        if (implType == null) throw new NullPointerException("Implementation type may not be null");
        mappings.put(interfaceType, implType);
    }

    @Override
    public <T> Class<? extends T> getMapping(Class<T> interfaceType) {
        Class<?> implType = mappings.get(interfaceType);
        if (implType != null) {
            return implType.asSubclass(interfaceType);
        } else return null;
    }

    @Override
    public String toString() {
        return C.stringifyObj("AbstractModule", C.field("mappings", mappings));
    }

    public static class EmptyModule extends AbstractModule {
        @Override
        public void configure() { /* No Configuration needed */ }
    }
}
