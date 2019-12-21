package by.epam.training.cafe.decorator;

/**
 * Interface Ingredient provides contract to concrete implementations and
 * to decorator and his implementations.
 *
 * @author Uladzimir Kalesny
 */
public interface Ingredient {
    /**
     * Method for getting numeric price for ingredient.
     *
     * @return numeric price
     */
    int getPrice();

    /**
     * Method for getting textual description about ingredient.
     *
     * @return textual title
     */
    String getTitle();
}
