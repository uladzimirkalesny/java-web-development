package by.epam.training.bank.entity.showForImpl;

import by.epam.training.bank.dao.impl.UserDaoImpl;
import by.epam.training.bank.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShowForName extends ShowFor {
    private List<String> users;
    private UserDaoImpl userDao;

    @Override
    public String toString() {
        return super.toString() +
                "users=" + users +
                '}';
    }
}
