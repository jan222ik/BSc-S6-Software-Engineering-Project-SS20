package com.github.jan222ik.helper;

import java.util.Arrays;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public abstract class C {
    private static final String RESET_COLOR = "\u001b[0m";

    public static String b(String s) {
        return "\u001B[32m" + s + RESET_COLOR;
    }

    public static String clazz(Object o) {
        return (o != null) ? o.getClass().getName() : "null";
    }

    public static String stringifyObj(String objName, Pair... pairs) {
        String collect = Arrays.stream(pairs).map(p -> "\t" + p.name + " = " + b((p.asClass ? clazz(p.value) : p.value) + "\n")).collect(Collectors.joining());
        return objName + " {\n" + collect + "}";
    }

    public static void logObj(String objName, Pair... pairs) {
        System.out.println(stringifyObj(objName, pairs));
    }

    public static Pair param(String name, Object value) {
        return new Pair(name, value);
    }

    public static Pair field(String name, Object value) {
        return param(name, value);
    }

    public static Pair fieldV(String name, Object value) {
        return param(name, value).setAsClass(false);
    }

    public static class Pair {
        public String name;
        public Object value;
        public boolean asClass;

        public Pair(String name, Object value) {
            this.name = name;
            this.value = value;
            this.asClass = true;
        }

        public Pair setAsClass(boolean asClass) {
            this.asClass = asClass;
            return this;
        }
    }

}
