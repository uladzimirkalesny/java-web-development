package by.epam.training.library.mapper;

import by.epam.training.library.domain.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.epam.training.library.constant.DDLConstant.AUTHOR_ID_COLUMN_LABEL;
import static by.epam.training.library.constant.DDLConstant.AUTHOR_NAME_COLUMN_LABEL;
import static by.epam.training.library.constant.DDLConstant.AUTHOR_SURNAME_COLUMN_LABEL;

public class SingleAuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Author.createAuthorBuilder()
                     .id(rs.getInt(AUTHOR_ID_COLUMN_LABEL))
                     .name(rs.getString(AUTHOR_NAME_COLUMN_LABEL))
                     .surname(rs.getString(AUTHOR_SURNAME_COLUMN_LABEL))
                     .build();
    }
}
