package by.epam.training.sql.dao.impl;

import by.epam.training.sql.dao.CrudDao;
import by.epam.training.sql.db.DbConnector;
import by.epam.training.sql.entity.Author;
import by.epam.training.sql.entity.Book;
import by.epam.training.sql.exception.DaoException;
import by.epam.training.sql.mapper.Mapper;
import by.epam.training.sql.mapper.impl.BookMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao implements CrudDao<Book> {
    private Mapper<Book> bookMapper = new BookMapper();
    private CrudDao<Author> authorDao = new AuthorDao();

    @Override
    public Book create(Book model) {

        if (model.getAuthor().getId() == 0) {
            Author author = authorDao.create(model.getAuthor());
            model.setAuthor(author);
        }

        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = createBookStatement(conn, model);
             ResultSet rs = statement.getGeneratedKeys()) {

            if (rs.next()) {
                model.setId(rs.getInt(1));
            } else {
                throw new DaoException("Error to getting id for book ");
            }

        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to create the book " + e.getMessage());
        }

        return model;
    }

    @Override
    public Book read(int id) {
        Book book = null;

        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = readBookStatement(conn, id);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                book = bookMapper.map(rs);
            }
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to update the book " + e.getMessage());
        }

        return book;
    }

    @Override
    public void update(Book model) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = updateBookStatement(conn, model)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to update the book " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = deleteBookStatement(conn, id)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to delete the book " + e.getMessage());
        }
    }

    private PreparedStatement createBookStatement(Connection connection, Book book) throws SQLException {
        final String SQL = "INSERT INTO library.book(book_title, book_description, author_id) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getDescription());
        statement.setInt(3, book.getAuthor().getId());

        statement.executeUpdate();

        return statement;
    }

    private PreparedStatement readBookStatement(Connection connection, int idBook) throws SQLException {
        final String SQL = "SELECT idBook, book_title, book_description, idAuthor, author_name, author_surname FROM library.book LEFT OUTER JOIN library.author ON author_id = idAuthor WHERE idBook = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idBook);

        return statement;
    }

    private PreparedStatement updateBookStatement(Connection connection, Book book) throws SQLException {
        final String SQL = "UPDATE library.book SET book_title = ?, book_description = ?, author_id = ? WHERE idBook = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getDescription());
        statement.setInt(3, book.getAuthor().getId());
        statement.setInt(4, book.getId());

        return statement;
    }

    private PreparedStatement deleteBookStatement(Connection connection, int idBook) throws SQLException {
        final String SQL = "DELETE FROM library.book WHERE idBook = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idBook);

        return statement;
    }
}
