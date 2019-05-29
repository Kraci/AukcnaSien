package sk.kracina1.db2.application.validation.Rules;

import sk.kracina1.db2.application.validation.isPriceHigherThanPrevious;
import sk.kracina1.db2.application.rdg.AuctionItem;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class isPriceHigherThanPreviousTest {
    private AuctionItem auctionItem;
    private double price = 10.0;
    private double min_bid = 1.0;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        auctionItem = new AuctionItem();
        auctionItem.setPrice(price);
        auctionItem.setMin_bid_price(min_bid);
    }

    @org.junit.jupiter.api.Test
    void samePrice() {
        assertFalse(new isPriceHigherThanPrevious(auctionItem).passes(Double.toString(price)));
    }

    @org.junit.jupiter.api.Test
    void less() {
        assertFalse(new isPriceHigherThanPrevious(auctionItem).passes(Double.toString((price + (min_bid*.5)))));
    }

    @org.junit.jupiter.api.Test
    void more() {
        assertTrue(new isPriceHigherThanPrevious(auctionItem).passes(Double.toString(price + (min_bid*1.1))));
    }

    @org.junit.jupiter.api.Test
    void negative() {
        assertFalse(new isPriceHigherThanPrevious(auctionItem).passes(Double.toString(-(price ))));
    }

    @org.junit.jupiter.api.Test
    void zero() {
        assertFalse(new isPriceHigherThanPrevious(auctionItem).passes("0"));
    }
}
