package kata.price;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Represent an item to put into a cart.
 */
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Item {
    @NonNull
    private final String name;
    @NonNull
    private final BigDecimal price;
}
