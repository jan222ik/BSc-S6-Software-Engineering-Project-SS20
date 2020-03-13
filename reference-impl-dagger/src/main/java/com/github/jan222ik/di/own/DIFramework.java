package com.github.jan222ik.di.own;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class DIFramework {
    private IModule mappingModule;

    public DIFramework(IModule mappingModule) {
        mappingModule.configure();
        this.mappingModule = mappingModule;
    }

    public <T> T createInstanceOf(Class<T> type) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (type == null) throw new IllegalArgumentException("Type may not be null");
        Class<? extends T> realType = type;
        if (type.isInterface()) {
            realType = mappingModule.getMapping(type);
        }
        for (final Constructor<?> constructor : realType.getConstructors()) {
            if (constructor.isAnnotationPresent(OurInject.class)) {
                T instance = injectConstructor(realType, constructor);
                injectFields(instance);
                return instance;
            }
        }
        Constructor<?> constructor = realType.getConstructors()[0];
        T instance = (T) constructor.newInstance();
        injectFields(instance);
        return instance;
    }

    private <T> void injectFields(T instance) throws
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(OurInject.class)) {
                OurInject annotation = field.getAnnotation(OurInject.class);
                if (!annotation.name().equals("")) {
                    field.setAccessible(true);
                    if (annotation.name().equals("dog")) {
                        field.set(instance, "Bello");
                    } else {
                        field.set(instance, "Mizi");
                    }
                } else {
                    Class<?> fieldType = field.getType();
                    Class<?> dependencyType = (!fieldType.isInterface()) ? fieldType : mappingModule.getMapping(fieldType);
                    field.setAccessible(true);
                    field.set(instance, dependencyType.getConstructor().newInstance());
                }
            }
        }
    }

    private <T> T injectConstructor(Class<T> type, Constructor<?> constructor) throws
            NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Class<?>[] parameterTypes = constructor.getParameterTypes();
        final Object[] objArr = new Object[parameterTypes.length];
        int i = 0;
        for (final Class<?> paramType : parameterTypes) {
            Class<?> dependencyType = (!paramType.isInterface()) ? paramType : mappingModule.getMapping(paramType);
            if (paramType.isAssignableFrom(dependencyType)) {
                objArr[i++] = dependencyType.getConstructor().newInstance();
            }
        }
        return type.getConstructor(parameterTypes).newInstance(objArr);
    }

}
