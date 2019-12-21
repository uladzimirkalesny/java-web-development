package by.epam.training.cafe.decorator;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class Product is a concrete interface Ingredient implementation.
 *
 * @author Uladzimir Kalesny
 */
@Data
public final class Product implements Ingredient {
    /**
     * Product title.
     */
    private String title;
    /**
     * Product ingredients.
     */
    private List<Ingredient> ingredients;

    /**
     * Design pattern ProductBuilder look like as static class into Client entity.
     */
    public static final class ProductBuilder {
        /**
         * Optional parameter title.
         */
        private String title;
        /**
         * Optional parameter ingredients.
         */
        private List<Ingredient> ingredients = new ArrayList<>();

        /**
         * The builder’s setter methods.
         *
         * @param title textual data for builder field title
         * @return the builder itself
         */
        public ProductBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * The builder’s setter methods.
         *
         * @param ingredients few Ingredient instances
         * @return the builder itself
         */
        public ProductBuilder addIngredients(final Ingredient... ingredients) {
            this.ingredients.addAll(Arrays.asList(ingredients));
            return this;
        }

        /**
         * The builder’s setter methods.
         *
         * @param ingredient Ingredient instance
         * @return the builder itself
         */
        public ProductBuilder addIngredient(final Ingredient ingredient) {
            this.ingredients.add(ingredient);
            return this;
        }

        /**
         * Build method calls the private constructor of the outer class and
         * passes itself as the argument.
         *
         * @return Product instance
         */
        public Product build() {
            return new Product(this);
        }
    }

    /**
     * Private constructor for initializing fields via builder.
     *
     * @param builder ProductBuilder instance
     */
    private Product(final ProductBuilder builder) {
        this.title = builder.title;
        this.ingredients = builder.ingredients;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPrice() {
        return ingredients.stream().mapToInt(Ingredient::getPrice).sum();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Method for adding extra ingredient into product.
     *
     * @param ingredient extra Ingredient instance
     */
    public void addSomething(final Ingredient ingredient) {
        if (ingredient != null) {
            this.getIngredients().add(ingredient);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (ingredients != null) {
            ingredients.stream()
                    .map(Ingredient::getTitle)
                    .forEach(sb::append);
        }
        return title + ", with ingredients: " + sb.toString();
    }
}
