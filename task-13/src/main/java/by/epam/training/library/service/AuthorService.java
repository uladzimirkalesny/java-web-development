package by.epam.training.library.service;

import by.epam.training.library.domain.Author;

import java.util.List;

public interface AuthorService {

    Author findById(int id);

    List<Author> findAll();

    int create(Author author);

    void update(Author author);

    void delete(Author author);
}
