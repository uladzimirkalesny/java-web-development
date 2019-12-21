package by.epam.training.cafe.decorator.ingredient;

import by.epam.training.cafe.decorator.Decorator;
import by.epam.training.cafe.decorator.Ingredient;
import lombok.NoArgsConstructor;

import static by.epam.training.cafe.constant.Constant.COFFEE;
import static by.epam.training.cafe.constant.Constant.COFFEE_PRICE;

/**
 * Class Coffee represents concrete Decorator instance.
 *
 * @author Uladzimir Kalesny
 */
@NoArgsConstructor
public class Coffee extends Decorator {
    /**
     * Constructor with argument.
     *
     * @param ingredient Ingredient instance
     */
    public Coffee(final Ingredient ingredient) {
        super(ingredient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice() {
        return super.getPrice() + COFFEE_PRICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return super.getTitle() + COFFEE;
    }
}
