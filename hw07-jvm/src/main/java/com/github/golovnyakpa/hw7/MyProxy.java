package com.github.golovnyakpa.hw7;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class MyProxy {

    private MyProxy() {
    }

    static class Handler implements InvocationHandler {

        TestLoggingInterface original;

        public Handler(TestLoggingInterface original) {
            this.original = original;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method m : original.getClass().getDeclaredMethods()) {
                if (Arrays.equals(m.getParameterTypes(), method.getParameterTypes()) &&
                        m.isAnnotationPresent(Log.class)) {

                    System.out.println(buildLogString(method, args));
                }
            }
            method.invoke(original, args);
            return null;
        }

        private String buildLogString(Method method, Object[] args) {
            if (Array.get(args, 0) instanceof Object[]) { // vararg case
                Object[] arr = (Object[]) Array.get(args, 0);
                return "Method " + method.getName() + " was called, " + "param(s): " +
                        Arrays.toString(arr);
            } else {
                return "Method " + method.getName() + " was called, " + "param(s): "
                        + Arrays.toString(args);
            }
        }
    }

    static TestLoggingInterface createInstance() {
        Handler handler = new Handler(new TestLoggingImpl());
        return (TestLoggingInterface) Proxy.newProxyInstance(
                TestLoggingInterface.class.getClassLoader(),
                new Class[]{TestLoggingInterface.class},
                handler
        );

    }

}
