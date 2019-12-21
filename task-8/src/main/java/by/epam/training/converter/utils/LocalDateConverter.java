package by.epam.training.converter.utils;

import by.epam.training.converter.entity.Birthday;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class LocalDateConverter {
    public LocalDate toLocalDate(Birthday birthday) {
        return LocalDate.of(birthday.getYear(), birthday.getMonth(), birthday.getDay());
    }
}
