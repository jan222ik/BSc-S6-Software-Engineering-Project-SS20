package com.github.jan222ik.di.own;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({CONSTRUCTOR, FIELD})
@Retention(RUNTIME)
public @interface OurInject {
    String name() default "";
}
