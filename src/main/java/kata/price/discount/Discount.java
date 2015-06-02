package kata.price.discount;

import kata.price.Item;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Represent a discount, is apply on all the {@link kata.price.Cart cart}.
 */
public interface Discount {
    /**
     * Apply the discount.
     * Get all items into cart, give the amount off.
     *
     * @param items items into cart.
     * @return the amount of discount.
     */
    BigDecimal apply(Collection<Item> items);

}
