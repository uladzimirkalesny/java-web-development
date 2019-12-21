package by.epam.training.cafe.decorator.ingredient;

import by.epam.training.cafe.decorator.Decorator;
import by.epam.training.cafe.decorator.Ingredient;
import lombok.NoArgsConstructor;

import static by.epam.training.cafe.constant.Constant.WATER;
import static by.epam.training.cafe.constant.Constant.WATER_PRICE;

/**
 * Class Water represents concrete Decorator instance.
 *
 * @author Uladzimir Kalesny
 */
@NoArgsConstructor
public class Water extends Decorator {
    /**
     * Constructor with argument.
     *
     * @param ingredient Ingredient instance
     */
    public Water(final Ingredient ingredient) {
        super(ingredient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice() {
        return super.getPrice() + WATER_PRICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return super.getTitle() + WATER;
    }
}
