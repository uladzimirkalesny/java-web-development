package by.epam.training.sql.factory.impl;

import by.epam.training.sql.dao.impl.AuthorDao;
import by.epam.training.sql.dao.impl.BookDao;
import by.epam.training.sql.dao.impl.CardDao;
import by.epam.training.sql.dao.impl.LibrarianDao;
import by.epam.training.sql.dao.impl.ReaderDao;
import by.epam.training.sql.factory.DaoFactory;

public class DaoFactoryImpl implements DaoFactory {
    @Override
    public AuthorDao createAuthorDao() {
        return new AuthorDao();
    }

    @Override
    public ReaderDao createReaderDao() {
        return new ReaderDao();
    }

    @Override
    public LibrarianDao createLibrarianDao() {
        return new LibrarianDao();
    }

    @Override
    public BookDao createBookDao() {
        return new BookDao();
    }

    @Override
    public CardDao createCardDao() {
        return new CardDao();
    }
}
