package by.epam.training.cafe.decorator.ingredient;

import by.epam.training.cafe.decorator.Decorator;
import by.epam.training.cafe.decorator.Ingredient;
import lombok.NoArgsConstructor;

import static by.epam.training.cafe.constant.Constant.SYRUP;
import static by.epam.training.cafe.constant.Constant.SYRUP_PRICE;

/**
 * Class Syrup represents concrete Decorator instance.
 *
 * @author Uladzimir Kalesny
 */
@NoArgsConstructor
public class Syrup extends Decorator {
    /**
     * Constructor with argument.
     *
     * @param ingredient Ingredient instance
     */
    public Syrup(final Ingredient ingredient) {
        super(ingredient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice() {
        return super.getPrice() + SYRUP_PRICE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return super.getTitle() + SYRUP;
    }
}
