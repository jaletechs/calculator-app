package com.jaletechs.calculator.app.util;

public class Tuple <T,U> {
    private final T t;
    private final U u;

    public Tuple(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T _1() {
        return t;
    }

    public U _2() {
        return u;
    }

    public String toString() {
        return t.toString() + " " + u.toString();
    }
}
