package kata.price.tariffCondition;

import kata.price.Item;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;

import static java.math.BigDecimal.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class XForPriceOfXTest {
    @Test
    public void getOneItemFromThreeSame() {
        Item item = new Item("item 1", ONE);
        Collection<Item> items = new ArrayList<>(3);
        items.add(item);
        items.add(item);
        items.add(item);
        TariffCondition tariffCondition = getInstance();

        Item newItem = new Item("3 x item 1", valueOf(2));

        assertThat("One new item is created", tariffCondition.apply(items), not(containsInAnyOrder(item, item, item)));
        assertThat("The price is 2 and name is 3 x item 1", tariffCondition.apply(items), contains(newItem));
    }

    @Test
    public void getThreeItemFromThreeSameWithOtherItemIntoTariffCondition() {
        Item item = new Item("item 1", ONE);
        Collection<Item> items = new ArrayList<>(3);
        items.add(item);
        items.add(item);
        items.add(item);
        TariffCondition tariffCondition = getInstance(new Item("item 2", ONE));

        Item newItem = new Item("3 x item 1", valueOf(2));

        assertThat("One new item is created", tariffCondition.apply(items), containsInAnyOrder(item, item, item));
        assertThat("The price is 2 and name is 3 x item 1", tariffCondition.apply(items), not(contains(newItem)));
    }

    @Test
    public void getThreeItemFromThreeSameWithItemIntoTariffCondition() {
        Item item = new Item("item 1", ONE);
        Collection<Item> items = new ArrayList<>(3);
        items.add(item);
        items.add(item);
        items.add(item);
        TariffCondition tariffCondition = getInstance(item);

        Item newItem = new Item("3 x item 1", valueOf(2));

        assertThat("One new item is created", tariffCondition.apply(items), not(containsInAnyOrder(item, item, item)));
        assertThat("The price is 2 and name is 3 x item 1", tariffCondition.apply(items), contains(newItem));
    }

    @Test
    public void getOneItemFromThreeSameTwoTime() {
        Item item = new Item("item 1", ONE);
        Collection<Item> items = new ArrayList<>(6);
        items.add(item);
        items.add(item);
        items.add(item);
        items.add(item);
        items.add(item);
        items.add(item);
        TariffCondition tariffCondition = getInstance();

        Item newItem = new Item("3 x item 1", valueOf(2));

        assertThat("One new item is created", tariffCondition.apply(items), not(containsInAnyOrder(item, item, item, item, item, item)));
        assertThat("The price is 2 and name is 3 x item 1", tariffCondition.apply(items), contains(newItem, newItem));
    }

    @Test
    public void getOneItemFromThreeSameTwoTimeMixed() {
        Item item1 = new Item("item 1", ONE);
        Item item2 = new Item("item 2", TEN);
        Collection<Item> items = new ArrayList<>(6);
        items.add(item1);
        items.add(item2);
        items.add(item1);
        items.add(item2);
        items.add(item1);
        items.add(item2);
        TariffCondition tariffCondition = getInstance();

        Item newItem1 = new Item("3 x item 1", valueOf(2));
        Item newItem2 = new Item("3 x item 2", valueOf(20));

        assertThat("One new item is created", tariffCondition.apply(items), not(containsInAnyOrder(item1, item2, item1, item2, item1, item2)));
        assertThat("The price is 2 and name is 3 x item 1", tariffCondition.apply(items), contains(newItem1, newItem2));
    }

    @Test
    public void getTwoItemsFromTwo() {
        Item item1 = new Item("item 1", ONE);
        Item item2 = new Item("item 2", TEN);
        Collection<Item> items = new ArrayList<>(6);
        items.add(item1);
        items.add(item1);
        items.add(item2);
        TariffCondition tariffCondition = getInstance();

        Item newItem1 = new Item("3 x item 1", valueOf(2));
        Item newItem2 = new Item("3 x item 2", valueOf(20));

        assertThat("One new item is created", tariffCondition.apply(items), contains(item1, item1, item2));
        assertThat("The price is 2 and name is 3 x item 1", tariffCondition.apply(items), not(containsInAnyOrder(newItem1, newItem2)));
    }

    private TariffCondition getInstance() {
        return getInstance(3, 2, null);
    }

    private TariffCondition getInstance(Item item) {
        return new XForPriceOfX(3, 2, item);
    }

    private TariffCondition getInstance(int x, int forX, Item item) {
        return new XForPriceOfX(x, forX, item);
    }
}
