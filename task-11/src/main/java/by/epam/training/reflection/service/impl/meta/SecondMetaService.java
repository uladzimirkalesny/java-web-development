package by.epam.training.reflection.service.impl.meta;

import by.epam.training.reflection.service.IMetaService;

public class SecondMetaService implements IMetaService {
    @Override
    public void printMetadataInfo() {
        System.out.println("Second Meta Service # method printMetadataInfo ");
    }

    @Override
    public void printMetadataExpiredTime() {
        System.out.println("Second Meta Service # method printMetadataExpiredTime ");
    }

    @Override
    public void printMetadataUser() {
        System.out.println("Second Meta Service # method printMetadataUser ");
    }

    @Override
    public void printMetadataBuilder() {
        System.out.println("Second Meta Service # method printMetadataBuilder ");
    }
}
