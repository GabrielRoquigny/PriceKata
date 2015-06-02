package kata.price.discount;

import kata.price.Item;

import java.math.BigDecimal;
import java.util.Collection;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

/**
 * Represent a percent discount.
 */
public class XPercentOff implements Discount {

    private final double percent;
    private final Item item;

    /**
     * To fix a percent for all item.
     *
     * @param percent the percent to apply.
     */
    public XPercentOff(double percent) {
        this(percent, null);
    }

    /**
     * To fix a percent for one item.
     *
     * @param percent the percent to apply.
     * @param item    the item on to apply the percent (all if null).
     */
    public XPercentOff(double percent, Item item) {
        this.percent = percent / 100;
        this.item = item;
    }

    /**
     * Apply the discount.
     * Get all items into cart, give the amount off.
     *
     * @param items items into cart.
     * @return the amount of discount.
     */
    @Override
    public BigDecimal apply(Collection<Item> items) {
        BigDecimal discount = ZERO;
        for (Item itemInToList : items) {
            if (null == item || item.equals(itemInToList)) {
                discount = discount.add(itemInToList.getPrice().multiply(valueOf(percent)));
            }
        }
        return discount;
    }
}
