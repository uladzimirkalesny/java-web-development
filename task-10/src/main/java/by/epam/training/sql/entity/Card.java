package by.epam.training.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private int id;
    private Reader reader;
    private Book book;
    private Librarian librarian;
    private LocalDate giveBookStamp;
    private LocalDate returnBookStamp;
    private String note;

    public Card(Reader reader, Book book, Librarian librarian, LocalDate giveBookStamp, LocalDate returnBookStamp, String note) {
        this.reader = reader;
        this.book = book;
        this.librarian = librarian;
        this.giveBookStamp = giveBookStamp;
        this.returnBookStamp = returnBookStamp;
        this.note = note;
    }
}
