CREATE SCHEMA IF NOT EXISTS library;
USE library;

CREATE TABLE library.reader (
	idReader INT(5) NOT NULL AUTO_INCREMENT,
	reader_name VARCHAR(30) NOT NULL,
	reader_surname VARCHAR(30) NOT NULL,
	reader_birthday DATE NOT NULL,
	reader_telephone VARCHAR(45) NOT NULL,
	reader_is_active BOOLEAN DEFAULT FALSE,

	CONSTRAINT PK_IdReader PRIMARY KEY (idReader)
);

CREATE TABLE library.librarian (
    idLibrarian INT(5) NOT NULL AUTO_INCREMENT,
    librarian_name VARCHAR(30) NOT NULL,
	librarian_surname VARCHAR(30) NOT NULL,

	CONSTRAINT PK_IdLibrarian
	    PRIMARY KEY (idLibrarian)
);

CREATE TABLE library.author (
    idAuthor INT(5) NOT NULL AUTO_INCREMENT,
    author_name VARCHAR(50) NOT NULL,
    author_surname VARCHAR(50) NOT NULL,

    CONSTRAINT PK_IdAuthor
        PRIMARY KEY (idAuthor)
);

CREATE TABLE library.book (
    idBook INT(5) NOT NULL AUTO_INCREMENT,
    book_title VARCHAR(30) NOT NULL,
    book_description VARCHAR(100) NOT NULL,
    author_id INT(5),

    CONSTRAINT PK_IdBook
        PRIMARY KEY (idBook),
    CONSTRAINT FK_BookAuthor
        FOREIGN KEY (author_id)
        REFERENCES library.author(idAuthor)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
);

CREATE TABLE library.card (
    idCard INT(5) NOT NULL AUTO_INCREMENT,
    reader_id INT(5),
    book_id INT(5),
    librarian_id INT(5),
    card_give_book_stamp DATE DEFAULT NULL,
    card_return_book_stamp DATE DEFAULT NULL,
    card_note VARCHAR(50) DEFAULT NULL,

    CONSTRAINT PK_IdCard
        PRIMARY KEY (idCard),
    CONSTRAINT FK_ReaderCard
        FOREIGN KEY (reader_id)
        REFERENCES library.reader(idReader)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FK_BookCard
        FOREIGN KEY (book_id)
        REFERENCES library.book(idBook)
        ON DELETE NO ACTION
        ON UPDATE CASCADE,
    CONSTRAINT FK_LibrarianCard
        FOREIGN KEY (librarian_id)
        REFERENCES library.librarian(idLibrarian)
        ON DELETE NO ACTION
        ON UPDATE CASCADE
);

INSERT INTO library.reader(idReader, reader_name, reader_surname, reader_birthday, reader_telephone, reader_is_active)VALUES (1, 'Carmelo', 'Anthony', '1979.05.15', '80252342323', TRUE);
INSERT INTO library.reader(idReader, reader_name, reader_surname, reader_birthday, reader_telephone, reader_is_active)VALUES (2, 'LeBron', 'James', '1984.06.17', '80252220606', TRUE);
INSERT INTO library.reader(idReader, reader_name, reader_surname, reader_birthday, reader_telephone, reader_is_active)VALUES (3, 'Kyle', 'Kuzma', '1994.10.25', '80253578726', FALSE);
INSERT INTO library.reader(idReader, reader_name, reader_surname, reader_birthday, reader_telephone, reader_is_active)VALUES (4, 'Paul', 'George', '1986.09.03', '80253205113', TRUE);
INSERT INTO library.reader(idReader, reader_name, reader_surname, reader_birthday, reader_telephone, reader_is_active)VALUES (5, 'James', 'Harden', '1989.12.15', '80252131313', FALSE);

INSERT INTO library.librarian(idLibrarian, librarian_name, librarian_surname) VALUES (1, 'Sandra', 'Lee');
INSERT INTO library.librarian(idLibrarian, librarian_name, librarian_surname) VALUES (2, 'Brad', 'Johnes');
INSERT INTO library.librarian(idLibrarian, librarian_name, librarian_surname) VALUES (3, 'Steven', 'Adams');
INSERT INTO library.librarian(idLibrarian, librarian_name, librarian_surname) VALUES (4, 'Daniel', 'Cormier');
INSERT INTO library.librarian(idLibrarian, librarian_name, librarian_surname) VALUES (5, 'Connor', 'McGregor');

INSERT INTO library.author(idAuthor, author_name, author_surname) VALUES (1, 'Alex', 'Prince');
INSERT INTO library.author(idAuthor, author_name, author_surname) VALUES (2, 'Artur', 'Lion');
INSERT INTO library.author(idAuthor, author_name, author_surname) VALUES (3, 'Leo', 'Messi');
INSERT INTO library.author(idAuthor, author_name, author_surname) VALUES (4, 'Cristiano', 'Ronaldo');
INSERT INTO library.author(idAuthor, author_name, author_surname) VALUES (5, 'Alexandr', 'Golovin');

INSERT INTO library.book(idBook, book_title, book_description, author_id) VALUES (1, 'Random n1', 'Random desc n1', 1);
INSERT INTO library.book(idBook, book_title, book_description, author_id) VALUES (2, 'Random n2', 'Random desc n2', 2);
INSERT INTO library.book(idBook, book_title, book_description, author_id) VALUES (3, 'Random n3', 'Random desc n3', 3);
INSERT INTO library.book(idBook, book_title, book_description, author_id) VALUES (4, 'Random n4', 'Random desc n4', 4);
INSERT INTO library.book(idBook, book_title, book_description, author_id) VALUES (5, 'Random n5', 'Random desc n5', 5);

INSERT INTO library.card(idCard, reader_id, book_id, librarian_id, card_give_book_stamp, card_return_book_stamp, card_note) VALUES (1, 1, 1, 1, '2016-01-02', '2016-01-09', 'careful use');
INSERT INTO library.card(idCard, reader_id, book_id, librarian_id, card_give_book_stamp, card_return_book_stamp, card_note) VALUES (2, 2, 2, 2, '2018-12-23', '2018-12-30', 'careful use');
INSERT INTO library.card(idCard, reader_id, book_id, librarian_id, card_give_book_stamp, card_return_book_stamp, card_note) VALUES (3, 3, 3, 3, '2016-01-30', '2017-03-01', 'careless use');
INSERT INTO library.card(idCard, reader_id, book_id, librarian_id, card_give_book_stamp, card_return_book_stamp, card_note) VALUES (4, 4, 4, 4, '2019-04-13', '2019-05-20', 'careful use');
INSERT INTO library.card(idCard, reader_id, book_id, librarian_id, card_give_book_stamp, card_return_book_stamp, card_note) VALUES (5, 5, 5, 5, '2017-10-02', '2017-10-30', 'careful use');