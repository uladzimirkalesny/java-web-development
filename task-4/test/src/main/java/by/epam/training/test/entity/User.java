package by.epam.training.test.entity;

import by.epam.training.library.interfaces.Entity;
import by.epam.training.test.enums.SexType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private String name;
    private String email;
    private Integer age;
    private Entity<SexType, String> sex;

    @Override
    public String toString() {
        return "User : name: " + name + ", email: " + email + ", age: " + age + ", sex: " + sex + ";";
    }
}
