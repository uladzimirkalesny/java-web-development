package by.epam.training.reflection.handler;

import by.epam.training.reflection.annotation.After;
import by.epam.training.reflection.annotation.Before;
import by.epam.training.reflection.annotation.Ignore;
import by.epam.training.reflection.annotation.ThrowException;
import by.epam.training.reflection.exception.NotSupportedMethodException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceInvocationHandler<T> implements InvocationHandler {
    private T t;

    public ServiceInvocationHandler(T t) {
        this.t = t;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Object result = null;
        Class<?> clazz = t.getClass();
        Method[] clazzMethods = clazz.getDeclaredMethods();

        Ignore ignoreClassAnnotation = clazz.getDeclaredAnnotation(Ignore.class);
        if (ignoreClassAnnotation != null) {
            return method.invoke(t, args);
        }

        Before[] beforeClassAnnotations = clazz.getAnnotationsByType(Before.class);
        for (Before beforeClassAnnotation : beforeClassAnnotations) {
            System.out.println(beforeClassAnnotation.message());
        }

        for (Method clazzMethod : clazzMethods) {
            if (method.getName().equals(clazzMethod.getName())) {
                Ignore ignoreMethodAnnotation = clazzMethod.getDeclaredAnnotation(Ignore.class);
                if (ignoreMethodAnnotation != null) {
                    result = method.invoke(t, args);
                    break;
                }

                ThrowException throwException = clazzMethod.getDeclaredAnnotation(ThrowException.class);
                if (throwException != null) {
                    throw new NotSupportedMethodException();
                }

                Before[] beforeMethodAnnotations = clazzMethod.getAnnotationsByType(Before.class);
                for (Before beforeMethodAnnotation : beforeMethodAnnotations) {
                    System.out.println(beforeMethodAnnotation.message());
                }

                result = method.invoke(t, args);

                After[] afterMethodAnnotations = clazzMethod.getAnnotationsByType(After.class);
                for (After afterMethodAnnotation : afterMethodAnnotations) {
                    System.out.println(afterMethodAnnotation.message());
                }
            }
        }

        After[] afterClassAnnotations = clazz.getAnnotationsByType(After.class);
        for (After afterClassAnnotation : afterClassAnnotations) {
            System.out.println(afterClassAnnotation.message());
        }

        return result;
    }
}
