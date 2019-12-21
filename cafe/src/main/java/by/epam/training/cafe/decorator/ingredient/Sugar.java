package by.epam.training.cafe.decorator.ingredient;

import by.epam.training.cafe.decorator.Decorator;
import by.epam.training.cafe.decorator.Ingredient;
import lombok.NoArgsConstructor;

import static by.epam.training.cafe.constant.Constant.SUGAR;
import static by.epam.training.cafe.constant.Constant.SUGAR_PRICE;

/**
 * Class Sugar represents concrete Decorator instance.
 *
 * @author Uladzimir Kalesny
 */
@NoArgsConstructor
public class Sugar extends Decorator {
    /**
     * Constructor with argument.
     *
     * @param ingredient Ingredient instance
     */
    public Sugar(final Ingredient ingredient) {
        super(ingredient);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice() {
        return super.getPrice() + SUGAR_PRICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return super.getTitle() + SUGAR;
    }
}
