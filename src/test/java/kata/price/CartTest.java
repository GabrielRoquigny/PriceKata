package kata.price;

import org.testng.annotations.Test;

import static java.math.BigDecimal.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartTest {
    @Test
    public void getTotalFormOneItem() {
        Cart cart = new Cart();
        Item item = new Item("item 1", ONE);
        cart.add(item);

        assertThat("Only item 1 is in the cart, total must be 1", cart.totalPrice(), equalTo(ONE));
    }

    @Test
    public void getTotalFormTwoItems() {
        Cart cart = new Cart();
        Item item1 = new Item("item 1", ONE);
        Item item2 = new Item("item 2", TEN);
        cart.add(item1);
        cart.add(item2);

        assertThat("item 1 and item 2 are in the cart, total must be 11", cart.totalPrice(), equalTo(valueOf(11)));
    }

    @Test
    public void getTotalFormTwoSameItems() {
        Cart cart = new Cart();
        Item item = new Item("item 1", ONE);
        cart.add(item);
        cart.add(item);

        assertThat("item 1 is twice in the cart, total must be 2", cart.totalPrice(), equalTo(valueOf(2)));
    }

    @Test
    public void getTotalFormTwoSameItemsTwoTimesIntoCart() {
        Cart cart = new Cart();
        Item item1 = new Item("item 1", ONE);
        Item item2 = new Item("item 2", TEN);
        cart.add(item1);
        cart.add(item2);
        cart.add(item1);
        cart.add(item2);

        assertThat("item 1 and item 2 are twice in the cart, total must be 11", cart.totalPrice(), equalTo(valueOf(22)));
    }
}
