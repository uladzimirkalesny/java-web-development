package by.epam.training.library.service;

import by.epam.training.library.domain.Book;

import java.util.List;

public interface BookService {
    Book findById(int id);

    List<Book> findAll();

    int create(Book book);

    void update(Book book);

    void delete(Book book);
}
