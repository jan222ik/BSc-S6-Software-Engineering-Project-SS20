package com.github.jan222ik.di.own;

import com.github.jan222ik.di.own.test.ClassA;
import com.github.jan222ik.di.own.test.ClassBWithA;
import com.github.jan222ik.di.own.test.ClassC;
import com.github.jan222ik.di.own.test.InterfaceA;
import com.github.jan222ik.di.own.test.ModuleA;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        DIFramework diFramework = new DIFramework(new ModuleA());
        diFramework.createInstanceOf(ClassC.class);
        ClassBWithA instanceOf = diFramework.createInstanceOf(ClassBWithA.class);
        System.out.println("instanceOf = " + instanceOf);
        boolean equals = diFramework.createInstanceOf(InterfaceA.class).getClass().equals(ClassA.class);
        System.out.println("equals = " + equals);
    }
}
