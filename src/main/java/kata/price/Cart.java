package kata.price;

import kata.price.discount.Discount;
import kata.price.tariffCondition.TariffCondition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    private final Collection<Discount> discounts;
    private final Collection<TariffCondition> tariffConditions;
    private final Collection<Item> items;
    private Collection<Item> chargedItems;

    /**
     * Create a cart with no item and no tariff condition.
     */
    public Cart() {
        super();
        items = new ArrayList<>();
        tariffConditions = new ArrayList<>();
        discounts = new ArrayList<>();
    }


    /**
     * Get items charged.
     *
     * @return the list of items charged. (unmodifiable list).
     * @see #getChargedItems(TypeOfSort)
     */
    @Nonnull
    public Collection<Item> getChargedItems() {
        return getChargedItems(null);
    }

    /**
     * Get items charged.
     *
     * @param sort sorting off the charged items can be null.
     * @return the list of items charged. (unmodifiable list).
     */
    @Nonnull
    public Collection<Item> getChargedItems(@Nullable TypeOfSort sort) {
        if (chargedItems == null) {
            chargedItems = unmodifiableCollection(items);
            for (TariffCondition tariffCondition : tariffConditions) {
                chargedItems = tariffCondition.apply(chargedItems);
            }
        }
        if (sort != null) {
            List<Item> itemsCopy = new ArrayList<>(chargedItems);
            sort(itemsCopy, sort);
            return unmodifiableCollection(itemsCopy);
        }
        return unmodifiableCollection(chargedItems);

    }

    /**
     * Add an item.
     *
     * @param item item to add.
     */
    public void add(@Nonnull Item item) {
        resetChargedItem();
        items.add(item);
    }

    /**
     * Give the total price form all items into cart.
     *
     * @return total price.
     */
    @Nonnull
    public BigDecimal totalPrice() {
        BigDecimal totalPrice = ZERO;
        Collection<Item> items = getChargedItems();
        for (Item item : items) {
            totalPrice = totalPrice.add(item.getPrice());
        }
        for (Discount discount : discounts) {
            totalPrice = totalPrice.subtract(discount.apply(items));
        }
        return totalPrice;
    }


    /**
     * Add a tariff condition.
     *
     * @param tariffCondition tariff condition to add.
     */
    public void add(@Nonnull TariffCondition tariffCondition) {
        resetChargedItem();
        tariffConditions.add(tariffCondition);
    }

    /**
     * Add a discount.
     *
     * @param discount discount to add.
     */
    public void add(@Nonnull Discount discount) {
        resetChargedItem();
        discounts.add(discount);
    }

    private void resetChargedItem() {
        chargedItems = null;
    }
}
