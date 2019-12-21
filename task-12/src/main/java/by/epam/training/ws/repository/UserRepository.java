package by.epam.training.ws.repository;

import by.epam.training.ws.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static Map<Integer, User> store = new HashMap<>();
    private static int id = 1;

    static {
        store.put(idIdentityGenerator(), new User("Alex", 25));
        store.put(idIdentityGenerator(), new User("Max", 30));
    }

    private UserRepository() {
    }

    private static int idIdentityGenerator() {
        return id++;
    }

    public static UserRepository getInstance() {
        return new UserRepository();
    }

    public Map<Integer, User> getDatabase() {
        return store;
    }

    public int create(User user) {
        store.put(idIdentityGenerator(), user);
        return id;
    }
}
