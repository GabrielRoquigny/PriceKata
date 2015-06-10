package kata.price;

import org.testng.annotations.Test;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartTest {
    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "^Item .* cannot have negative quantity$")
    public void removeItemIntoEmptyCart() {
        Cart cart = new Cart();
        Item item = new Item("item 1", ONE);
        cart.remove(item);
    }

    @Test
    public void removeAllTheItemIntoCart() {
        Cart cart = new Cart();
        Item item = new Item("item 1", ONE);
        cart.add(item);
        cart.remove(item);
        assertThat("There is no item in the cart, total must be 0",
                cart.totalPrice(), equalTo(ZERO));
    }

    @Test
    public void getTotalFormOneItem() {
        Cart cart = new Cart();
        Item item = new Item("item 1", ONE);
        cart.add(item);

        assertThat("Only item 1 is in the cart, total must be 1",
                cart.totalPrice(), equalTo(ONE));
    }

    @Test
    public void getTotalFormOneAndHalfItem() {
        Cart cart = new Cart();
        Item item = new Item("item 1", ONE);
        cart.add(item, valueOf(1.5));

        assertThat("Only item 1.5 is in the cart, total must be 1.5",
                cart.totalPrice(), equalTo(valueOf(1.5)));
    }

    @Test
    public void getTotalFormTwoItems() {
        Cart cart = new Cart();
        Item item1 = new Item("item 1", ONE);
        Item item2 = new Item("item 2", TEN);
        cart.add(item1);
        cart.add(item2);

        assertThat("item 1 and item 2 are in the cart, total must be 11",
                cart.totalPrice(), equalTo(valueOf(11)));
    }

    @Test
    public void getTotalFormTwoItemsHalfNb() {
        Cart cart = new Cart();
        Item item1 = new Item("item 1", ONE);
        Item item2 = new Item("item 2", TEN);
        cart.add(item1, valueOf(1.5));
        cart.add(item2, valueOf(1.5));

        assertThat("item 1 and item 2 are in the cart, total must be 16.5",
                cart.totalPrice(), equalTo(valueOf(16.5)));
    }

    @Test
    public void getTotalFormTwoSameItems() {
        Cart cart = new Cart();
        Item item = new Item("item 1", ONE);
        cart.add(item);
        cart.add(item);

        assertThat("item 1 is twice in the cart, total must be 2",
                cart.totalPrice(), equalTo(valueOf(2)));
    }

    @Test
    public void getTotalFormTwoSameItemsHalfNb() {
        Cart cart = new Cart();
        Item item = new Item("item 1", ONE);
        cart.add(item, valueOf(1.5));
        cart.add(item);

        assertThat("item 1 is 2.5 times in the cart, total must be 2.5",
                cart.totalPrice(), equalTo(valueOf(2.5)));
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

        assertThat("item 1 and item 2 are twice in the cart, total must be 11",
                cart.totalPrice(), equalTo(valueOf(22)));
    }
}
