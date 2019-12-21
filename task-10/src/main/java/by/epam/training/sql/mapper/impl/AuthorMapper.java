package by.epam.training.sql.mapper.impl;

import by.epam.training.sql.entity.Author;
import by.epam.training.sql.exception.MapperException;
import by.epam.training.sql.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements Mapper<Author> {
    @Override
    public Author map(ResultSet rs) {
        try {
            return new Author(rs.getInt("idAuthor"),
                              rs.getString("author_name"),
                              rs.getString("author_surname"));
        } catch (SQLException e) {
            throw new MapperException("Mapping exception to the author entity");
        }
    }
}
