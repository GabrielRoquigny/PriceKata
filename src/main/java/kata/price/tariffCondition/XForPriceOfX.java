package kata.price.tariffCondition;

import kata.price.Item;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.unmodifiableCollection;

/**
 * To get same 3 items for the price of 2.
 */
public class XForPriceOfX implements TariffCondition {

    private final int xItem;
    private final BigDecimal forXItem;
    private final Item item;

    /**
     * To create tariff condition for all item.
     * If one item is present xItem time then the price is forXItem of item.
     *
     * @param xItem    number of item to satisfy the tariff condition.
     * @param forXItem number of item need to pay.
     */
    public XForPriceOfX(int xItem, int forXItem) {
        this(xItem, forXItem, null);
    }

    /**
     * To create tariff condition for a specify item.
     * If one item is present xItem time then the price is forXItem of item.
     *
     * @param xItem    number of item to satisfy the tariff condition.
     * @param forXItem number of item need to pay.
     * @param item     the item to apply the tariff condition.
     */
    public XForPriceOfX(int xItem, int forXItem, Item item) {
        super();
        this.xItem = xItem;
        this.forXItem = valueOf(forXItem);
        this.item = item;
    }

    @Override
    public Collection<Item> apply(Collection<Item> items) {
        Collection<Item> itemsToFilter = new ArrayList<>(items);
        Collection<Item> itemsFiltered = new ArrayList<>();

        if (null == this.item) {
            for (Item item : items) {
                removeAndCreateItem(item, itemsToFilter, itemsFiltered);
            }
        } else {
            removeAndCreateItem(this.item, itemsToFilter, itemsFiltered);
            itemsFiltered.addAll(itemsToFilter);
        }

        return unmodifiableCollection(itemsFiltered);
    }

    private void removeAndCreateItem(Item item, Collection<Item> itemsToFilter, Collection<Item> itemsFiltered) {
        int nbItemRemoved = 0;
        while (itemsToFilter.remove(item)) {
            nbItemRemoved++;
        }
        if (nbItemRemoved == 0) {
            return;
        }
        int nbItemToCreate = nbItemRemoved / xItem;
        int nbItemToKeep = nbItemRemoved % xItem;
        for (int i = 0; i < nbItemToKeep; i++) {
            itemsFiltered.add(item);
        }
        for (int i = 0; i < nbItemToCreate; i++) {
            itemsFiltered.add(new XForXItem(item, xItem, forXItem));
        }
    }

    @Getter
    public static class XForXItem extends Item {
        private final Item item;

        protected XForXItem(Item item, int xItem, BigDecimal forXItem) {
            super(xItem + " x " + item.getName(), item.getPrice().multiply(forXItem));
            this.item = item;
        }
    }
}
