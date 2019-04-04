package com.homedepot.bb.util;

import java.lang.reflect.Method;

public class ControllerMapping {

    private Object instance;
    private Method method;


    public ControllerMapping(Object instance, Method method){

        super();

        this.instance = instance;
        this.method = method;

    }

    public Object getInstance() {
        return instance;
    }

    public Method getMethod() {
        return method;
    }

}
