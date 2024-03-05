package com._lightdigitaltask.servlet;

/**
 * Класс создан для совместной работы по логированию методов и сожержит методы,
 * возвращающие имя класса или метода в ктором они вызывается.
 * @Версия: 1.0
 * @Дата: 03.03.2024
 * @Автор: Станислав Любань
 */
public class MethodInspector {

    /**
     * Метод возвращает имя метода в котором он вызывается
     * @return - String имя метода
     */
    public static String getCurrentMethodName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return stackTraceElements[2].getMethodName();
    }

    /**
     * Метод возвращает имя класса в котором он вызывается.
     * @return - String имя класса
     */
    public static String getCurrentClassName() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        return stackTraceElements[2].getClassName();
    }
}
