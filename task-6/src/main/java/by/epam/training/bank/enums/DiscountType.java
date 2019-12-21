package by.epam.training.bank.enums;

import by.epam.training.bank.entity.Discount;
import by.epam.training.bank.entity.Transaction;
import by.epam.training.bank.entity.discountImpl.ManyDayDiscount;
import by.epam.training.bank.entity.discountImpl.OneDayDiscount;

public enum DiscountType {
    ONE{
        @Override
        public boolean isDiscountTransaction(Transaction transaction, Discount discount) {
            return transaction.getDate().equals(((OneDayDiscount) discount).getDate());
        }
    },
    MANY{
        @Override
        public boolean isDiscountTransaction(Transaction transaction, Discount discount) {
            return transaction.getDate().isAfter(((ManyDayDiscount) discount).getDateFrom()) &&
                     transaction.getDate().isBefore(((ManyDayDiscount) discount).getDateTo());
        }
    };

    public abstract boolean isDiscountTransaction(Transaction transaction, Discount discount);

    @Override
    public String toString() {
        return name().toUpperCase();
    }
}
