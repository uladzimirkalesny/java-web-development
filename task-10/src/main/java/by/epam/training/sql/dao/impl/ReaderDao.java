package by.epam.training.sql.dao.impl;

import by.epam.training.sql.dao.CrudDao;
import by.epam.training.sql.db.DbConnector;
import by.epam.training.sql.entity.Reader;
import by.epam.training.sql.exception.DaoException;
import by.epam.training.sql.mapper.Mapper;
import by.epam.training.sql.mapper.impl.ReaderMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderDao implements CrudDao<Reader> {
    private Mapper<Reader> readerMapper = new ReaderMapper();

    @Override
    public Reader create(Reader model) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = createReaderStatement(conn, model);
             ResultSet rs = statement.getGeneratedKeys()) {

            if (rs.next()) {
                model.setId(rs.getInt(1));
            } else {
                throw new DaoException("Error to getting id for reader ");
            }

        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to create the reader " + e.getMessage());
        }

        return model;
    }

    @Override
    public Reader read(int id) {
        Reader reader = null;

        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = readReaderStatement(conn, id);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                reader = readerMapper.map(rs);
            }

        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to read the reader " + e.getMessage());
        }

        return reader;
    }

    @Override
    public void update(Reader model) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = updateReaderStatement(conn, model)) {
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to update the reader " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = deleteReaderStatement(conn, id)) {
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to delete the reader " + e.getMessage());
        }
    }

    private PreparedStatement createReaderStatement(Connection connection, Reader reader) throws SQLException {
        final String SQL = "INSERT INTO library.reader(reader_name, reader_surname, reader_birthday, reader_telephone, reader_is_active) VALUES(?,?,?,?,?)";

        PreparedStatement statement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1, reader.getName());
        statement.setString(2, reader.getSurname());
        statement.setDate(3, Date.valueOf(reader.getBirthday()));
        statement.setString(4, reader.getTelephone());
        statement.setBoolean(5, reader.isActive());

        statement.executeUpdate();

        return statement;
    }

    private PreparedStatement readReaderStatement(Connection connection, int idReader) throws SQLException {
        final String SQL = "SELECT idReader, reader_name, reader_surname, reader_birthday, reader_telephone, reader_is_active FROM library.reader WHERE idReader = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idReader);

        return statement;
    }

    private PreparedStatement updateReaderStatement(Connection connection, Reader reader) throws SQLException {
        final String SQL = "UPDATE library.reader SET reader_name = ?, reader_surname = ?, reader_birthday = ?, reader_telephone = ?, reader_is_active = ? WHERE idReader = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setString(1, reader.getName());
        statement.setString(2, reader.getSurname());
        statement.setDate(3, Date.valueOf(reader.getBirthday()));
        statement.setString(4, reader.getTelephone());
        statement.setBoolean(5, reader.isActive());
        statement.setInt(6, reader.getId());

        return statement;
    }

    private PreparedStatement deleteReaderStatement(Connection connection, int idReader) throws SQLException {
        final String SQL = "DELETE FROM library.reader WHERE idReader = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idReader);

        return statement;
    }
}