package by.epam.training.cafe.entity;

import by.epam.training.cafe.decorator.Product;
import by.epam.training.cafe.memento.OrderMemento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static by.epam.training.cafe.constant.Constant.CLIENT_INFO_HEADER;
import static by.epam.training.cafe.constant.Constant.PRODUCT_INFO_HEADER;

/**
 * Class Order represent entity order with fields inherent to order.
 *
 * @author Uladzimir Kalesny
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
     * Client instance.
     */
    private Client client;
    /**
     * Product instance.
     */
    private Product product;

    /**
     * Method save provides logic to save order state.
     *
     * @return OrderMemento instance
     */
    public OrderMemento save() {
        return new OrderMemento(product, client);
    }

    /**
     * Method restore provides logic to restore order state.
     *
     * @param memento OrderMemento instance
     */
    public void restore(final OrderMemento memento) {
        this.client = memento.getClient();
        this.product = memento.getProduct();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return CLIENT_INFO_HEADER + client + "\n" + PRODUCT_INFO_HEADER + product;
    }
}
