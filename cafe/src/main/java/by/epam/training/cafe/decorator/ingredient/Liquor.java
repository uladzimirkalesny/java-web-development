package by.epam.training.cafe.decorator.ingredient;

import by.epam.training.cafe.decorator.Decorator;
import by.epam.training.cafe.decorator.Ingredient;
import lombok.NoArgsConstructor;

import static by.epam.training.cafe.constant.Constant.LIQUOR;
import static by.epam.training.cafe.constant.Constant.LIQUOR_PRICE;

/**
 * Class Liquor represents concrete Decorator instance.
 *
 * @author Uladzimir Kalesny
 */
@NoArgsConstructor
public class Liquor extends Decorator {
    /**
     * Constructor with argument.
     *
     * @param ingredient Ingredient instance
     */
    public Liquor(final Ingredient ingredient) {
        super(ingredient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice() {
        return super.getPrice() + LIQUOR_PRICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return super.getTitle() + LIQUOR;
    }
}
