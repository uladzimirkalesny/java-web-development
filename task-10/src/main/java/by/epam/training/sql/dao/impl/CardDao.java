package by.epam.training.sql.dao.impl;

import by.epam.training.sql.dao.CrudDao;
import by.epam.training.sql.db.DbConnector;
import by.epam.training.sql.entity.Book;
import by.epam.training.sql.entity.Card;
import by.epam.training.sql.entity.Librarian;
import by.epam.training.sql.entity.Reader;
import by.epam.training.sql.exception.DaoException;
import by.epam.training.sql.mapper.Mapper;
import by.epam.training.sql.mapper.impl.CardMapper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDao implements CrudDao<Card> {
    private Mapper<Card> cardMapper = new CardMapper();
    private CrudDao<Reader> readerDao = new ReaderDao();
    private CrudDao<Book> bookDao = new BookDao();
    private CrudDao<Librarian> librarianDao = new LibrarianDao();

    @Override
    public Card create(Card model) {

        setRelationshipEntityIfNotExist(model);

        try (Connection connection = DbConnector.getInstance().getConnection();
             PreparedStatement statement = createCardStatement(connection, model);
             ResultSet rs = statement.getGeneratedKeys()) {

            if (rs.next()) {
                model.setId(rs.getInt(1));
            } else {
                throw new DaoException("Error to getting id for card ");
            }

        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to create the card " + e.getMessage());
        }

        return model;
    }

    @Override
    public Card read(int id) {
        Card card = null;

        try (Connection conn = DbConnector.getInstance().getConnection();
             PreparedStatement statement = readCardStatement(conn, id);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                card = cardMapper.map(rs);
            }

            return card;
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to read the card " + e.getMessage());
        }
    }

    @Override
    public void update(Card model) {
        try (Connection connection = DbConnector.getInstance().getConnection();
             PreparedStatement statement = updateCardStatement(connection, model)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to update the card " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DbConnector.getInstance().getConnection();
             PreparedStatement statement = deleteCardStatement(connection, id)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("An exception occurred while trying to delete the card " + e.getMessage());
        }
    }

    private PreparedStatement createCardStatement(Connection connection, Card card) throws SQLException {
        final String SQL = "INSERT INTO library.card(reader_id, book_id, librarian_id, card_give_book_stamp, card_return_book_stamp, card_note) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setInt(1, card.getReader().getId());
        statement.setInt(2, card.getBook().getId());
        statement.setInt(3, card.getLibrarian().getId());
        statement.setDate(4, Date.valueOf(card.getGiveBookStamp()));
        statement.setDate(5, Date.valueOf(card.getReturnBookStamp()));
        statement.setString(6, card.getNote());

        statement.executeUpdate();

        return statement;
    }

    private PreparedStatement readCardStatement(Connection connection, int idCard) throws SQLException {
        final String SQL = "SELECT idCard, idReader, reader_name, reader_surname, reader_birthday, reader_telephone, reader_is_active, " +
                "idBook, book_title, book_description, " +
                "idAuthor, author_name, author_surname, " +
                "idLibrarian, librarian_name, librarian_surname, " +
                "card_give_book_stamp, card_return_book_stamp, card_note FROM library.card " +
                "LEFT OUTER JOIN library.reader ON reader_id = idReader " +
                "LEFT OUTER JOIN library.book ON book_id = idBook " +
                "LEFT OUTER JOIN library.author ON book.author_id = author.idAuthor " +
                "LEFT OUTER JOIN library.librarian ON librarian_id = idLibrarian " +
                "WHERE idCard = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idCard);

        return statement;
    }

    private PreparedStatement updateCardStatement(Connection connection, Card card) throws SQLException {
        final String SQL = "UPDATE library.card SET reader_id = ?, book_id = ?, librarian_id = ?, card_give_book_stamp = ?, card_return_book_stamp = ?, card_note = ? WHERE idCard = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, card.getReader().getId());
        statement.setInt(2, card.getBook().getId());
        statement.setInt(3, card.getLibrarian().getId());
        statement.setDate(4, Date.valueOf(card.getGiveBookStamp()));
        statement.setDate(5, Date.valueOf(card.getReturnBookStamp()));
        statement.setString(6, card.getNote());
        statement.setInt(7, card.getId());

        return statement;
    }

    private PreparedStatement deleteCardStatement(Connection connection, int idCard) throws SQLException {
        final String SQL = "DELETE FROM library.card WHERE idCard = ?";

        PreparedStatement statement = connection.prepareStatement(SQL);
        statement.setInt(1, idCard);

        return statement;
    }

    private void setRelationshipEntityIfNotExist(Card model) {
        if (model.getReader().getId() == 0) {
            Reader reader = readerDao.create(model.getReader());
            model.setReader(reader);
        }

        if (model.getBook().getId() == 0) {
            Book book = bookDao.create(model.getBook());
            model.setBook(book);
        }

        if (model.getLibrarian().getId() == 0) {
            Librarian librarian = librarianDao.create(model.getLibrarian());
            model.setLibrarian(librarian);
        }
    }
}
