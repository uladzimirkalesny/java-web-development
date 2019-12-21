package by.epam.training.library.constant;

public class QueryConstant {
    public static final String FIND_ALL_BOOKS_SQL =
            "SELECT BOOK_ID, BOOK_TITLE, BOOK_DESCRIPTION, BOOK_UNITS_IN_STOCK, AUTHOR_ID, AUTHOR_NAME, AUTHOR_SURNAME FROM BOOK " +
            "LEFT JOIN AUTHORBOOK ON BOOK.BOOK_ID = AUTHORBOOK.ID_BOOK " +
            "LEFT JOIN AUTHOR ON AUTHORBOOK.ID_AUTHOR = AUTHOR.AUTHOR_ID";

    public static final String FIND_BOOK_BY_ID_SQL =
            "SELECT BOOK_ID, BOOK_TITLE, BOOK_DESCRIPTION, BOOK_UNITS_IN_STOCK, AUTHOR_ID, AUTHOR_NAME, AUTHOR_SURNAME FROM BOOK " +
            "LEFT JOIN AUTHORBOOK ON BOOK.BOOK_ID = AUTHORBOOK.ID_BOOK " +
            "LEFT JOIN AUTHOR ON AUTHORBOOK.ID_AUTHOR = AUTHOR.AUTHOR_ID " +
            "WHERE BOOK_ID = :id";

    public static final String INSERT_BOOK_SQL =
            "INSERT INTO BOOK (BOOK_TITLE, BOOK_DESCRIPTION, BOOK_UNITS_IN_STOCK)" +
            "VALUES (:title, :description, :unitsInStock)";

    public static final String UPDATE_BOOK_SQL =
            "UPDATE BOOK " +
            "SET BOOK_TITLE = :title, BOOK_DESCRIPTION = :description, BOOK_UNITS_IN_STOCK = :unitsInStock " +
            "WHERE BOOK_ID = :id";

    public static final String DELETE_BOOK_SQL =
            "DELETE FROM BOOK WHERE BOOK_ID = :id";

    public static final String FIND_AUTHOR_BY_ID_SQL =
            "SELECT AUTHOR_ID, AUTHOR_NAME, AUTHOR_SURNAME " +
            "FROM AUTHOR WHERE AUTHOR_ID = :id";

    public static final String FIND_ALL_AUTHOR_SQL =
            "SELECT AUTHOR_ID, AUTHOR_NAME, AUTHOR_SURNAME FROM AUTHOR";

    public static final String INSERT_AUTHOR_SQL =
            "INSERT INTO AUTHOR (AUTHOR_NAME, AUTHOR_SURNAME) " +
            "VALUES (:name, :surname)";

    public static final String BOOK_AUTHOR_REF_SQL =
            "INSERT INTO AUTHORBOOK (ID_AUTHOR, ID_BOOK) " +
            "VALUES (:authorId, :bookId)";

    public static final String UPDATE_AUTHOR_SQL =
            "UPDATE AUTHOR " +
            "SET AUTHOR_NAME = :name, AUTHOR_SURNAME = :surname " +
            "WHERE AUTHOR_ID = :id";

    public static final String DELETE_AUTHOR_SQL =
            "DELETE FROM AUTHOR WHERE AUTHOR_ID = :id";

    public static final String ID_PARAMETER = "id";
    public static final String AUTHOR_ID_PARAMETER = "authorId";
    public static final String BOOK_ID_PARAMETER = "bookId";
}
