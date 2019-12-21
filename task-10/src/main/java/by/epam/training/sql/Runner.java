package by.epam.training.sql;

import by.epam.training.sql.dao.impl.AuthorDao;
import by.epam.training.sql.dao.impl.BookDao;
import by.epam.training.sql.dao.impl.CardDao;
import by.epam.training.sql.dao.impl.LibrarianDao;
import by.epam.training.sql.dao.impl.ReaderDao;
import by.epam.training.sql.entity.Author;
import by.epam.training.sql.entity.Book;
import by.epam.training.sql.entity.Card;
import by.epam.training.sql.entity.Librarian;
import by.epam.training.sql.entity.Reader;
import by.epam.training.sql.factory.DaoFactory;
import by.epam.training.sql.factory.impl.DaoFactoryImpl;

import java.time.LocalDate;

public class Runner {

    public static void main(String[] args) {

        final DaoFactory factory = new DaoFactoryImpl();

        final AuthorDao authorDao = factory.createAuthorDao();
        Author newAuthor = new Author("Michael", "Jordan");
        newAuthor = authorDao.create(newAuthor);
        System.out.println(authorDao.read(newAuthor.getId()));
        newAuthor.setName("UPDATED");
        authorDao.update(newAuthor);
        System.out.println(authorDao.read(newAuthor.getId()));
        authorDao.delete(newAuthor.getId());


        final ReaderDao readerDao = factory.createReaderDao();
        Reader newReader = new Reader("Michael", "Jordan", LocalDate.of(1965, 10, 27), "80253219823", false);
        newReader = readerDao.create(newReader);
        System.out.println(readerDao.read(newReader.getId()));
        newReader.setActive(true);
        readerDao.update(newReader);
        System.out.println(readerDao.read(newReader.getId()));
        readerDao.delete(newReader.getId());


        final LibrarianDao librarianDao = factory.createLibrarianDao();
        Librarian newLibrarian = new Librarian("Michael", "Jordan");
        newLibrarian = librarianDao.create(newLibrarian);
        System.out.println(librarianDao.read(newLibrarian.getId()));
        newLibrarian.setName("Paul");
        librarianDao.update(newLibrarian);
        System.out.println(librarianDao.read(newLibrarian.getId()));
        librarianDao.delete(newLibrarian.getId());


        final BookDao bookDao = factory.createBookDao();
        Author bookAuthor = new Author("Oleg", "Ivanov");
        Book newBook = new Book("New", "Book", bookAuthor);
        newBook = bookDao.create(newBook);
        System.out.println(bookDao.read(newBook.getId()));
        newBook.setTitle("UPDATED");
        bookDao.update(newBook);
        System.out.println(bookDao.read(newBook.getId()));
        bookDao.delete(newBook.getId());


        final CardDao cardDao = factory.createCardDao();
        final Reader newCardReader = new Reader("Card", "Reader", LocalDate.of(1988, 8, 19), "80259939321", true);
        final Author newCardAuthor = new Author("Card", "Author");
        final Book newCardBook = new Book("Card", "Book", newCardAuthor);
        final Librarian newCardLibrarian = new Librarian("Card", "Librarian");
        Card newCard = new Card(newCardReader,
                             newCardBook,
                             newCardLibrarian,
                             LocalDate.now(),
                             LocalDate.now().plusWeeks(1),
                             "no");
        newCard = cardDao.create(newCard);
        System.out.println(cardDao.read(newCard.getId()));
        newCard.setNote("YES UPDATED");
        cardDao.update(newCard);
        System.out.println(cardDao.read(newCard.getId()));
        cardDao.delete(newCard.getId());
    }
}
