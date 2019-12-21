package by.epam.training.library.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Person {
    private int id;
    private String name;
    private String surname;

    public Person(int id) {
        this.id = id;
    }
}

