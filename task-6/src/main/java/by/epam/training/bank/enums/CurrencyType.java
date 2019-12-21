package by.epam.training.bank.enums;

public enum CurrencyType {
    BR, USD, EUR;

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
