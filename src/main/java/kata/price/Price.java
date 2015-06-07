package kata.price;

import kata.price.discount.Discount;
import kata.price.discount.XPercentOff;
import kata.price.tariffCondition.TariffCondition;
import kata.price.tariffCondition.XForPriceOfX;

import java.util.Collection;

import static java.math.BigDecimal.valueOf;

/**
 * To test the application.
 */
public class Price {
    private Item milk = new Item("1 liter of milk", valueOf(1.2));
    private Item ham = new Item("100 grammes of ham", valueOf(0.7));
    private Item soda = new Item("Can of soda", valueOf(0.3));
    private TariffCondition fourSodaForPriceOfThree = new XForPriceOfX(4, 3, soda);
    private Discount fivePercentOffOnMilk = new XPercentOff(5, milk);

    public static void main(String[] args) {
        Price app = new Price();
        app.run();
    }

    public void run() {
        Cart cart = new Cart();
        cart.add(milk);
        cart.add(milk);
        cart.add(ham);
        cart.add(soda);
        cart.add(soda);
        cart.add(soda);
        cart.add(ham);
        cart.add(soda);
        cart.add(milk);
        cart.add(milk);

        cart.add(fourSodaForPriceOfThree);
        cart.add(fivePercentOffOnMilk);

        Collection<Item> items = cart.getChargedItems(TypeOfSort.ALPHABETIC);
        for (Item item : items) {
            System.out.println(item.getName() + " - " + item.getPrice() + " €");
        }
        System.out.println(cart.totalPrice().toString() + " €");
    }
}
