package by.epam.training.cafe.factory.menu;

import by.epam.training.cafe.decorator.Ingredient;
import by.epam.training.cafe.entity.Menu;
import by.epam.training.cafe.enums.MenuType;
import lombok.NoArgsConstructor;

import java.util.List;

import static by.epam.training.cafe.constant.Constant.*;

/**
 * Class CustomMenu represent custom client menu.
 *
 * @author Uladzimir Kalesny
 */
@NoArgsConstructor
public class CustomMenu extends Menu {
    /**
     * Constructor with parameter.
     *
     * @param ingredients products instance
     */
    public CustomMenu(final List<Ingredient> ingredients) {
        super(ingredients);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printMenu() {
        System.out.println(MENU_HEADER + MenuType.CUSTOM);

        getIngredients().forEach(i -> {
            System.out.println(PRODUCT_HEADER + i.getTitle() + ", " + PRICE_HEADER + i.getPrice());
        });
    }
}
