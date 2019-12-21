package by.epam.training.bank.dao.impl;

import by.epam.training.bank.dao.root.HeadBankDataDaoImpl;
import by.epam.training.bank.entity.Discount;
import by.epam.training.bank.entity.HeadBankData;

import java.util.List;

public class DiscountDaoImpl extends HeadBankDataDaoImpl<Discount> {

    @Override
    protected List<Discount> getDataEntities() {
        return getRootObject().getData().getDiscounts();
    }

    @Override
    protected HeadBankData setDataEntities(List<Discount> list) {
        HeadBankData root = getRootObject();
        root.getData().setDiscounts(list);
        return root;
    }
}
