package by.epam.training.library.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class Author extends Person {
    private List<Book> books;

    @Builder(builderMethodName = "createAuthorBuilder")
    public Author(int id, String name, String surname, List<Book> books) {
        super(id, name, surname);
        this.books = books;
    }
}
