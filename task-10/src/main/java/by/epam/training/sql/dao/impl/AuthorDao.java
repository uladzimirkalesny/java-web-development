package by.epam.training.sql.dao.impl;

import by.epam.training.sql.dao.CrudDao;
import by.epam.training.sql.db.DbConnector;
import by.epam.training.sql.entity.Author;
import by.epam.training.sql.exception.DaoException;
import by.epam.training.sql.mapper.Mapper;
import by.epam.training.sql.mapper.impl.AuthorMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDao implements CrudDao<Author> {
    private Mapper<Author> authorMapper = new AuthorMapper();

    @Override
    public Author create(Author model) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = createAuthorStatement(conn, model);
             ResultSet rs = statement.getGeneratedKeys()) {

            if (rs.next()) {
                model.setId(rs.getInt(1));
            } else {
                throw new DaoException("Error to getting id for author ");
            }

        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to create the author " + e.getMessage());
        }
        return model;
    }

    @Override
    public Author read(int id) {
        Author author = null;

        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = readAuthorStatement(conn, id);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                author = authorMapper.map(rs);
            }

        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to read the author " + e.getMessage());
        }

        return author;
    }

    @Override
    public void update(Author model) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = updateAuthorStatement(conn, model)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to update the author " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = deleteAuthorStatement(conn, id)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to delete the author " + e.getMessage());
        }
    }

    private PreparedStatement createAuthorStatement(Connection connection, Author author) throws SQLException {
        final String SQL = "INSERT INTO library.author(author_name, author_surname) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, author.getName());
        statement.setString(2, author.getSurname());

        statement.executeUpdate();

        return statement;
    }

    private PreparedStatement readAuthorStatement(Connection connection, int idAuthor) throws SQLException {
        final String SQL = "SELECT idAuthor, author_name, author_surname FROM library.author WHERE idAuthor = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idAuthor);

        return statement;
    }

    private PreparedStatement updateAuthorStatement(Connection connection, Author author) throws SQLException {
        final String SQL = "UPDATE library.author SET author_name = ?, author_surname = ? WHERE idAuthor = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setString(1, author.getName());
        statement.setString(2, author.getSurname());
        statement.setInt(3, author.getId());

        return statement;
    }

    private PreparedStatement deleteAuthorStatement(Connection connection, int idAuthor) throws SQLException {
        final String SQL = "DELETE FROM library.author WHERE idAuthor = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idAuthor);


        return statement;
    }
}