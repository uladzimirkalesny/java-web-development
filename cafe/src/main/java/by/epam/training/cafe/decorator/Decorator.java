package by.epam.training.cafe.decorator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract class Decorator represent super class as part of pattern design Decorator.
 *
 * @author Uladzimir Kalesny
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Decorator implements Ingredient {
    /**
     * Ingredient for decoration.
     */
    private Ingredient ingredient;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice() {
        int result = 0;

        if (ingredient != null) {
            result = ingredient.getPrice();
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        String result = "";

        if (ingredient != null) {
            result = ingredient.getTitle();
        }

        return result;
    }
}
