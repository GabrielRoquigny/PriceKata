package kata.price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

/**
 * Represent a {@link kata.price.Cart cart} line.
 */
@Data
@AllArgsConstructor
public class CartLine {
    private final Item item;
    @NonNull
    private final String description;
    @NonNull
    private final BigDecimal price;
    @NonNull
    private final BigDecimal nb;

}
