package by.epam.training.converter.utils;

import by.epam.training.converter.entity.Person;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class PeopleIndexRefresher {
    public List<Person> refresh(List<Person> people) {
        for (int i = 0; i < people.size(); ) {
            people.get(i).setId(++i);
        }

        return people;
    }
}