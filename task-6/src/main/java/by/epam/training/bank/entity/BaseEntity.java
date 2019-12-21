package by.epam.training.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {
    private int id;

    @Override
    public String toString() {
        return "id=" + id;
    }
}
