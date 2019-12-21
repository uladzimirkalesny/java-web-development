package by.epam.training.bank.enums;

import by.epam.training.bank.dto.CreditDto;

import java.util.Comparator;

public enum SortType {
    NAME(new NameComparator()),
    DEBT(new DebtComparator()),
    AGE(new AgeComparator());

    private Comparator comparator;

    SortType(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public String toString() {
        return name().toUpperCase();
    }

    public Comparator getComparator() {
        return comparator;
    }

    private static class NameComparator implements Comparator<CreditDto> {
        @Override
        public int compare(CreditDto o1, CreditDto o2) {
            return o1.getUserDto().getName().compareTo(o2.getUserDto().getName());
        }
    }

    private static class AgeComparator implements Comparator<CreditDto> {
        @Override
        public int compare(CreditDto o1, CreditDto o2) {
            return o2.getUserDto().getBirthday().compareTo(o1.getUserDto().getBirthday());
        }
    }

    private static class DebtComparator implements Comparator<CreditDto> {
        @Override
        public int compare(CreditDto o1, CreditDto o2) {
            return o1.getCreditDebt().compareTo(o2.getCreditDebt());
        }
    }

}
