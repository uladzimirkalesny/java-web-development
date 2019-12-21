package by.epam.training.library.mapper;

import by.epam.training.library.domain.Author;
import by.epam.training.library.domain.Book;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static by.epam.training.library.constant.DDLConstant.AUTHOR_ID_COLUMN_LABEL;
import static by.epam.training.library.constant.DDLConstant.AUTHOR_NAME_COLUMN_LABEL;
import static by.epam.training.library.constant.DDLConstant.AUTHOR_SURNAME_COLUMN_LABEL;
import static by.epam.training.library.constant.DDLConstant.BOOK_DESCRIPTION_COLUMN_LABEL;
import static by.epam.training.library.constant.DDLConstant.BOOK_ID_COLUMN_LABEL;
import static by.epam.training.library.constant.DDLConstant.BOOK_TITLE_COLUMN_LABEL;
import static by.epam.training.library.constant.DDLConstant.BOOK_UNITS_IN_STOCK_COLUMN_LABEL;

public class SingleBookWithAuthorExtractor implements ResultSetExtractor<Book> {
    @Override
    public Book extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer, Book> map = new HashMap<>();
        Book book = null;

        while (rs.next()) {
            int bookId = rs.getInt(BOOK_ID_COLUMN_LABEL);
            book = map.get(bookId);

            if (book == null) {
                book = Book.createBookBuilder()
                           .id(bookId)
                           .title(rs.getString(BOOK_TITLE_COLUMN_LABEL))
                           .description(rs.getString(BOOK_DESCRIPTION_COLUMN_LABEL))
                           .unitsInStock(rs.getInt(BOOK_UNITS_IN_STOCK_COLUMN_LABEL))
                           .authors(new HashSet<>())
                           .build();

                map.put(bookId, book);
            }

            int authorId = rs.getInt(AUTHOR_ID_COLUMN_LABEL);

            if (authorId > 0) {
                Author author = Author.createAuthorBuilder()
                                      .id(authorId)
                                      .name(rs.getString(AUTHOR_NAME_COLUMN_LABEL))
                                      .surname(rs.getString(AUTHOR_SURNAME_COLUMN_LABEL))
                                      .build();

                book.getAuthors().add(author);
            }
        }
        return book;
    }
}
