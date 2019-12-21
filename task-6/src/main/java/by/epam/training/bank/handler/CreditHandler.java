package by.epam.training.bank.handler;

import by.epam.training.bank.converter.MoneyConverter;
import by.epam.training.bank.dao.impl.DiscountDaoImpl;
import by.epam.training.bank.dao.impl.EventDaoImpl;
import by.epam.training.bank.dao.impl.TransactionDaoImpl;
import by.epam.training.bank.dto.CreditDto;
import by.epam.training.bank.entity.Credit;
import by.epam.training.bank.entity.Discount;
import by.epam.training.bank.entity.Event;
import by.epam.training.bank.entity.Transaction;
import by.epam.training.bank.entity.User;
import by.epam.training.bank.enums.DebtStatus;
import by.epam.training.bank.enums.DiscountType;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CreditHandler {

    private final UserHandler userHandler;
    private final TransactionDaoImpl transactionDao;
    private final EventDaoImpl eventDao;
    private final DiscountDaoImpl discountDao;
    private final MoneyConverter moneyConverter;

    private final BigDecimal PERCENT_100 = new BigDecimal(100);

    public List<CreditDto> process(User user, List<Credit> credits, LocalDate dateFrom, LocalDate dateTo) {
        ArrayList<CreditDto> creditDtos = new ArrayList<>();

        for (Credit credit : credits) {

            moneyConverter.resetCurrencyBucket();

            LocalDate creditDate = credit.getDate();
            BigDecimal creditMoney = credit.getMoney();
            BigDecimal rate = credit.getRate();
            BigDecimal transactionAmount = new BigDecimal(0);

            BigDecimal transactionAmountWithDiscount = new BigDecimal(0);
            BigDecimal rateDiscount = new BigDecimal(0);

            while (creditDate.isBefore(dateTo)) {

                List<Transaction> transactions = transactionDao.getTransactionsByCreditIdBetweenDateRange(
                        credit.getId(), credit.getDate(), creditDate, credit.getPeriod().increaseDays(creditDate));

                if (transactions.size() > 0) {

                    for (Transaction transaction : transactions) {
                        // process Events
                        for (Event event : eventDao.findAll()) {
                            if (!transaction.getDate().isAfter(event.getDate())) {
                                break;
                            }
                            moneyConverter.updateCurrencyCost(event.getCurrency(), event.getCost());
                        }

                        // process Discount
                        boolean isDiscountTransactionFlag = false;
                        for (Discount discount : discountDao.findAll()) {
                            if (discount.getType().isDiscountTransaction(transaction, discount)) {
                                isDiscountTransactionFlag = true;
                                rateDiscount = rate.compareTo(discount.getDiscount()) > 0 ? rate.subtract(discount.getDiscount()) : new BigDecimal(0);
                                break;
                            }
                        }

                        // process of money convert
                        BigDecimal brMoneyAfterConvert = moneyConverter.convert(transaction.getCurrency(), transaction.getMoney());

                        if (isDiscountTransactionFlag) {
                            transactionAmountWithDiscount = transactionAmountWithDiscount.add(brMoneyAfterConvert);
                        } else {
                            transactionAmount = transactionAmount.add(brMoneyAfterConvert);
                        }

                    }
                }


                creditMoney = creditMoney.add(creditMoney.multiply(rate).divide(PERCENT_100, 2, BigDecimal.ROUND_HALF_EVEN))
                                         .subtract(transactionAmount)
                                         .add(creditMoney.multiply(rateDiscount).divide(PERCENT_100, 2, BigDecimal.ROUND_HALF_EVEN))
                                         .subtract(transactionAmountWithDiscount);


                transactionAmount = new BigDecimal(0);
                rateDiscount = new BigDecimal(0);

                credit.setMoney(creditMoney);

                creditDate = credit.getPeriod().increaseDays(creditDate);
            }

            DebtStatus debtStatus = DebtStatus.getDebtStatus(credit.getMoney());

            CreditDto creditDto = CreditDto.builder().creditId(credit.getId())
                                           .transactionCount(transactionDao.getTransactionCountsByCreditId(credit.getId(), credit
                                                   .getDate()))
                                           .creditDebt(credit.getMoney())
                                           .dayOfCreditPeriod(getDayCountOfCreditPeriod(credit, dateFrom, dateTo))
                                           .debtStatus(debtStatus)
                                           .dateOfCreditDone(getDateOfCreditDone(credit, debtStatus))
                                           .userDto(userHandler.process(user)).build();

            creditDtos.add(creditDto);
        }

        return creditDtos;
    }

    private int getDayCountOfCreditPeriod(Credit credit, LocalDate dateFrom, LocalDate dateTo) {
        if (!credit.getDate().isAfter(dateFrom) && credit.getDate().isBefore(dateTo)) {
            throw new IllegalArgumentException("Incorrect credit days count calculation");
        }

        return Period.between(credit.getDate(), dateTo).getDays();
    }

    private String getDateOfCreditDone(Credit credit, DebtStatus creditStatus) {
        String result = "";

        if (creditStatus.equals(DebtStatus.DONE)) {
            result = transactionDao.getLastTransactionByCreditId(credit.getId(), credit.getDate()).getDate().toString();
        }

        return result;
    }
}
