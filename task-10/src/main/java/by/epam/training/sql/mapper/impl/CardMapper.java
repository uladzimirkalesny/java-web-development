package by.epam.training.sql.mapper.impl;

import by.epam.training.sql.entity.Book;
import by.epam.training.sql.entity.Card;
import by.epam.training.sql.entity.Librarian;
import by.epam.training.sql.entity.Reader;
import by.epam.training.sql.exception.MapperException;
import by.epam.training.sql.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CardMapper implements Mapper<Card> {
    private Mapper<Reader> readerMapper = new ReaderMapper();
    private Mapper<Book> bookMapper = new BookMapper();
    private Mapper<Librarian> librarianMapper = new LibrarianMapper();

    @Override
    public Card map(ResultSet rs) {
        try {
            return new Card(rs.getInt("idCard"),
                            readerMapper.map(rs),
                            bookMapper.map(rs),
                            librarianMapper.map(rs),
                            rs.getDate("card_give_book_stamp").toLocalDate(),
                            rs.getDate("card_return_book_stamp").toLocalDate(),
                            rs.getString("card_note"));
        } catch (SQLException e) {
            throw new MapperException("Mapping exception to the card entity " + e.getMessage());
        }
    }
}
