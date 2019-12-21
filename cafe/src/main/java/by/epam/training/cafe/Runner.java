package by.epam.training.cafe;

import by.epam.training.cafe.decorator.Ingredient;
import by.epam.training.cafe.decorator.Product;
import by.epam.training.cafe.decorator.ingredient.Coffee;
import by.epam.training.cafe.decorator.ingredient.Cream;
import by.epam.training.cafe.decorator.ingredient.Liquor;
import by.epam.training.cafe.decorator.ingredient.Milk;
import by.epam.training.cafe.decorator.ingredient.Sugar;
import by.epam.training.cafe.decorator.ingredient.Syrup;
import by.epam.training.cafe.decorator.ingredient.Water;
import by.epam.training.cafe.entity.Client;
import by.epam.training.cafe.entity.Menu;
import by.epam.training.cafe.entity.Order;
import by.epam.training.cafe.enums.AmateurType;
import by.epam.training.cafe.enums.GenderType;
import by.epam.training.cafe.enums.MenuType;
import by.epam.training.cafe.factory.MenuFactory;
import by.epam.training.cafe.memento.OrderCaretaker;
import by.epam.training.cafe.memento.OrderMemento;

import java.util.Collections;

import static by.epam.training.cafe.constant.Constant.*;

public class Runner {
    public static void main(final String[] args) {

        Client fullFieldClient = new Client.ClientBuilder()
                .name(JOSHUA_BLOCH_NAME)
                .surname(JOSHUA_BLOCH_SURNAME)
                .age(JOSHUA_BLOCH_AGE)
                .gender(GenderType.MALE)
                .address(JOSHUA_BLOCH_ADDRESS)
                .amateur(AmateurType.INVETERATE)
                .build();

        System.out.println(FULL_FIELD_CLIENT_HEADER + fullFieldClient);

        Client partFieldClient = new Client.ClientBuilder()
                .name(HAUDI_HO_NAME)
                .surname(HAUDI_HO_SURNAME)
                .age(HAUDI_HO_AGE)
                .amateur(AmateurType.REGULAR)
                .build();

        System.out.println(PART_FIELD_CLIENT_HEADER + partFieldClient + "\n");

        Ingredient chiefRecipe =
                new Coffee(new Water(new Sugar(new Milk(new Syrup(new Cream(new Liquor()))))));

        Product chiefProduct = new Product.ProductBuilder()
                .title(CHIEF_PRODUCT_TITLE)
                .addIngredient(chiefRecipe)
                .build();

        System.out.println(ORDER_PREPARATION_HEADER + chiefProduct);
        System.out.println(PRICE_HEADER + chiefProduct.getTitle() + DELIMETER
                + chiefProduct.getPrice() + "\n");

        Product customProduct = new Product.ProductBuilder()
                .title(CUSTOM_PRODUCT_TITLE)
                .addIngredient(new Coffee())
                .addIngredients(new Water(new Liquor()))
                .build();

        System.out.println(ORDER_PREPARATION_HEADER + customProduct);
        System.out.println(PRICE_HEADER + customProduct.getTitle() + DELIMETER
                + customProduct.getPrice() + "\n");

        MenuFactory factory = new MenuFactory();
        Menu chiefMenu = factory.getMenu(MenuType.CHIEF, Collections.singletonList(chiefProduct));
        chiefMenu.printMenu();

        Order order = new Order(fullFieldClient, chiefProduct);
        OrderMemento memento = order.save();
        OrderCaretaker caretaker = new OrderCaretaker();
        caretaker.setMemento(memento);

        System.out.println(SAVE_ORDER_HEADER + "\n" + order + "\n" + PRICE_DELIMETER_HEADER
                + order.getProduct().getPrice());
        System.out.println("\n" + INVALID_MODIFICATION_HEADER);
        order.setClient(partFieldClient);
        System.out.println(order);

        order.restore(caretaker.getMemento());
        System.out.println("\n" + RESTORE_HEADER + "\n" + order + "\n" + PRICE_DELIMETER_HEADER
                + order.getProduct().getPrice());

        System.out.println("\n" + order.getClient().getName()
                + ADD_SOMETHING + chiefProduct.getTitle() + DELIMETER);
        chiefProduct.addSomething(new Milk());

        System.out.println("\n" + order.getClient().getName()
                + NEW_PRODUCT + "\n" + chiefProduct
                + "\n" + PRICE_DELIMETER_HEADER + chiefProduct.getPrice() + "\n");
    }
}
