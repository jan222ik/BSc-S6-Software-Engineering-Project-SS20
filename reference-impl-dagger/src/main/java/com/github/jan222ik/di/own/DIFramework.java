package com.github.jan222ik.di.own;


import lombok.Setter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@SuppressWarnings({"UnusedReturnValue"})
public class DIFramework {
    private IModule mappingModule;
    @Setter
    private Map<Class<? extends InjectInterface>, Consumer<? extends InjectInterface>> interfaceInjects;

    public DIFramework(IModule mappingModule) {
        this();
        if (mappingModule != null) {
            mappingModule.configure();
            this.mappingModule = mappingModule;
        }
    }

    public DIFramework() {
        this.mappingModule = new AbstractModule.EmptyModule();
        this.mappingModule.configure();
    }

    public <T> T createInstanceOf(Class<T> type) throws Exception {
        Class<? extends T> realType = (type == null || !type.isInterface()) ? type : mappingModule.getMapping(type);
        if (realType == null) {
            return null;
        }
        Optional<Constructor<?>> applicableConstructor = Arrays
                .stream(realType.getConstructors())
                .filter(con -> con.isAnnotationPresent(OurInject.class))
                .findFirst();

        final T instance;
        if (applicableConstructor.isPresent()) {
            instance = injectConstructor(realType, applicableConstructor.get());
        } else {
            Constructor<?> con = realType.getConstructor();
            //noinspection unchecked
            instance = (T) con.newInstance();
        }
        return injectFields(instance);
    }

    private <T> T injectConstructor(Class<T> type, Constructor<?> constructor) throws Exception {
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        Object[] args = new Object[parameterTypes.length];
        int i = 0;
        for (Class<?> paramType : parameterTypes) {
            Object dependency = createInstanceOf(paramType);
            args[i++] = dependency;
        }
        return type.getConstructor(parameterTypes).newInstance(args);
    }

    private <T> T injectFields(final T instance) throws Exception {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(OurInject.class)) {
                field.setAccessible(true);
                Object dependency = createInstanceOf(field.getType());
                field.set(instance, dependency);
            } //else processFromPropertyAnnotation(instance, field);
        }
        return instance;
    }

    public <T extends InjectInterface> T injectInterface(T instance) {
        for (Class<?> iFace : instance.getClass().getInterfaces()) {
            //noinspection unchecked
            Consumer<T> consumer = (Consumer<T>) interfaceInjects.get(iFace);
            if (consumer != null) {
                consumer.accept(instance);
            }
        }
        return instance;
    }


    public <T> void processFromPropertyAnnotation(T instance, Field field) throws Exception {
        if (field.isAnnotationPresent(FromProperty.class)) {
            field.setAccessible(true);
            FromProperty annotation = field.getAnnotation(FromProperty.class);
            if (!annotation.value().equals("") && field.getType().equals(String.class)) {
                boolean isDog = annotation.value().equals("dog");
                field.set(instance, (isDog) ? "Bello" : "Mizi");
            } else field.set(instance, null);
        }
    }
}
