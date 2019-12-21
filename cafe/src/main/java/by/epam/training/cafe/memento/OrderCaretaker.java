package by.epam.training.cafe.memento;

import lombok.Data;

/**
 * Class OrderCaretaker represent caretaker from pattern design Memento.
 *
 * @author Uladzimir Kalesny
 */
@Data
public class OrderCaretaker {
    /**
     * Memento instance.
     */
    private OrderMemento memento;
}
