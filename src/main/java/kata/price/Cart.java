package kata.price;

import kata.price.discount.Discount;
import kata.price.tariffCondition.TariffCondition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static java.util.Collections.sort;
import static java.util.Collections.unmodifiableCollection;

/**
 * Represent a cart.
 */
public class Cart {
    private Collection<TariffCondition> tariffConditions;
    private Collection<Item> items;

    /**
     * Create a cart with no item and no tariff condition.
     */
    public Cart() {
        super();
        items = new ArrayList<>();
        tariffConditions = new ArrayList<>();
    }

    /**
     * Get items in the order of insertion.
     *
     * @return the list of items. (unmodifiable list).
     */
    public Collection<Item> getItems() {
        return unmodifiableCollection(items);
    }

    /**
     * Get items in the specify order.
     *
     * @param sort the order to get the list of items.
     * @return the list of items. (unmodifiable list).
     */
    public Collection<Item> getItems(TypeOfSort sort) {
        List<Item> itemsCopy = new ArrayList<>(items);
        sort(itemsCopy, sort);
        return unmodifiableCollection(itemsCopy);
    }

    /**
     * Get items charged.
     *
     * @return the list of items charged. (unmodifiable list).
     */
    public Collection<Item> getChargedItems() {
        Collection<Item> chargedItems = getItems();
        for (TariffCondition tariffCondition : tariffConditions) {
            chargedItems = tariffCondition.apply(chargedItems);
        }
        return unmodifiableCollection(chargedItems);
    }

    /**
     * Add an item.
     *
     * @param item item to add.
     */
    public void add(Item item) {
        items.add(item);
    }

    /**
     * Give the total price form all items into cart.
     *
     * @return total price.
     */
    public BigDecimal totalPrice() {
        BigDecimal totalPrice = ZERO;
        for (Item item : items) {
            totalPrice = totalPrice.add(item.getPrice());
        }
        return totalPrice;
    }


    /**
     * Add a tariff condition.
     *
     * @param tariffCondition tariff condition to add.
     */
    public void add(TariffCondition tariffCondition) {
        tariffConditions.add(tariffCondition);
    }

    /**
     * Get the tariff conditions.
     *
     * @return the tariff conditions.
     * TODO to delete is only for test :(
     */
    protected Collection<TariffCondition> getTariffConditions() {
        return unmodifiableCollection(tariffConditions);
    }

    /**
     * Add a discount.
     *
     * @param discount tariff condition to add.
     */
    public void add(Discount discount) {
        throw new NotImplementedException();
    }
}
