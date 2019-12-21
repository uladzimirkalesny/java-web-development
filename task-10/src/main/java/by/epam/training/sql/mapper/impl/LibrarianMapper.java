package by.epam.training.sql.mapper.impl;

import by.epam.training.sql.entity.Librarian;
import by.epam.training.sql.exception.MapperException;
import by.epam.training.sql.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibrarianMapper implements Mapper<Librarian> {
    @Override
    public Librarian map(ResultSet rs) {
        try {
            return new Librarian(rs.getInt("idLibrarian"),
                                 rs.getString("librarian_name"),
                                 rs.getString("librarian_surname"));
        } catch (SQLException e) {
            throw new MapperException("Mapping exception to the librarian entity");
        }
    }
}
