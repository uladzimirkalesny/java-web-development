package by.epam.training.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private int id;
    private String title;
    private String description;
    private Author author;

    public Book(String title, String description, Author author) {
        this.title = title;
        this.description = description;
        this.author = author;
    }
}
