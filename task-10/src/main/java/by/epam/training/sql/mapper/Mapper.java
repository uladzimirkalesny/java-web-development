package by.epam.training.sql.mapper;

import java.sql.ResultSet;

public interface Mapper<T> {
    T map(ResultSet rs);
}
