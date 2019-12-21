package by.epam.training.reflection.service.impl.meta;

import by.epam.training.reflection.annotation.After;
import by.epam.training.reflection.annotation.Before;
import by.epam.training.reflection.annotation.Ignore;
import by.epam.training.reflection.annotation.ThrowException;
import by.epam.training.reflection.service.IMetaService;

@Before(message = "Before FirstMetaService class annotation # 1")
@Before(message = "Before FirstMetaService class annotation # 2")
@After(message = "After FirstMetaService class annotation # 1")
@After(message = "After FirstMetaService class annotation # 2")
public class FirstMetaService implements IMetaService {
    @Override
    public void printMetadataInfo() {
        System.out.println("First Meta Service # method printMetadataInfo ");
    }

    @Override
    @Before(message = "Before printMetadataExpiredTime method annotation # 3")
    @After(message = "After printMetadataExpiredTime method annotation # 3")
    @Before(message = "Before printMetadataExpiredTime method annotation # 4")
    public void printMetadataExpiredTime() {
        System.out.println("First Meta Service # method printMetadataExpiredTime ");
    }

    @Override
    @After(message = "After printMetadataUser method annotation # 3")
    public void printMetadataUser() {
        System.out.println("First Meta Service # method printMetadataUser ");
    }

    @Override
    @Ignore
    @ThrowException
    @Before(message = "Before printMetadataBuilder method annotation # 3")
    @Before(message = "Before printMetadataBuilder method annotation # 4")
    @After(message = "After printMetadataBuilder method annotation # 3")
    public void printMetadataBuilder() {
        System.out.println("First Meta Service # method printMetadataBuilder ");
    }
}


