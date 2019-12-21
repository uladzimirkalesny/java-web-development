package by.epam.training.cafe.decorator.ingredient;

import by.epam.training.cafe.decorator.Decorator;
import by.epam.training.cafe.decorator.Ingredient;
import lombok.NoArgsConstructor;

import static by.epam.training.cafe.constant.Constant.MILK;
import static by.epam.training.cafe.constant.Constant.MILK_PRICE;

/**
 * Class Milk represents concrete Decorator instance.
 *
 * @author Uladzimir Kalesny
 */
@NoArgsConstructor
public class Milk extends Decorator {
    /**
     * Constructor with argument.
     *
     * @param ingredient Ingredient instance
     */
    public Milk(final Ingredient ingredient) {
        super(ingredient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice() {
        return super.getPrice() + MILK_PRICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return super.getTitle() + MILK;
    }
}
