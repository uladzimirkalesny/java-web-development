package by.epam.training.reflection;

import by.epam.training.reflection.factory.ProxyFactory;
import by.epam.training.reflection.handler.ServiceInvocationHandler;
import by.epam.training.reflection.service.IMetaService;
import by.epam.training.reflection.service.impl.meta.FirstMetaService;

import java.lang.reflect.InvocationHandler;

public class Runner {
    public static void main(String[] args) {
        IMetaService first = new FirstMetaService();

        InvocationHandler handler1 = new ServiceInvocationHandler<>(first);

        first = ProxyFactory.newInstance(first, handler1);

        first.printMetadataBuilder();
        first.printMetadataExpiredTime();
        first.printMetadataInfo();
        first.printMetadataUser();

    }
}
