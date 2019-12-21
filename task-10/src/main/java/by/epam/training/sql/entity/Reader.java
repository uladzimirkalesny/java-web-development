package by.epam.training.sql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reader {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String telephone;
    private boolean isActive;

    public Reader(String name, String surname, LocalDate birthday, String telephone, boolean isActive) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.telephone = telephone;
        this.isActive = isActive;
    }
}
