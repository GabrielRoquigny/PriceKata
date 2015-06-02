package kata.price.discount;

import kata.price.Item;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;

import static java.math.BigDecimal.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test the class {@link kata.price.discount.XPrecentOffTest XPrecentOffTest}.
 */
public class XPrecentOffTest {
    @Test
    public void getPercentOnAllItem() {
        Item item1 = new Item("Item 1", valueOf(2.5));
        Item item2 = new Item("Item 2", TEN);
        Collection<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item1);

        Discount discount = new XPercentOff(5d);

        assertThat("5% of 15 is 0,75", discount.apply(items).setScale(2, ROUND_HALF_UP), equalTo(valueOf(.75d).setScale(2, ROUND_HALF_UP)));
    }

    @Test
    public void getPercentOnOneItem() {
        Item item1 = new Item("Item 1", valueOf(2.5));
        Item item2 = new Item("Item 2", TEN);
        Collection<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item1);

        Discount discount = new XPercentOff(5d, item2);

        assertThat("5% of 10 is 0,5", discount.apply(items).setScale(2, ROUND_HALF_UP), equalTo(valueOf(.5d).setScale(2, ROUND_HALF_UP)));
    }

    @Test
    public void getPercentOnNoItem() {
        Item item1 = new Item("Item 1", valueOf(2.5));
        Item item2 = new Item("Item 2", TEN);
        Collection<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item1);

        Discount discount = new XPercentOff(5d, new Item("Item 3", TEN));

        assertThat("5% of 0 is 0", discount.apply(items).setScale(2, ROUND_HALF_UP), equalTo(ZERO.setScale(2, ROUND_HALF_UP)));
    }
}
