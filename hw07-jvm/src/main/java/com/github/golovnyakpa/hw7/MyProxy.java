package com.github.golovnyakpa.hw7;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class MyProxy {

    private MyProxy() {
    }

    static class Handler implements InvocationHandler {

        TestLoggingInterface original;

        Map<List<Class<?>>, Method> originalMethods;

        public Handler(TestLoggingInterface original) {
            this.original = original;
            this.originalMethods = new HashMap<>();
            Arrays.stream(original.getClass().getDeclaredMethods()).forEach(m ->
                    this.originalMethods.put(Arrays.asList(m.getParameterTypes()), m)
            );
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Method originalMethod = originalMethods.get(Arrays.asList(method.getParameterTypes()));
            if (originalMethod != null && originalMethod.isAnnotationPresent(Log.class)) {
                System.out.println(buildLogString(method, args));
            }
            method.invoke(original, args);
            return null;
        }

        private String buildLogString(Method method, Object[] args) {
            if (Array.get(args, 0) instanceof Object[] arr) { // vararg case
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
