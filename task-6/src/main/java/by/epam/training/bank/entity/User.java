package by.epam.training.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class User extends BaseEntity {
    private String name;
    private String secondName;
    private SexType sex;
    private LocalDate birthday;

    public enum SexType {
        MALE, FEMALE, ANY;

        @Override
        public String toString() {
            return name().toUpperCase();
        }
    }
}
