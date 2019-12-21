package by.epam.training.bank.dao.root;

import by.epam.training.bank.dao.AbstractCrudDao;
import by.epam.training.bank.entity.BaseEntity;
import by.epam.training.bank.entity.HeadBankData;

import static by.epam.training.bank.constant.AppConstants.DATABASE_PATTERN;

public abstract class HeadBankDataDaoImpl<T extends BaseEntity> extends AbstractCrudDao<T, HeadBankData> {

    @Override
    public Class<HeadBankData> getRootObjectClass() {
        return HeadBankData.class;
    }

    @Override
    public String getFileName() {
        return DATABASE_PATTERN;
    }
}
