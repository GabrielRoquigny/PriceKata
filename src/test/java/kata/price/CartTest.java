package kata.price;

import kata.price.discount.Discount;
import kata.price.discount.XPercentOff;
import kata.price.tariffCondition.TariffCondition;
import kata.price.tariffCondition.XForPriceOfX;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static java.math.BigDecimal.*;
import static kata.price.TypeOfSort.ALPHABETIC;
import static kata.price.TypeOfSort.INITIAL_PRICE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsEmptyCollection.emptyCollectionOf;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

public class CartTest {
    @Test
    public void createNewCartAndGetNoItem() {
        Cart cart = new Cart();
        assertThat("The cart need to be empty when is created", cart.getItems(), emptyCollectionOf(Item.class));
    }

    @Test
    public void createNewCartAndAddSomeItemAndGetThem() {
        Cart cart = new Cart();
        Item item1 = newItem("Item 1"), item2 = newItem("Item 2");

        cart.add(item1);
        cart.add(item2);

        assertThat("Get the item put into the cart", cart.getItems(), containsInAnyOrder(item1, item2));
    }

    @Test
    public void createNewCartAndAddTwiceItemAndGetThem() {
        Cart cart = new Cart();
        Item item = newItem("Item");

        cart.add(item);
        cart.add(item);

        assertThat("Get two time the item put twice into the cart", cart.getItems(), containsInAnyOrder(item, item));
    }

    @Test
    public void createNewCartAndAddSomeItemAndGetThemIntoAlphabeticOrder() {
        Cart cart = new Cart();
        Item item1 = newItem("Item 1"), item2 = newItem("Item 2");

        cart.add(item2);
        cart.add(item1);

        assertThat("Get the item put into the cart by alphabetic order", cart.getItems(ALPHABETIC), contains(item1, item2));
    }

    @Test
    public void createNewCartAndAddSomeItemAndGetThemIntoInitialPriceOrder() {
        Cart cart = new Cart();
        Item item1 = newItem(ZERO), item2 = newItem(TEN);

        cart.add(item2);
        cart.add(item1);

        assertThat("Get the item put into the cart by price order", cart.getItems(INITIAL_PRICE), contains(item1, item2));
    }

    @Test
    public void getTotalPriceForItems() {
        Cart cart = new Cart();
        Item item1 = newItem(ONE), item2 = newItem(TEN);

        cart.add(item2);
        cart.add(item1);

        assertThat("Get the total price for the item put into the cart", cart.totalPrice(), equalTo(valueOf(11)));
    }

    @Test
    public void getTotalPriceZeroForEmptyCart() {
        Cart cart = new Cart();

        assertThat("Get the total price for the item put into the cart", cart.totalPrice(), equalTo(ZERO));
    }

    @Test
    public void cartApplyTheTariffConditionThreeForTwo() {
        Cart cart = new Cart();
        TariffCondition tariffCondition1 = getInstance();
        cart.add(tariffCondition1);
        Item item = newItem("Item", ONE);
        cart.add(item);
        cart.add(item);
        cart.add(item);
        Item itemCharged = newItem("3 x Item", valueOf(2));

        assertThat("Get charged item with a tariff condition", cart.getChargedItems(), contains(itemCharged));
    }

    @Test
    public void cartApply10PercentOff() {
        Cart cart = new Cart();
        Discount discount = new XPercentOff(10);
        cart.add(discount);
        Item item = newItem("Item", ONE);
        cart.add(item);
        cart.add(item);
        cart.add(item);

        assertThat("Get all item into cart", cart.getChargedItems(), contains(item, item, item));
        assertThat("Get price with 10% off", cart.totalPrice(), equalTo(valueOf(2.7)));
    }


    private Item newItem(String item) {
        return newItem(item, ZERO);
    }

    private Item newItem(BigDecimal initialPrice) {
        return newItem("item", initialPrice);
    }

    private Item newItem(String name, BigDecimal initialPrice) {
        return new Item(name, initialPrice);
    }

    private TariffCondition getInstance() {
        return new XForPriceOfX(3, 2);
    }
}
