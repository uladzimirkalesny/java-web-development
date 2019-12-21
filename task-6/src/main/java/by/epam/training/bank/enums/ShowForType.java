package by.epam.training.bank.enums;

public enum ShowForType {
    ID, NAME;

    ShowForType() {
    }

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
