package by.epam.training.library.service.impl;

import by.epam.training.library.domain.Author;
import by.epam.training.library.repository.impl.AuthorRepository;
import by.epam.training.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author findById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public int create(Author author) {
        return authorRepository.create(author);
    }

    @Override
    public void update(Author author) {
        authorRepository.update(author);
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author.getId());
    }
}
