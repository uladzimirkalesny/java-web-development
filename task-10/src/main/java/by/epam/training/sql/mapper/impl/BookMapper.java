package by.epam.training.sql.mapper.impl;

import by.epam.training.sql.entity.Author;
import by.epam.training.sql.entity.Book;
import by.epam.training.sql.exception.MapperException;
import by.epam.training.sql.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements Mapper<Book> {
    @Override
    public Book map(ResultSet rs) {
        try {
            return new Book(rs.getInt("idBook"),
                            rs.getString("book_title"),
                            rs.getString("book_description"),
                            new Author(rs.getInt("idAuthor"),
                                       rs.getString("author_name"),
                                       rs.getString("author_surname")));
        } catch (SQLException e) {
            throw new MapperException("Mapping exception to the full book info that includes book and author info " + e.getMessage());
        }
    }
}
