package by.epam.training.sql.mapper.impl;

import by.epam.training.sql.entity.Reader;
import by.epam.training.sql.exception.MapperException;
import by.epam.training.sql.mapper.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReaderMapper implements Mapper<Reader> {
    @Override
    public Reader map(ResultSet rs) {
        try {
            return new Reader(rs.getInt("idReader"),
                              rs.getString("reader_name"),
                              rs.getString("reader_surname"),
                              rs.getDate("reader_birthday").toLocalDate(),
                              rs.getString("reader_telephone"),
                              rs.getBoolean("reader_is_active"));
        } catch (SQLException e) {
            throw new MapperException("Mapping exception to the reader entity");
        }
    }
}
