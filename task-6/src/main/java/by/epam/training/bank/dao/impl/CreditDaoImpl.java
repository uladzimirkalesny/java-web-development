package by.epam.training.bank.dao.impl;

import by.epam.training.bank.dao.root.HeadBankDataDaoImpl;
import by.epam.training.bank.entity.Credit;
import by.epam.training.bank.entity.HeadBankData;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

public class CreditDaoImpl extends HeadBankDataDaoImpl<Credit> {
    @Override
    protected List<Credit> getDataEntities() {
        return getRootObject().getData().getCredits();
    }

    @Override
    protected HeadBankData setDataEntities(List<Credit> list) {
        HeadBankData root = getRootObject();
        root.getData().setCredits(list);
        return root;
    }

    public List<Credit> getCreditsByUserId(int userId) {

        return findAll().stream()
                        .filter(credit -> credit.getUserId() == userId)
                        .collect(Collectors.toList());

    }
}
