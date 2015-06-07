package kata.price;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;

/**
 * Represent a cart.
 */
public class Cart {
    private Map<Item, Integer> items;

    {
        items = new HashMap<>();
    }

    public void add(@Nonnull Item item) {
        Integer nbItem = items.remove(item);
        if (null == nbItem) {
            items.put(item, 1);
        } else {
            items.put(item, nbItem + 1);
        }
    }

    public BigDecimal totalPrice() {
        return items.entrySet()
                .stream()
                .map((e) -> e.getKey().getPrice().multiply(valueOf(e.getValue())))
                .reduce(ZERO, (a, b) -> a.add(b));
    }
}
