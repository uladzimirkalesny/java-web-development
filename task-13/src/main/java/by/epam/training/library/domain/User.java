package by.epam.training.library.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class User extends Person {

    private String login;
    private String email;
    private String roles;
    private LocalDate birthday;
    private List<Book> books;

    @Builder(builderMethodName = "createReaderBuilder")
    public User(int id,
                String name,
                String surname,
                String login,
                String email,
                String roles,
                LocalDate birthday,
                List<Book> books) {
        super(id, name, surname);
        this.login = login;
        this.email = email;
        this.roles = roles;
        this.birthday = birthday;
        this.books = books;
    }
}
