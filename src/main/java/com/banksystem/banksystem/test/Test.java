package com.banksystem.banksystem.test;

import java.lang.reflect.Method;


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

}
