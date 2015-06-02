package kata.price;

import java.util.Comparator;

/**
 * The type of sort for {@link kata.price.Item Item} into {@link kata.price.Cart Cart}.
 */
public enum TypeOfSort implements Comparator<Item> {
    ALPHABETIC {
        @Override
        public int compare(Item item1, Item item2) {
            return item1.getName().compareToIgnoreCase(item2.getName());
        }
    },
    INITIAL_PRICE {
        @Override
        public int compare(Item item1, Item item2) {
            return item1.getPrice().compareTo(item2.getPrice());
        }
    };

}
