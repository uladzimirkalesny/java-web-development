package by.epam.training.bank;

import by.epam.training.bank.converter.MoneyConverter;
import by.epam.training.bank.dao.impl.CreditDaoImpl;
import by.epam.training.bank.dao.impl.DiscountDaoImpl;
import by.epam.training.bank.dao.impl.EventDaoImpl;
import by.epam.training.bank.dao.impl.TransactionDaoImpl;
import by.epam.training.bank.dao.impl.UserDaoImpl;
import by.epam.training.bank.dao.root.DepartmentBankDataDaoImpl;
import by.epam.training.bank.dao.root.SettingDaoImpl;
import by.epam.training.bank.dto.CreditDto;
import by.epam.training.bank.entity.Transaction;
import by.epam.training.bank.entity.User;
import by.epam.training.bank.entity.config.Settings;
import by.epam.training.bank.handler.CreditHandler;
import by.epam.training.bank.handler.UserHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static by.epam.training.bank.constant.AppConstants.CURRENT_DIR_PATTERN;
import static by.epam.training.bank.constant.AppConstants.DATABASE_PATTERN;
import static by.epam.training.bank.constant.AppConstants.DATA_FILES_REGEXP;
import static by.epam.training.bank.constant.AppConstants.DATA_PATTERN;
import static by.epam.training.bank.constant.AppConstants.PATH_SEPARATOR;
import static by.epam.training.bank.constant.AppConstants.SETTINGS_PATTERN;

public class Runner {

    private static final Pattern PATTERN = Pattern.compile(DATA_FILES_REGEXP);
    private static final List<String> REQUIRED_FILES = Arrays.asList(DATABASE_PATTERN, SETTINGS_PATTERN);

    private static final UserDaoImpl USER_DAO = new UserDaoImpl();
    private static final TransactionDaoImpl TRANSACTION_DAO = new TransactionDaoImpl();
    private static final CreditDaoImpl CREDIT_DAO = new CreditDaoImpl();
    private static final EventDaoImpl EVENT_DAO = new EventDaoImpl();
    private static final DiscountDaoImpl DISCOUNT_DAO = new DiscountDaoImpl();

    public static void main(String[] args) {

        File dataDir = new File(CURRENT_DIR_PATTERN + PATH_SEPARATOR + DATA_PATTERN);

        if (!(dataDir.exists() && dataDir.isDirectory())) {
            throw new NoSuchElementException("Missing data directory");
        }

        Stream<Path> filePathsFromDataDirStream;

        try {
            filePathsFromDataDirStream = Files.walk(Paths.get(dataDir.getPath()), 1);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected exception occurred while scanning the directory for files");
        }

        List<String> filesList = filePathsFromDataDirStream.filter(Files::isRegularFile)
                                                           .map(Path::getFileName)
                                                           .map(String::valueOf)
                                                           .collect(Collectors.toList());

        checkRequiredFiles(filesList);

        List<String> nonRequiredFilesList = getNonRequiredFiles(filesList);

        Settings settings = new SettingDaoImpl().getSettings();

        List<String> useDepartments = settings.getUseDepartments();


        if (useDepartments == null) {
            useDepartments = nonRequiredFilesList;
        }

        if (useDepartments.size() > 0) {
            useDepartments = useDepartments.stream()
                                           .map(Runner::verifyPrefixAndPostfix)
                                           .collect(Collectors.toList());

            passTransactionsFromDepartmentsToDb(useDepartments);
        }

        UserHandler userHandler = new UserHandler();
        MoneyConverter moneyConverter = new MoneyConverter(settings.getStartCostUSD(), settings.getStartCostEUR());

        CreditHandler creditHandler = new CreditHandler(userHandler, TRANSACTION_DAO, EVENT_DAO, DISCOUNT_DAO, moneyConverter);

        List<User> users = USER_DAO.getUsersByShowFor(settings.getShowFor());
        List<CreditDto> credits = new ArrayList<>();

        users.stream()
             .map(user -> creditHandler.process(user, CREDIT_DAO.getCreditsByUserId(user.getId()), settings.getDateFrom(), settings.getDateTo()))
             .forEach(credits::addAll);


        if (credits != null && !credits.isEmpty()) {

            Collections.sort(credits, settings.getSortBy().getComparator());

            String header = String.format("|%10s|%7s|%15s|%17s|%12s|%14s|%12s|%12s| ",
                                          "Credit ID", "User ID", "User name", "Transaction count", "Credit debt", "Credit period",
                                          "Status", "Date of done");

            System.out.println(header);

            credits.stream()
                   .map(credit -> String.format("|%10s|%7s|%15s|%17s|%12s|%14s|%12s|%12s| ",
                                                credit.getCreditId(),
                                                credit.getUserDto().getUserId(),
                                                credit.getUserDto().getName(),
                                                credit.getTransactionCount(),
                                                credit.getCreditDebt(),
                                                credit.getDayOfCreditPeriod(),
                                                credit.getDebtStatus().getName(),
                                                credit.getDateOfCreditDone()))
                   .forEach(System.out::println);

        }
    }

    private static String verifyPrefixAndPostfix(String department) {
        if (!(department.startsWith("db_") && department.endsWith(".json"))) {
            department = "db_" + department + ".json";
        }
        return department;
    }

    private static void passTransactionsFromDepartmentsToDb(List<String> useDepartments) {
        TransactionDaoImpl transactionDao = new TransactionDaoImpl();

        useDepartments.forEach(department -> {
            DepartmentBankDataDaoImpl departmentBankDataDao = new DepartmentBankDataDaoImpl(department);
            List<Transaction> departmentTransactions = departmentBankDataDao.findAll();

            if (departmentTransactions != null && departmentTransactions.size() > 0) {

                departmentTransactions.forEach(departmentTransaction -> {
                    departmentBankDataDao.delete(departmentTransaction.getId());

                    departmentTransaction.setId(transactionDao.getLastTransactionId() + 1);
                    transactionDao.save(departmentTransaction);
                });
            }
        });
    }

    private static List<String> getNonRequiredFiles(List<String> files) {
        return files.stream()
                    .filter(file -> PATTERN.matcher(file).find())
                    .collect(Collectors.toList());
    }

    private static void checkRequiredFiles(List<String> files) {
        REQUIRED_FILES.forEach(requiredFile -> files.stream()
                                                    .filter(file -> file.equals(requiredFile))
                                                    .findFirst()
                                                    .orElseThrow(() -> new NoSuchElementException("Missing file " + requiredFile)));
    }
}
