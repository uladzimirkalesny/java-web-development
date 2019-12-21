package by.epam.training.bank.dao.impl;

import by.epam.training.bank.dao.root.HeadBankDataDaoImpl;
import by.epam.training.bank.entity.HeadBankData;
import by.epam.training.bank.entity.User;
import by.epam.training.bank.entity.showForImpl.ShowFor;
import by.epam.training.bank.enums.ShowForType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDaoImpl extends HeadBankDataDaoImpl<User> {

    @Override
    protected List<User> getDataEntities() {
        return getRootObject().getData().getUsers();
    }

    @Override
    protected HeadBankData setDataEntities(List<User> list) {
        HeadBankData root = getRootObject();
        root.getData().setUsers(list);
        return root;
    }

    private List<User> getUsersByName(List<String> names) {
        List<User> users = findAll();

        return names.stream()
                    .filter(name -> (name != null || !name.isEmpty()) && name.contains(" "))
                    .map(name -> name.split(" "))
                    .map(userData -> findUserByName(users, userData))
                    .collect(Collectors.toList());
    }

    private User findUserByName(List<User> users, String[] userData) {
        return users.stream()
                    .filter(user -> user.getName().equals(userData[0]) && user.getSecondName().equals(userData[1]))
                    .findFirst()
                    .orElse(null);
    }

    private List<User> getUsersById(List<Integer> indexes) {
        List<User> users = findAll();

        return users.stream()
                    .filter(user -> indexes.contains(user.getId()))
                    .collect(Collectors.toList());
    }

    public List<User> getUsersByShowFor(ShowFor showFor) {
        List<User> usersFromDB = new ArrayList<>();

        if (showFor != null && showFor.getType() != null) {

            ShowForType type = showFor.getType();

            if (type.equals(ShowForType.ID)) {
                usersFromDB = getUsersById(showFor.getUsers());
            } else if (type.equals(ShowForType.NAME)) {
                usersFromDB = getUsersByName(showFor.getUsers());
            }

        } else {
            usersFromDB = findAll();
        }

        return usersFromDB;
    }
}
