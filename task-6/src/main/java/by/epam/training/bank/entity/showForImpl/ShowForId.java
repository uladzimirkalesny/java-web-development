package by.epam.training.bank.entity.showForImpl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShowForId extends ShowFor {
    private List<Integer> users;

    @Override
    public String toString() {
        return super.toString() +
                "users=" + users +
                '}';
    }
}


