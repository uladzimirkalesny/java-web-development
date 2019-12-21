package by.epam.training.library.repository.impl;

import by.epam.training.library.domain.Book;
import by.epam.training.library.exception.BookNotFoundException;
import by.epam.training.library.mapper.BookWithAuthorExtractor;
import by.epam.training.library.mapper.SingleBookWithAuthorExtractor;
import by.epam.training.library.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static by.epam.training.library.constant.QueryConstant.DELETE_BOOK_SQL;
import static by.epam.training.library.constant.QueryConstant.FIND_ALL_BOOKS_SQL;
import static by.epam.training.library.constant.QueryConstant.FIND_BOOK_BY_ID_SQL;
import static by.epam.training.library.constant.QueryConstant.ID_PARAMETER;
import static by.epam.training.library.constant.QueryConstant.INSERT_BOOK_SQL;
import static by.epam.training.library.constant.QueryConstant.UPDATE_BOOK_SQL;

@Repository
public class BookRepository implements CrudRepository<Book> {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int create(Book model) {
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(model);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(INSERT_BOOK_SQL, fileParameters, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public Book findById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put(ID_PARAMETER, id);

        Book book = jdbcTemplate.query(FIND_BOOK_BY_ID_SQL, params, new SingleBookWithAuthorExtractor());
        isBookNullable(id, book);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(FIND_ALL_BOOKS_SQL, new BookWithAuthorExtractor());
    }

    @Override
    public void update(Book model) {
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(model);
        jdbcTemplate.update(UPDATE_BOOK_SQL, fileParameters);
    }

    @Override
    public void delete(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put(ID_PARAMETER, id);
        isBookNullable(id, findById(id));
        jdbcTemplate.update(DELETE_BOOK_SQL, params);
    }

    private void isBookNullable(int id, Book book) {
        if (book == null) {
            throw new BookNotFoundException(id);
        }
    }
}
