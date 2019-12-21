package by.epam.training.ws;

import by.epam.training.ws.repository.UserRepository;
import by.epam.training.ws.resource.UserResource;

public class Runner {
    public static void main(String[] args) {
        UserRepository repository = UserRepository.getInstance();
    }
}
