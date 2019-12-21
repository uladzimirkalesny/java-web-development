package by.epam.training.bank.dao.root;

import by.epam.training.bank.dao.AbstractCrudDao;
import by.epam.training.bank.entity.DepartmentBankData;
import by.epam.training.bank.entity.Transaction;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DepartmentBankDataDaoImpl extends AbstractCrudDao<Transaction, DepartmentBankData> {

    private final String departmentFileName;

    @Override
    public Class<DepartmentBankData> getRootObjectClass() {
        return DepartmentBankData.class;
    }

    @Override
    public String getFileName() {
        return departmentFileName;
    }

    @Override
    protected List<Transaction> getDataEntities() {
        return getRootObject().getTransactions();
    }

    @Override
    protected DepartmentBankData setDataEntities(List<Transaction> list) {
        DepartmentBankData root = getRootObject();
        root.setTransactions(list);
        return root;
    }

}
