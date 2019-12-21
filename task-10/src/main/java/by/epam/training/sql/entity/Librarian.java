package by.epam.training.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Librarian {
    private int id;
    private String name;
    private String surname;

    public Librarian(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
