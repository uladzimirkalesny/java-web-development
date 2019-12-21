package by.epam.training.cafe.entity;

import by.epam.training.cafe.decorator.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class Menu represent entity menu with fields inherent to menu.
 *
 * @author Uladzimir Kalesny
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Menu {
    /**
     * Ingredients for ordering.
     */
    private List<Ingredient> ingredients;

    /**
     * Method for printing menu information and full menu products.
     */
    public abstract void printMenu();
}
