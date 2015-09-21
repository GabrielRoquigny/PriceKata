package kata.price;

import org.testng.annotations.Test;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.valueOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test {@link kata.price.Bill bill} class.
 */
public class BillLineTest {

    @Test
    public void totalForPrice1AndNb3() throws Exception {
        BillLine billLine = new BillLine("Test", ONE, valueOf(3));

        assertThat("Total must be 3", billLine.total(), equalTo(valueOf(3)));
    }
}
