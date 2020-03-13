package com.github.jan222ik.di.own.test;

import com.github.jan222ik.di.own.OurInject;

public class ClassBWithA {

    @OurInject
    private InterfaceA dependency;
    @OurInject(name = "dog")
    private String dog;
    @OurInject(name = "cat")
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
