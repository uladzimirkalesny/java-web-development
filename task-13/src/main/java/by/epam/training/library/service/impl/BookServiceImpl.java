package by.epam.training.library.service.impl;

import by.epam.training.library.domain.Book;
import by.epam.training.library.repository.impl.BookRepository;
import by.epam.training.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public int create(Book book) {
        return bookRepository.create(book);
    }

    @Override
    public void update(Book book) {
        bookRepository.update(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book.getId());
    }
}
