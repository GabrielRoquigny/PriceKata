package kata.price.tariffCondition;

import kata.price.Item;

import java.util.Collection;

/**
 * The tariff condition, is apply for each or for group of item into cart.
 */
public interface TariffCondition {

    /**
     * Apply the tariff condition.
     * Get all items into cart, create and remove items who are concern by the
     * tariff condition.
     *
     * @param items items into cart.
     * @return all items when the tariff condition is applied.
     */
    Collection<Item> apply(Collection<Item> items);
}
