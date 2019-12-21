package by.epam.training.bank.entity.showForImpl;

import by.epam.training.bank.entity.User;
import by.epam.training.bank.enums.ShowForType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ShowFor {
    private ShowForType type;

    public abstract <T> List<T> getUsers();

    @Override
    public String toString() {
        return "ShowFor{" +
                "type=" + type +
                '}';
    }
}

