package by.epam.training.library.repository.impl;

import by.epam.training.library.domain.Author;
import by.epam.training.library.domain.Book;
import by.epam.training.library.exception.AuthorNotFoundException;
import by.epam.training.library.exception.BookNotFoundException;
import by.epam.training.library.mapper.SingleAuthorMapper;
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
import java.util.stream.Collectors;

import static by.epam.training.library.constant.QueryConstant.AUTHOR_ID_PARAMETER;
import static by.epam.training.library.constant.QueryConstant.BOOK_AUTHOR_REF_SQL;
import static by.epam.training.library.constant.QueryConstant.BOOK_ID_PARAMETER;
import static by.epam.training.library.constant.QueryConstant.DELETE_AUTHOR_SQL;
import static by.epam.training.library.constant.QueryConstant.FIND_ALL_AUTHOR_SQL;
import static by.epam.training.library.constant.QueryConstant.FIND_AUTHOR_BY_ID_SQL;
import static by.epam.training.library.constant.QueryConstant.ID_PARAMETER;
import static by.epam.training.library.constant.QueryConstant.INSERT_AUTHOR_SQL;
import static by.epam.training.library.constant.QueryConstant.UPDATE_AUTHOR_SQL;

@Repository
public class AuthorRepository implements CrudRepository<Author> {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Author> findAll() {
        return jdbcTemplate.query(FIND_ALL_AUTHOR_SQL, new SingleAuthorMapper());
    }

    @Override
    public Author findById(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put(ID_PARAMETER, id);
        Author author = jdbcTemplate.queryForObject(FIND_AUTHOR_BY_ID_SQL, params, new SingleAuthorMapper());
        isAuthorNullable(id, author);
        return author;
    }

    @Override
    public int create(Author model) {
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(model);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(INSERT_AUTHOR_SQL, fileParameters, keyHolder);

        if (model.getBooks() != null && !model.getBooks().isEmpty()) {
            List<Integer> bookIds = model.getBooks()
                                         .stream()
                                         .mapToInt(Book::getId)
                                         .boxed()
                                         .collect(Collectors.toList());

            int authorId = Objects.requireNonNull(keyHolder.getKey()).intValue();

            bookIds.forEach(bookId -> {
                Map<String, Object> params = new HashMap<>();
                params.put(AUTHOR_ID_PARAMETER, authorId);
                params.put(BOOK_ID_PARAMETER, bookId);
                jdbcTemplate.update(BOOK_AUTHOR_REF_SQL, params);
            });
        }

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(Author model) {
        SqlParameterSource fileParameters = new BeanPropertySqlParameterSource(model);
        jdbcTemplate.update(UPDATE_AUTHOR_SQL, fileParameters);
    }

    @Override
    public void delete(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put(ID_PARAMETER, id);
        isAuthorNullable(id, findById(id));
        jdbcTemplate.update(DELETE_AUTHOR_SQL, params);
    }

    private void isAuthorNullable(int id, Author author) {
        if (author == null) {
            throw new AuthorNotFoundException(id);
        }
    }
}
