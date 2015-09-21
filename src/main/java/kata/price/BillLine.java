package kata.price;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

/**
 * Represent a line of {@link kata.price.Bill bill}.
 */
@Data
@AllArgsConstructor
public class BillLine {
    @NonNull
    private final String description;
    @NonNull
    private final BigDecimal price;
    @NonNull
    private final BigDecimal nb;

    public BigDecimal total() {
        return price.multiply(nb);
    }
}
