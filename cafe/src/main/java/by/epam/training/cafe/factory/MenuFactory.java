package by.epam.training.cafe.factory;

import by.epam.training.cafe.decorator.Ingredient;
import by.epam.training.cafe.entity.Menu;
import by.epam.training.cafe.enums.MenuType;
import by.epam.training.cafe.factory.menu.ChiefMenu;
import by.epam.training.cafe.factory.menu.CustomMenu;
import by.epam.training.cafe.factory.menu.SeasonMenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory MenuFactory for creating concrete menu instance.
 *
 * @author Uladzimir Kalesny
 */
public class MenuFactory {
    /**
     * Storage which keeps all available menu type.
     */
    private Map<MenuType, Menu> storage = new HashMap<>();

    {
        storage.put(MenuType.SEASON, new SeasonMenu());
        storage.put(MenuType.CHIEF, new ChiefMenu());
        storage.put(MenuType.CUSTOM, new CustomMenu());
    }

    /**
     * Method for generation menu.
     *
     * @param typeMenu    type Menu
     * @param ingredients products
     * @return concrete Menu instance
     */
    public Menu getMenu(final MenuType typeMenu, final List<Ingredient> ingredients) {
        Menu menu = storage.get(typeMenu);
        menu.setIngredients(ingredients);
        return menu;
    }
}
