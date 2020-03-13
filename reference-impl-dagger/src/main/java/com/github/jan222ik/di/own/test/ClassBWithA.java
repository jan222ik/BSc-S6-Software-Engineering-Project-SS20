package com.github.jan222ik.di.own.test;

import com.github.jan222ik.di.own.FromProperty;
import com.github.jan222ik.di.own.OurInject;

public class ClassBWithA {

    @OurInject
    private InterfaceA dependency;

    @FromProperty("dog")
    private String dog;
    @FromProperty("cat")
    private String cat;

    @OurInject
    public ClassBWithA(ClassA dependency) {

    }

    @Override
    public String toString() {
        return "ClassBWithA{" +
                "dependency=" + dependency +
                ", dog='" + dog + '\'' +
                ", cat='" + cat + '\'' +
                '}';
    }
}
