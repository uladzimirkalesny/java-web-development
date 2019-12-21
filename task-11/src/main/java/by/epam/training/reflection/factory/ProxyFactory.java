package by.epam.training.reflection.factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static <T> T newInstance(T t, InvocationHandler handler) {

        return (T) Proxy.newProxyInstance(t.getClass().getClassLoader(),
                                          t.getClass().getInterfaces(),
                                          handler);
    }
}