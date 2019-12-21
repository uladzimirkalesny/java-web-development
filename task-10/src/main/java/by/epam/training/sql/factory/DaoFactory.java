package by.epam.training.sql.factory;

import by.epam.training.sql.dao.impl.AuthorDao;
import by.epam.training.sql.dao.impl.BookDao;
import by.epam.training.sql.dao.impl.CardDao;
import by.epam.training.sql.dao.impl.LibrarianDao;
import by.epam.training.sql.dao.impl.ReaderDao;

public interface DaoFactory {
    AuthorDao createAuthorDao();

    ReaderDao createReaderDao();

    LibrarianDao createLibrarianDao();

    BookDao createBookDao();

    CardDao createCardDao();
}
