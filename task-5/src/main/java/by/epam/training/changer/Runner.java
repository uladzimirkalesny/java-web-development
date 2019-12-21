package by.epam.training.changer;

import by.epam.training.changer.entity.Changer;

public class Runner {
    public static void main(String[] args) {
        Changer changer = new Changer(args[0]);
        changer.performChange();
    }
}
