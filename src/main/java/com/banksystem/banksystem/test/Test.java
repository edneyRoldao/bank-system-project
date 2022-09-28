package com.banksystem.banksystem.test;

import java.lang.reflect.Method;
import java.util.Objects;


public class Test {

    public static void main(String[] args) {
        var p = new Pessoa();
        testReflection(p.getClass());
    }

    public static void testReflection(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }

    static String completeField(String value, int size, Direction direction, CompletionType completionType) {
        StringBuilder sb = new StringBuilder(Objects.nonNull(value) ? value : "");

        if (sb.length() >= size) {
            return sb.substring(0, size);
        }

        String completionValue = completionType == CompletionType.WHITESPACE ? " " : "0";

        while (sb.length() < size)  {
            switch (direction) {
                case LEFT:
                    sb.append(completionValue);
                    break;
                case RIGHT:
                    sb.insert(0, completionValue);
            }
        }

        return sb.toString();
    }


}

enum Direction {
    LEFT, RIGHT
}

enum CompletionType {
    ZERO, WHITESPACE
}
