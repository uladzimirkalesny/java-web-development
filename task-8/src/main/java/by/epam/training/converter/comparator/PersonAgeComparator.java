package by.epam.training.converter.comparator;

import by.epam.training.converter.entity.Person;
import by.epam.training.converter.utils.LocalDateConverter;

import java.time.LocalDate;
import java.util.Comparator;

public class PersonAgeComparator implements Comparator<Person> {
    private LocalDateConverter converter;

    public PersonAgeComparator() {
        this.converter = new LocalDateConverter();
    }

    @Override
    public int compare(Person person1, Person person2) {
        LocalDate age1 = converter.toLocalDate(person1.getBirthday());
        LocalDate age2 = converter.toLocalDate(person2.getBirthday());

        return age1.compareTo(age2);
    }
}
