package by.epam.training.sql.dao.impl;

import by.epam.training.sql.dao.CrudDao;
import by.epam.training.sql.db.DbConnector;
import by.epam.training.sql.entity.Librarian;
import by.epam.training.sql.exception.DaoException;
import by.epam.training.sql.mapper.Mapper;
import by.epam.training.sql.mapper.impl.LibrarianMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianDao implements CrudDao<Librarian> {
    private Mapper<Librarian> librarianMapper = new LibrarianMapper();

    @Override
    public Librarian create(Librarian model) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = createLibrarianStatement(conn, model);
             ResultSet rs = statement.getGeneratedKeys()) {

            if (rs.next()) {
                model.setId(rs.getInt(1));
            } else {
                throw new DaoException("Error to getting id for author ");
            }

        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to create the librarian " + e.getMessage());
        }

        return model;
    }

    @Override
    public Librarian read(int id) {
        Librarian librarian = null;

        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = readLibrarianStatement(conn, id);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                librarian = librarianMapper.map(rs);
            }

            return librarian;
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to read the librarian " + e.getMessage());

        }
    }

    @Override
    public void update(Librarian model) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = updateReaderStatement(conn, model)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to update the librarian " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = deleteLibrarianStatement(conn, id)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to delete the librarian " + e.getMessage());
        }
    }

    private PreparedStatement createLibrarianStatement(Connection connection, Librarian librarian) throws SQLException {
        final String SQL = "INSERT INTO library.librarian(librarian_name, librarian_surname) VALUES (?, ?)";

        PreparedStatement statement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, librarian.getName());
        statement.setString(2, librarian.getSurname());

        statement.executeUpdate();

        return statement;
    }

    private PreparedStatement readLibrarianStatement(Connection connection, int idLibrarian) throws SQLException {
        final String SQL = "SELECT idLibrarian, librarian_name, librarian_surname FROM library.librarian WHERE idLibrarian = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idLibrarian);

        return statement;
    }

    private PreparedStatement updateReaderStatement(Connection connection, Librarian librarian) throws SQLException {
        final String SQL = "UPDATE library.librarian SET librarian_name = ?, librarian_surname = ? WHERE idLibrarian = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setString(1, librarian.getName());
        statement.setString(2, librarian.getSurname());
        statement.setInt(3, librarian.getId());

        return statement;
    }

    private PreparedStatement deleteLibrarianStatement(Connection connection, int idLibrarian) throws SQLException {
        final String SQL = "DELETE FROM library.librarian WHERE idLibrarian = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idLibrarian);

        return statement;
    }
}
