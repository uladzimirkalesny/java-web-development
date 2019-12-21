package by.epam.training.bank.dao.impl;

import by.epam.training.bank.dao.root.HeadBankDataDaoImpl;
import by.epam.training.bank.entity.HeadBankData;
import by.epam.training.bank.entity.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionDaoImpl extends HeadBankDataDaoImpl<Transaction> {

    public int getLastTransactionId() {
        List<Transaction> transactions = findAll();
        return (transactions != null && transactions.size() > 0) ? transactions.get(transactions.size() - 1)
                                                                               .getId() : 0;
    }

    @Override
    protected List<Transaction> getDataEntities() {
        return getRootObject().getData().getTransactions();
    }

    @Override
    protected HeadBankData setDataEntities(List<Transaction> list) {
        HeadBankData root = getRootObject();
        root.getData().setTransactions(list);
        return root;
    }

    public List<Transaction> getTransactionsByUserId(int userId) {

        return findAll().stream()
                        .filter(transaction -> transaction.getUserId() == userId)
                        .collect(Collectors.toList());

    }

    public long getTransactionCountsByCreditId(int creditId, LocalDate creditDate) {
        return getTransactionsByCreditId(creditId, creditDate).size();
    }

    public BigDecimal getTransactionsAmount(int creditId, LocalDate creditDate) {
        return getTransactionsByCreditId(creditId, creditDate).stream()
                                                              .map(Transaction::getMoney)
                                                              .reduce(BigDecimal::add)
                                                              .orElse(BigDecimal.ZERO);
    }


    public List<Transaction> getTransactionsByCreditId(int creditId, LocalDate creditDate) {
        return findAll().stream()
                        .filter(transaction -> transaction.getCreditId() == creditId)
                        .filter(transaction -> creditDate.isBefore(transaction.getDate()))
                        .collect(Collectors.toList());
    }

    public Transaction getLastTransactionByCreditId(int creditId, LocalDate creditDate) {
        List<Transaction> transactions = getTransactionsByCreditId(creditId, creditDate);
        Transaction transaction = transactions.get(transactions.size() - 1);
        return transaction;

    }

    public List<Transaction> getTransactionsByCreditIdBetweenDateRange(int creditId, LocalDate creditDate, LocalDate from, LocalDate to) {
        return getTransactionsByCreditId(creditId, creditDate).stream()
                                                              .filter(transaction -> transaction.getDate().isEqual(from) || (transaction.getDate().isAfter(from) && transaction.getDate().isBefore(to)))
                                                              .collect(Collectors.toList());
    }
}
