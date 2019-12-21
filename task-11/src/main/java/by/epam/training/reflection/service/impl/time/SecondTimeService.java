package by.epam.training.reflection.service.impl.time;

import by.epam.training.reflection.service.ITimeService;

public class SecondTimeService implements ITimeService {
    @Override
    public void printTimeInfo() {
        System.out.println("Second Meta Service # method printTimeInfo ");
    }

    @Override
    public void printTimeExpiredTime() {
        System.out.println("Second Meta Service # method printTimeExpiredTime ");
    }

    @Override
    public void printTimeUser() {
        System.out.println("Second Meta Service # method printTimeUser ");
    }

    @Override
    public void printTimeBuilder() {
        System.out.println("Second Meta Service # method printTimeBuilder ");
    }
}
