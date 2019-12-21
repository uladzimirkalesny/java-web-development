package by.epam.training.cafe.memento;

import by.epam.training.cafe.decorator.Product;
import by.epam.training.cafe.entity.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class OrderMemento represent memento from pattern design Memento.
 *
 * @author Uladzimir Kalesny
 */
@Getter
@AllArgsConstructor
public class OrderMemento {
    /**
     * Product instance.
     */
    private final Product product;
    /**
     * Client instance.
     */
    private final Client client;
}
