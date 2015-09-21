package kata.price;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.util.Collections.unmodifiableCollection;
import static java.util.stream.Collectors.toList;

/**
 * Represent a cart.
 */
public class Cart {
    private final Map<Item, BigDecimal> items;
    private Collection<CartLine> cartLines;

    {
        items = new HashMap<>();
    }

    public void add(@Nonnull Item item) {
        add(item, ONE);
    }

    public void add(@Nonnull Item item, @Nonnull BigDecimal nb) {
        manipulateNb(item, nb);
    }

    public void remove(@Nonnull Item item) {
        remove(item, ONE);
    }

    public void remove(@Nonnull Item item, @Nonnull BigDecimal nb) {
        manipulateNb(item, nb.negate());
    }

    private void manipulateNb(@Nonnull Item item, @Nonnull BigDecimal nb) {
        BigDecimal nbItem = items.remove(item);
        if (null == nbItem) {
            nbItem = nb;
        } else {
            nbItem = nbItem.add(nb);
        }
        if (!nbItem.abs().equals(nbItem)) {
            throw new IllegalArgumentException(
                    format("Item %s cannot have negative quantity",
                            item.toString()));
        }
        if (!nbItem.equals(ZERO)) {
            items.put(item, nbItem);
        }
        resetCartLines();
    }

    public BigDecimal totalPrice() {
        return getCartLines().stream()
                             .map((e) -> e.getPrice().multiply(e.getNb()))
                             .reduce(ZERO, BigDecimal::add);
    }

    private void resetCartLines() {
        cartLines = null;
    }

    private Collection<CartLine> getCartLines() {
        if (null == cartLines) {
            cartLines = items.entrySet()
                             .stream()
                             .map((e) -> new CartLine(e.getKey(),
                                     e.getKey().getName(),
                                     e.getKey().getPrice(), e.getValue()))
                             .collect(toList());
        }
        return unmodifiableCollection(cartLines);
    }
}
