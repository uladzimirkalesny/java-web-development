DROP SCHEMA IF EXISTS library;
CREATE SCHEMA library;

DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    role_id   INT(5)      NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(10) NOT NULL,
    CONSTRAINT PK_IdRole
        PRIMARY KEY (role_id)
);

DROP TABLE IF EXISTS author;
CREATE TABLE author
(
    author_id      INT(5)      NOT NULL AUTO_INCREMENT,
    author_name    VARCHAR(50) NOT NULL,
    author_surname VARCHAR(50) NOT NULL,

    CONSTRAINT PK_IdAuthor
        PRIMARY KEY (author_id)
);

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    user_id       INT(5)      NOT NULL AUTO_INCREMENT,
    user_name     VARCHAR(30) NOT NULL,
    user_surname  VARCHAR(30) NOT NULL,
    user_email    VARCHAR(30) NOT NULL,
    user_login    VARCHAR(30) NOT NULL,
    user_password VARCHAR(30) NOT NULL,
    user_birthday DATE        NOT NULL,

    CONSTRAINT PK_IdReader
        PRIMARY KEY (user_id)
);

DROP TABLE IF EXISTS book;
CREATE TABLE book
(
    book_id             INT(5)       NOT NULL AUTO_INCREMENT,
    book_title          VARCHAR(30)  NOT NULL,
    book_description    VARCHAR(100) NOT NULL,
    book_units_in_stock INT(5)       NOT NULL,

    CONSTRAINT PK_IdBook
        PRIMARY KEY (book_id)
);

DROP TABLE IF EXISTS authorBook;
CREATE TABLE authorBook
(
    author_book_id INT(5) NOT NULL AUTO_INCREMENT,
    id_author      INT(5) NOT NULL,
    id_book        INT(5) NOT NULL,

    CONSTRAINT PK_AuthorBook
        PRIMARY KEY (author_book_id),
    CONSTRAINT FK_IdAuthor
        FOREIGN KEY (id_author)
            REFERENCES author (author_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT FK_IdBook
        FOREIGN KEY (id_book)
            REFERENCES book (book_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
);

DROP TABLE IF EXISTS userRole;
CREATE TABLE userRole
(
    user_role_id INT(5) NOT NULL AUTO_INCREMENT,
    id_user      INT(5) NOT NULL,
    id_role      INT(5) NOT NULL,

    CONSTRAINT PK_UserRole
        PRIMARY KEY (user_role_id),
    CONSTRAINT FK_IdUser
        FOREIGN KEY (id_user)
            REFERENCES user (user_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_IdRole
        FOREIGN KEY (id_role)
            REFERENCES role (role_id)
            ON DELETE NO ACTION
            ON UPDATE CASCADE
);

DROP TABLE IF EXISTS userBook;
CREATE TABLE userBook
(
    user_book_id INT(5) NOT NULL AUTO_INCREMENT,
    id_user      INT(5) NOT NULL,
    id_book      INT(5) NOT NULL,

    CONSTRAINT PK_UserBook
        PRIMARY KEY (user_book_id),
    CONSTRAINT FK_IdUserBook
        FOREIGN KEY (id_user)
            REFERENCES user (user_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT FK_UB
        FOREIGN KEY (id_book)
            REFERENCES book (book_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)