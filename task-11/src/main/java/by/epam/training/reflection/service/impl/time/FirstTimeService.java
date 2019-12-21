package by.epam.training.reflection.service.impl.time;

import by.epam.training.reflection.service.ITimeService;

public class FirstTimeService implements ITimeService {
    @Override
    public void printTimeInfo() {
        System.out.println("First Meta Service # method printTimeInfo ");
    }

    @Override
    public void printTimeExpiredTime() {
        System.out.println("First Meta Service # method printTimeExpiredTime ");
    }

    @Override
    public void printTimeUser() {
        System.out.println("First Meta Service # method printTimeUser ");
    }

    @Override
    public void printTimeBuilder() {
        System.out.println("First Meta Service # method printTimeBuilder ");
    }
}
